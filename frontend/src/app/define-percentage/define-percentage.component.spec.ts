import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefinePercentageComponent } from './define-percentage.component';

describe('DefinePercentageComponent', () => {
  let component: DefinePercentageComponent;
  let fixture: ComponentFixture<DefinePercentageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DefinePercentageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DefinePercentageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
