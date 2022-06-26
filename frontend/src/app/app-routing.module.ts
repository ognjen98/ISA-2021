import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AuthGuard } from "./helpers/auth.guard";
import { AdminHomeComponent } from "./home/admin-home/admin-home.component";
import { ClientHomeComponent } from "./home/client-home/client-home.component";
import { HomeComponent } from "./home/home/home.component";
import { LoginComponent } from "./login/login.component";
import { RegistrationComponent } from "./registration/registration/registration.component";
import { ReservationComponent } from "./reservation/reservation.component";
import { UpdateInfoComponent } from "./update-info/update-info.component";

const routes: Routes = [
    {path:'', component: HomeComponent, children: [
      {path: 'register', component: RegistrationComponent},
      {path: 'login', component: LoginComponent}
    ]},
    {path: 'client', component: ClientHomeComponent, canActivate: [AuthGuard],
  
    
    data: {
      allowedRoles: ['ROLE_CLIENT']},
    children: [
      {path: 'updateInfo', component: UpdateInfoComponent}
    ]
  },
    {path: 'admin', component: AdminHomeComponent, canActivate: [AuthGuard],
  
    
    data: {
      allowedRoles: ['ROLE_SYSTEM_ADMIN']},
    children: [
        {path: 'updateInfo', component: UpdateInfoComponent}
    ]
  },
  {path: 'reservation', component: ReservationComponent}
    
  ];
  
  @NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }