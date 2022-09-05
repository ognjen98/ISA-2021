import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionReservationsComponent } from './action-reservations.component';

describe('ActionReservationsComponent', () => {
  let component: ActionReservationsComponent;
  let fixture: ComponentFixture<ActionReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionReservationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
