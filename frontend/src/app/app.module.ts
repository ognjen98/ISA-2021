import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import {MatButtonModule} from '@angular/material/button';
import { HomeComponent } from './home/home/home.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { LoginComponent } from './login/login.component';
import { ClientHomeComponent } from './home/client-home/client-home.component';
import { AdminHomeComponent } from './home/admin-home/admin-home.component';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';
import { AuthGuard } from './helpers/auth.guard';

import { AuthInterceptor } from './helpers/auth.interceptor';
import { UpdateInfoComponent } from './update-info/update-info.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatNativeDateModule } from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ReservationComponent } from './reservation/reservation.component';
import { MatStepperModule} from '@angular/material/stepper';
import {DatetimerangepickerModule } from "angular-datetimerangepicker";
import { DlDateTimeDateModule, DlDateTimePickerModule } from 'angular-bootstrap-datetimepicker';
import { NgxDatetimeRangePickerModule } from 'ngx-datetime-range-picker';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule,NgxMatDateFormats, NGX_MAT_DATE_FORMATS } from '@angular-material-components/datetime-picker';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import { ShipsComponent } from './ships/ships.component';
import { LessonsComponent } from './lessons/lessons.component';
import { CottagesComponent } from './cottages/cottages.component';
import { ServicePageComponent } from './service-page/service-page.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { PendingReservationsComponent } from './pending-reservations/pending-reservations.component';
import { DefinePercentageComponent } from './define-percentage/define-percentage.component';
import { AllServicesComponent } from './all-services/all-services.component';
import { AllUsersComponent } from './all-users/all-users.component';
import {MatTableModule} from '@angular/material/table';
import { ChartsModule } from 'ng2-charts';
import { ChartsComponent } from './charts/charts.component';
import { CreateAdminComponent } from './create-admin/create-admin.component';
import { CategoryComponent } from './category/category.component';
import { PointsComponent } from './points/points.component';
import { ShipReservationsComponent } from './ship-reservations/ship-reservations.component';
import { CottageReservationsComponent } from './cottage-reservations/cottage-reservations.component';
import { LessonReservationsComponent } from './lesson-reservations/lesson-reservations.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    HomeComponent,
    LoginComponent,
    ClientHomeComponent,
    AdminHomeComponent,
    UpdateInfoComponent,
    ReservationComponent,
    ShipsComponent,
    LessonsComponent,
    CottagesComponent,
    ServicePageComponent,
    PendingReservationsComponent,
    DefinePercentageComponent,
    AllServicesComponent,
    AllUsersComponent,
    ChartsComponent,
    CreateAdminComponent,
    CategoryComponent,
    PointsComponent,
    ShipReservationsComponent,
    CottageReservationsComponent,
    LessonReservationsComponent
  ],
  imports: [
    MatRadioModule,
    MatTableModule,
    ChartsModule,
    MatExpansionModule,
    NgxMatDatetimePickerModule,
    MatCardModule,
    NgxMatTimepickerModule,
    MatListModule,
    NgxMatNativeDateModule,
    DlDateTimeDateModule,
    DlDateTimePickerModule,
    NgxDatetimeRangePickerModule.forRoot(),
    DatetimerangepickerModule,
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatFormFieldModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    AppRoutingModule,
    FormsModule,
    MatSelectModule,
    MatInputModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatButtonModule,
    MatStepperModule,
    MatGridListModule
  ],
  providers: [AuthGuard,
    {
      
      provide:HTTP_INTERCEPTORS,
      useClass:AuthInterceptor,
      multi:true
    },
    MatDatepickerModule,
    MatNativeDateModule],
    
  bootstrap: [AppComponent]
})
export class AppModule { }
