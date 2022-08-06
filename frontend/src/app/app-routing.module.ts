import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { CottagesComponent } from "./cottages/cottages.component";
import { AuthGuard } from "./helpers/auth.guard";
import { AdminHomeComponent } from "./home/admin-home/admin-home.component";
import { ClientHomeComponent } from "./home/client-home/client-home.component";
import { HomeComponent } from "./home/home/home.component";
import { LessonsComponent } from "./lessons/lessons.component";
import { LoginComponent } from "./login/login.component";
import { PendingReservationsComponent } from "./pending-reservations/pending-reservations.component";
import { RegistrationComponent } from "./registration/registration/registration.component";
import { ReservationComponent } from "./reservation/reservation.component";
import { ServicePageComponent } from "./service-page/service-page.component";
import { ShipsComponent } from "./ships/ships.component";
import { UpdateInfoComponent } from "./update-info/update-info.component";

const routes: Routes = [
    {path:'', component: HomeComponent, children: [
      {path: 'register', component: RegistrationComponent},
      {path: 'login', component: LoginComponent},
      {path: 'ships', component: ShipsComponent},
      {path: 'lessons', component: LessonsComponent},
      {path: 'cottages', component: CottagesComponent},
      {path: 'lessons/servicePage/:id', component: ServicePageComponent},
      {path: 'ships/servicePage/:id', component: ServicePageComponent},
      {path: 'cottages/servicePage/:id', component: ServicePageComponent},
      {path: 'pendingRes', component: PendingReservationsComponent}
    ]},
    {path: 'client', component: ClientHomeComponent, canActivate: [AuthGuard],
  
    
    data: {
      allowedRoles: ['ROLE_CLIENT']},
    children: [
      {path: 'updateInfo', component: UpdateInfoComponent},
      {path: 'reservation', component: ReservationComponent}
    ]
  },
    {path: 'admin', component: AdminHomeComponent, canActivate: [AuthGuard],
  
    
    data: {
      allowedRoles: ['ROLE_SYSTEM_ADMIN']},
    children: [
        {path: 'updateInfo', component: UpdateInfoComponent}
    ]
  },
  
    
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }