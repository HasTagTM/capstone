import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaQComponent } from './fa-q.component';

describe('FaQComponent', () => {
  let component: FaQComponent;
  let fixture: ComponentFixture<FaQComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FaQComponent]
    });
    fixture = TestBed.createComponent(FaQComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
