import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TShirtComponent } from './t-shirt.component';

describe('TShirtComponent', () => {
  let component: TShirtComponent;
  let fixture: ComponentFixture<TShirtComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TShirtComponent]
    });
    fixture = TestBed.createComponent(TShirtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
