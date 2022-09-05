package com.isa.isa;

import com.isa.loyalties.Category;
import com.isa.loyalties.Points;
import com.isa.loyalties.repository.CategoryRepository;
import com.isa.loyalties.repository.PointsRepository;
import com.isa.requests.Complaint;
import com.isa.requests.DeleteRequest;
import com.isa.requests.SellerComplaint;
import com.isa.requests.ServiceComplaint;
import com.isa.requests.repository.ComplaintRepository;
import com.isa.requests.repository.DeleteRequestRepository;
import com.isa.requests.repository.SellerComplaintRepository;
import com.isa.requests.repository.ServiceComplaintRepository;
import com.isa.requests.service.ComplaintService;
import com.isa.services.*;
import com.isa.services.dto.ReservationDTO;
import com.isa.services.dto.SearchDataDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.repository.EarningPercentageRepository;
import com.isa.services.repository.EarningsRepository;
import com.isa.services.repository.ServiceRepository;
import com.isa.services.repository.TimePeriodRepository;
import com.isa.services.service.ReservationService;
import com.isa.services.service.ServicesService;
import com.isa.testing.domain.Product;
import com.isa.testing.service.ProductService;
import com.isa.users.*;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.ReservationRepository;
import com.isa.users.repository.SellerRepository;
import com.isa.users.repository.UserRepository;
import org.junit.Before;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsaApplicationTests {


	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	ComplaintRepository complaintRepository;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	EarningsRepository earningsRepository;

	@Autowired
	EarningPercentageRepository earningPercentageRepository;

	@Autowired
	PointsRepository pointsRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ServiceComplaintRepository serviceComplaintRepository;

	@Autowired
	SellerComplaintRepository sellerComplaintRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TimePeriodRepository timePeriodRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	DeleteRequestRepository deleteRequestRepository;

	@Autowired
	ServiceRepository serviceRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	EntityManager entityManager;

	@Autowired
	ServicesService servicesService;

	@Autowired
	EntityManagerFactory factory;

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");

//				EntityManager entityManager = factory.createEntityManager();
//				entityManager.getTransaction().begin();
				Set<AdditionalInfo> additionalInfos = new HashSet<>();
				additionalInfos.add(new AdditionalInfo(1L, "", 50F));
				additionalInfos.add(new AdditionalInfo(2L, "", 100F));
				ReservationDTO dto = new ReservationDTO(null, LocalDateTime.of(2022, 9, 15, 8, 0, 0),
						LocalDateTime.of(2022,9,16,6,0,0), additionalInfos, 1L, 0);

				Reservation reservation = reservationRepository.getReservationById(dto.getId());
				Client client = clientRepository.findByEmail("ognjencivcic23@gmail.com");




				com.isa.services.Service service = serviceRepository.findById(1L).get();
				List<Reservation> serviceReservations = reservationRepository.getReservationsByServiceId(service.getId());
				for(Reservation res : serviceReservations){
					if(dto.getId() == null) {
						if (((dto.getStart().isBefore(res.getStartTime()) || dto.getStart().isEqual(res.getStartTime())) && (dto.getEnd().isAfter(res.getStartTime())))
								|| ((dto.getStart().isBefore(res.getEndTime())) && (dto.getEnd().isAfter(res.getEndTime()) || dto.getEnd().isEqual(res.getEndTime())))) {
							System.out.println("Jebiga");
						}
					}
				}


				List<TimePeriod> removal = new ArrayList<>();
				List<TimePeriod> addition = new ArrayList<>();
				for(TimePeriod tp: service.getPeriod()){
					if((dto.getStart().isAfter(tp.getStart()) || dto.getStart().isEqual(tp.getStart())) && (dto.getEnd().isBefore(tp.getEnd()) || dto.getEnd().isEqual(tp.getEnd()))){
						removal.add(tp);
						TimePeriod first = new TimePeriod(tp.getStart(), dto.getStart());
						TimePeriod second = new TimePeriod(dto.getEnd(), tp.getEnd());
//						timePeriodRepository.save(first);
//						timePeriodRepository.save(second);

						addition.add(first);
						addition.add(second);
					}


				}
//
				service.getPeriod().removeAll(removal);
				service.getPeriod().addAll(addition);

				//try { Thread.sleep(400); } catch (InterruptedException e) {}
				serviceRepository.save(service);

				if(reservation == null){
				float price = 0;
				float hours = ChronoUnit.HOURS.between(dto.getStart(), dto.getEnd());
				price+=service.getPrice() * hours;
				for(AdditionalInfo additionalInfo: dto.getAdditionalInfos()){
					price += additionalInfo.getPrice();
				}
				Optional<EarningPercentage> ep = earningPercentageRepository.findById(1L);

				Earnings earnings = new Earnings(LocalDate.now(), price*(ep.get().getPercentage()/100));
				Category goldClient = categoryRepository.findCategoryByNameAndType("GOLD", "CLIENT");
				Category silverClient = categoryRepository.findCategoryByNameAndType("SILVER", "CLIENT");
				Category bronzeClient = categoryRepository.findCategoryByNameAndType("BRONZE", "CLIENT");
				if(client.getPoints()>goldClient.getPoints()){
					price = price - price * goldClient.getDiscount();
				}
				else if(client.getPoints() > silverClient.getPoints()){
					price = price - price * silverClient.getDiscount();
				}
				else if(client.getPoints() > bronzeClient.getPoints()){
					price = price - price * bronzeClient.getDiscount();
				}
				Category goldSeller = categoryRepository.findCategoryByNameAndType("GOLD", "SELLER");
				Category silverSeller = categoryRepository.findCategoryByNameAndType("SILVER", "SELLER");
				Category bronzeSeller = categoryRepository.findCategoryByNameAndType("BRONZE", "SELLER");
				Seller seller = service.getSeller();
				float sellerMoney = 0;
				if(seller.getPoints()>goldSeller.getPoints()){
					sellerMoney = price - price * goldSeller.getDiscount()/100;
				}
				else if(seller.getPoints() > silverSeller.getPoints()){
					sellerMoney = price - price * silverSeller.getDiscount()/100;
				}
				else if(seller.getPoints() > bronzeSeller.getPoints()){
					sellerMoney = price - price * bronzeSeller.getDiscount()/100;
				}
				seller.setMoney(sellerMoney);

//					earningsRepository.save(earnings);
				Optional<Points> points = pointsRepository.findById(1L);
				int sellerPoints = seller.getPoints();
				int clientPoints = client.getPoints();
				sellerPoints += points.get().getSellerPoints();
				clientPoints += points.get().getClientPoints();

				client.setPoints(clientPoints);
				seller.setPoints(sellerPoints);
					sellerRepository.save(seller);
					clientRepository.save(client);
				reservation = new Reservation(dto.getStart(), dto.getEnd(), dto.getNoPersons(),
                    dto.getAdditionalInfos(), price, service.getAddress(), service, client, false, true, false);

				// thread uspavan na 3 sekunde da bi
				reservationRepository.save(reservation);

				}






				// drugi thread mogao da izvrsi istu operaciju


//				entityManager.flush();
//				entityManager.getTransaction().commit();
//				entityManager.close();
			}
		});
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
//				EntityManager entityManager = factory.createEntityManager();
//				entityManager.getTransaction().begin();
				Set<AdditionalInfo> additionalInfos = new HashSet<>();
				additionalInfos.add(new AdditionalInfo(1L, "", 50F));
				additionalInfos.add(new AdditionalInfo(2L, "", 100F));
				ReservationDTO dto = new ReservationDTO(null, LocalDateTime.of(2022, 9, 15, 8, 0, 0),
						LocalDateTime.of(2022,9,16,6,0,0), additionalInfos, 1L, 0);
				Reservation reservation = reservationRepository.getReservationById(dto.getId());

				Client client = clientRepository.findByEmail("ognjencivcic23@gmail.com");




				com.isa.services.Service service = serviceRepository.findById(1L).get();

				List<Reservation> serviceReservations = reservationRepository.getReservationsByServiceId(service.getId());
				for(Reservation res : serviceReservations){
					if(dto.getId() == null) {
						if (((dto.getStart().isBefore(res.getStartTime()) || dto.getStart().isEqual(res.getStartTime())) && (dto.getEnd().isAfter(res.getStartTime())))
								|| ((dto.getStart().isBefore(res.getEndTime())) && (dto.getEnd().isAfter(res.getEndTime()) || dto.getEnd().isEqual(res.getEndTime())))) {
							System.out.println("Jebiga");
						}
					}
				}


				List<TimePeriod> removal = new ArrayList<>();
				List<TimePeriod> addition = new ArrayList<>();
				for(TimePeriod tp: service.getPeriod()){
					if((dto.getStart().isAfter(tp.getStart()) || dto.getStart().isEqual(tp.getStart())) && (dto.getEnd().isBefore(tp.getEnd()) || dto.getEnd().isEqual(tp.getEnd()))){
						removal.add(tp);
						TimePeriod first = new TimePeriod(tp.getStart(), dto.getStart());
						TimePeriod second = new TimePeriod(dto.getEnd(), tp.getEnd());
//						timePeriodRepository.save(first);
//						timePeriodRepository.save(second);

						addition.add(first);
						addition.add(second);
					}


				}

				service.getPeriod().removeAll(removal);
				service.getPeriod().addAll(addition);


				serviceRepository.save(service);

				if(reservation == null) {
					float price = 0;
					float hours = ChronoUnit.HOURS.between(dto.getStart(), dto.getEnd());
					price += service.getPrice() * hours;
				for(AdditionalInfo additionalInfo: dto.getAdditionalInfos()){
					price += additionalInfo.getPrice();
				}
					Optional<EarningPercentage> ep = earningPercentageRepository.findById(1L);
				Earnings earnings = new Earnings(LocalDate.now(), price*(ep.get().getPercentage()/100));
				Category goldClient = categoryRepository.findCategoryByNameAndType("GOLD", "CLIENT");
				Category silverClient = categoryRepository.findCategoryByNameAndType("SILVER", "CLIENT");
				Category bronzeClient = categoryRepository.findCategoryByNameAndType("BRONZE", "CLIENT");
				if(client.getPoints()>goldClient.getPoints()){
					price = price - price * goldClient.getDiscount();
				}
				else if(client.getPoints() > silverClient.getPoints()){
					price = price - price * silverClient.getDiscount();
				}
				else if(client.getPoints() > bronzeClient.getPoints()){
					price = price - price * bronzeClient.getDiscount();
				}
				Category goldSeller = categoryRepository.findCategoryByNameAndType("GOLD", "SELLER");
				Category silverSeller = categoryRepository.findCategoryByNameAndType("SILVER", "SELLER");
				Category bronzeSeller = categoryRepository.findCategoryByNameAndType("BRONZE", "SELLER");
				Seller seller = service.getSeller();
				float sellerMoney = 0;
				if(seller.getPoints()>goldSeller.getPoints()){
					sellerMoney = price - price * goldSeller.getDiscount()/100;
				}
				else if(seller.getPoints() > silverSeller.getPoints()){
					sellerMoney = price - price * silverSeller.getDiscount()/100;
				}
				else if(seller.getPoints() > bronzeSeller.getPoints()){
					sellerMoney = price - price * bronzeSeller.getDiscount()/100;
				}
				seller.setMoney(sellerMoney);

//					earningsRepository.save(earnings);
				Optional<Points> points = pointsRepository.findById(1L);
				int sellerPoints = seller.getPoints();
				int clientPoints = client.getPoints();
				sellerPoints += points.get().getSellerPoints();
				clientPoints += points.get().getClientPoints();

				client.setPoints(clientPoints);
				seller.setPoints(sellerPoints);
					sellerRepository.save(seller);
					clientRepository.save(client);
					reservation = new Reservation(dto.getStart(), dto.getEnd(), dto.getNoPersons(),
                    dto.getAdditionalInfos(), price, service.getAddress(), service, client, false, true, false);


					reservationRepository.save(reservation);

				}
