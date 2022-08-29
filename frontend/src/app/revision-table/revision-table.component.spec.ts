import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevisionTableComponent } from './revision-table.component';

describe('RevisionTableComponent', () => {
  let component: RevisionTableComponent;
  let fixture: ComponentFixture<RevisionTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RevisionTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RevisionTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
