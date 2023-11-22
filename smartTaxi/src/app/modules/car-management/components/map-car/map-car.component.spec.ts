import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapCarComponent } from './map-car.component';

describe('MapCarComponent', () => {
  let component: MapCarComponent;
  let fixture: ComponentFixture<MapCarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MapCarComponent]
    });
    fixture = TestBed.createComponent(MapCarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