//				entityManager.flush();
//				entityManager.getTransaction().commit();
//				entityManager.close();



			}
		});
		try {
			future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
		} catch (ExecutionException e) {
			System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
			throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();

	}


//	@Test(expected = ObjectOptimisticLockingFailureException.class)
//	public void testComplaint() throws Throwable {
//
//		ExecutorService executor = Executors.newFixedThreadPool(2);
//		Future<?> future1 = executor.submit(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println("Startovan Thread 1");
//
//				Optional<Complaint> complaint = complaintRepository.findById(1L);
//
//				// thread uspavan na 3 sekunde da bi
//				// drugi thread mogao da izvrsi istu operaciju
//				if(complaint.get() instanceof ServiceComplaint) {
//					ServiceComplaint serviceComplaint = (ServiceComplaint) complaint.get();
//					serviceComplaint.setStatus(1);
//					try { Thread.sleep(100); } catch (InterruptedException e) {}
//					serviceComplaintRepository.save(serviceComplaint);
////            emailSender.sendEmail(serviceComplaint.getService().getSeller().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
////            emailSender.sendEmail(serviceComplaint.getClient().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
//				}
//				else if(complaint.get() instanceof SellerComplaint) {
//					SellerComplaint sellerComplaint = (SellerComplaint) complaint.get();
//					sellerComplaint.setStatus(1);
//					sellerComplaintRepository.save(sellerComplaint);
////            emailSender.sendEmail(sellerComplaint.getSeller().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
////            emailSender.sendEmail(sellerComplaint.getClient().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
//				}
//
//
//			}
//		});
//		executor.submit(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println("Startovan Thread 2");
//				Optional<Complaint> complaint = complaintRepository.findById(1L);
//
//				// drugi thread mogao da izvrsi istu operaciju
//				if(complaint.get() instanceof ServiceComplaint) {
//					ServiceComplaint serviceComplaint = (ServiceComplaint) complaint.get();
//					serviceComplaint.setStatus(1);
//
//					serviceComplaintRepository.save(serviceComplaint);
////            emailSender.sendEmail(serviceComplaint.getService().getSeller().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
////            emailSender.sendEmail(serviceComplaint.getClient().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
//				}
//				else if(complaint.get() instanceof SellerComplaint) {
//					SellerComplaint sellerComplaint = (SellerComplaint) complaint.get();
//					sellerComplaint.setStatus(1);
//					sellerComplaintRepository.save(sellerComplaint);
////            emailSender.sendEmail(sellerComplaint.getSeller().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
////            emailSender.sendEmail(sellerComplaint.getClient().getEmail(), buildEmail("", "", "COM",
////                    response), "COM");
//				}
//			}
//		});
//		try {
//			future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
//		} catch (ExecutionException e) {
//			System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
//			throw e.getCause();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		executor.shutdown();
//
//	}
//

		@Before
		public void setUp() throws Exception {
			User user = userRepository.findByEmail("ognjencivcic23@gmail.com");
			deleteRequestRepository.save(new DeleteRequest("message", user,  2));
		}


		@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testDelete() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");

				Optional<User> user = userRepository.findById(5L);
				DeleteRequest deleteRequest = deleteRequestRepository.findByUser(user.get());
				deleteRequest.setStatus(0);
				try { Thread.sleep(500); } catch (InterruptedException e) {}

				deleteRequestRepository.save(deleteRequest);



			}
		});
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
				Optional<User> user = userRepository.findById(5L);
				DeleteRequest deleteRequest = deleteRequestRepository.findByUser(user.get());
				deleteRequest.setStatus(0);
				deleteRequestRepository.save(deleteRequest);
			}
		});
		try {
			future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
		} catch (ExecutionException e) {
			System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
			throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();

	}




}
