import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JeansComponent } from './jeans.component';

describe('JeansComponent', () => {
  let component: JeansComponent;
  let fixture: ComponentFixture<JeansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JeansComponent]
    });
    fixture = TestBed.createComponent(JeansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
