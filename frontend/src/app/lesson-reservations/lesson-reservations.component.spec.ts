import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LessonReservationsComponent } from './lesson-reservations.component';

describe('LessonReservationsComponent', () => {
  let component: LessonReservationsComponent;
  let fixture: ComponentFixture<LessonReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LessonReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LessonReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
