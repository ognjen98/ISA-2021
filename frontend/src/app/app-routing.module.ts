import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AllServicesComponent } from "./all-services/all-services.component";
import { AllUsersComponent } from "./all-users/all-users.component";
import { CategoryComponent } from "./category/category.component";
import { ChartsComponent } from "./charts/charts.component";
import { CottageReservationsComponent } from "./cottage-reservations/cottage-reservations.component";
import { CottagesComponent } from "./cottages/cottages.component";
import { CreateAdminComponent } from "./create-admin/create-admin.component";
import { DefinePercentageComponent } from "./define-percentage/define-percentage.component";
import { DeleteRequestComponent } from "./delete-request/delete-request.component";
import { DeleteRequestsComponent } from "./delete-requests/delete-requests.component";
import { AuthGuard } from "./helpers/auth.guard";
import { AdminHomeComponent } from "./home/admin-home/admin-home.component";
import { ClientHomeComponent } from "./home/client-home/client-home.component";
import { HomeComponent } from "./home/home/home.component";
import { LessonReservationsComponent } from "./lesson-reservations/lesson-reservations.component";
import { LessonsComponent } from "./lessons/lessons.component";
import { LoginComponent } from "./login/login.component";
import { PendingReservationsComponent } from "./pending-reservations/pending-reservations.component";
import { PointsComponent } from "./points/points.component";
import { RegRequestsComponent } from "./reg-requests/reg-requests.component";
import { RegistrationComponent } from "./registration/registration/registration.component";
import { ReservationComponent } from "./reservation/reservation.component";
import { RevisionTableComponent } from "./revision-table/revision-table.component";
import { RevisionComponent } from "./revision/revision.component";
import { ServicePageComponent } from "./service-page/service-page.component";
import { ShipReservationsComponent } from "./ship-reservations/ship-reservations.component";
import { ShipsComponent } from "./ships/ships.component";
import { SubscriptionsComponent } from "./subscriptions/subscriptions.component";
import { UpdateInfoComponent } from "./update-info/update-info.component";

const routes: Routes = [
    {path:'', component: HomeComponent, children: [
      {path: 'register', component: RegistrationComponent},
      {path: 'login', component: LoginComponent},
      {path: 'category', component: CategoryComponent},
      {path: 'points', component: PointsComponent},
      {path: 'ships', component: ShipsComponent},
      {path: 'lessons', component: LessonsComponent},
      {path: 'cottages', component: CottagesComponent},
      {path: 'lessons/servicePage/:id', component: ServicePageComponent},
      {path: 'ships/servicePage/:id', component: ServicePageComponent},
      {path: 'cottages/servicePage/:id', component: ServicePageComponent},
      {path: 'shipRes', component: ShipReservationsComponent},
      {path: 'cottageRes', component: CottageReservationsComponent},
      {path: 'lessonRes', component: LessonReservationsComponent},
      {path: 'subscriptions', component: SubscriptionsComponent},
      {path: 'regRequests', component: RegRequestsComponent},
      {path: 'cottageRes/revision/:id', component: RevisionComponent},
      {path: 'shipRes/revision/:id', component: RevisionComponent},
      {path: 'lessonRes/revision/:id', component: RevisionComponent},
      {path: 'revisionTable', component: RevisionTableComponent},
      {path: 'deleteRequest', component: DeleteRequestComponent},
      {path: 'deleteRequests', component: DeleteRequestsComponent},





    ]},
    {path: 'client', component: ClientHomeComponent, canActivate: [AuthGuard],
  
    
    data: {
      allowedRoles: ['ROLE_CLIENT']},
    children: [
      {path: 'updateInfo', component: UpdateInfoComponent},
      {path: 'reservation', component: ReservationComponent},
      {path: 'pendingRes', component: PendingReservationsComponent},
    ]
  },
    {path: 'admin', component: AdminHomeComponent, canActivate: [AuthGuard],
  
    
    data: {
      allowedRoles: ['ROLE_SYSTEM_ADMIN']},
    children: [
        {path: 'updateInfo', component: UpdateInfoComponent},
        {path: 'percentage', component: DefinePercentageComponent},
      {path: 'allServices', component: AllServicesComponent},
      {path: 'allUsers', component: AllUsersComponent},
      {path: 'charts', component: ChartsComponent},
      {path: 'createAdmin', component: CreateAdminComponent}
    ]
  },
  
    
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }