import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ConsumatoreComponent} from './consumatore.component';

describe('ConsumatoreComponent', () => {
  let component: ConsumatoreComponent;
  let fixture: ComponentFixture<ConsumatoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumatoreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumatoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
