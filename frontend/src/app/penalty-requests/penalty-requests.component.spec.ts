import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PenaltyRequestsComponent } from './penalty-requests.component';

describe('PenaltyRequestsComponent', () => {
  let component: PenaltyRequestsComponent;
  let fixture: ComponentFixture<PenaltyRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PenaltyRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PenaltyRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
