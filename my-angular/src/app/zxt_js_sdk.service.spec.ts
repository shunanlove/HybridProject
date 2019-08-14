import { TestBed } from '@angular/core/testing';

import { ZxtJsSdkService } from './zxt_js_sdk.service';

describe('ZxtJsSdkService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ZxtJsSdkService = TestBed.get(ZxtJsSdkService);
    expect(service).toBeTruthy();
  });
});
