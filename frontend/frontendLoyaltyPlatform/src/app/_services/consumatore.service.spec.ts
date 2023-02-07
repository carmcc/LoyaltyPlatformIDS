import {TestBed} from '@angular/core/testing';

import {ConsumatoreService} from './consumatore.service';

describe('ConsumatoreService', () => {
  let service: ConsumatoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsumatoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
