import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { isDriverGuard } from './is-driver.guard';

describe('isDriverGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => isDriverGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
