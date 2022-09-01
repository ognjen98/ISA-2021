package com.isa.isa;

import com.isa.requests.service.ComplaintService;
import com.isa.services.AdditionalInfo;
import com.isa.services.Reservation;
import com.isa.services.dto.ReservationDTO;
import com.isa.services.dto.SearchDataDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.service.ReservationService;
import org.aspectj.lang.annotation.Before;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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


	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");
				SearchDataDTO searchDataDTO = new SearchDataDTO(LocalDateTime.of(2022, 10, 3, 8, 0, 0),
						LocalDateTime.of(2022,10,4,8,0,0),	"",
						"SHIP", "", "");
				List<ServiceDTO> searchResult = reservationService.search(searchDataDTO);

				String email = "ognjencivcic23@gmail.com";
				Set<AdditionalInfo> additionalInfoList = new HashSet<>();
				additionalInfoList.add(new AdditionalInfo(1L, "", 50F));
				additionalInfoList.add(new AdditionalInfo(2L, "", 100F));
				LocalDate localDate =  LocalDate.of(2022, 10 ,3);
				LocalTime localTime = LocalTime.of(8, 0,0);
				LocalDate localDate2 =  LocalDate.of(2022, 10 ,4);
				LocalTime localTime2 = LocalTime.of(8, 0,0);
				ReservationDTO reservationDTO = new ReservationDTO(null, LocalDateTime.of(localDate, localTime),
						LocalDateTime.of(localDate2, localTime2),additionalInfoList, searchResult.get(0).getId(), 5);


				try { Thread.sleep(13000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi
				// drugi thread mogao da izvrsi istu operaciju

				Reservation reservation = reservationService.reserve(reservationDTO, email);
			}
		});
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");
				SearchDataDTO searchDataDTO = new SearchDataDTO(LocalDateTime.of(2022, 10, 3, 8, 0, 0),
						LocalDateTime.of(2022,10,4,8,0,0),	"",
						"SHIP", "", "");
				List<ServiceDTO> searchResult = reservationService.search(searchDataDTO);

				String email = "ognjen5@gmail.com";
				Set<AdditionalInfo> additionalInfoList = new HashSet<>();
				additionalInfoList.add(new AdditionalInfo(1L, "", 50F));
				additionalInfoList.add(new AdditionalInfo(2L, "", 100F));
				LocalDate localDate =  LocalDate.of(2022, 10 ,3);
				LocalTime localTime = LocalTime.of(8, 0,0);
				LocalDate localDate2 =  LocalDate.of(2022, 10 ,4);
				LocalTime localTime2 = LocalTime.of(8, 0,0);
				ReservationDTO reservationDTO = new ReservationDTO(null, LocalDateTime.of(localDate, localTime),
						LocalDateTime.of(localDate2, localTime2),additionalInfoList, searchResult.get(0).getId(), 5);

				Reservation reservation = reservationService.reserve(reservationDTO, email);
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


	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testComplaint() throws Throwable {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 1");




				try { Thread.sleep(5000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi
				// drugi thread mogao da izvrsi istu operaciju
				complaintService.respond(1L, "dobar dan");


			}
		});
		executor.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("Startovan Thread 2");

				complaintService.respond(1L, "asdasda");
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
