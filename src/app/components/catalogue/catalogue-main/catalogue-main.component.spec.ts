import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalogueMainComponent } from './catalogue-main.component';

describe('CatalogueMainComponent', () => {
  let component: CatalogueMainComponent;
  let fixture: ComponentFixture<CatalogueMainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CatalogueMainComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CatalogueMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
