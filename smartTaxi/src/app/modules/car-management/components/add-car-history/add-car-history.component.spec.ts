import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCarHistoryComponent } from './add-car-history.component';

describe('AddCarHistoryComponent', () => {
  let component: AddCarHistoryComponent;
  let fixture: ComponentFixture<AddCarHistoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddCarHistoryComponent]
    });
    fixture = TestBed.createComponent(AddCarHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
