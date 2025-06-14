import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { Filter } from '../../../models/filter';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Platform } from '../../../models/platform';
import { Publisher } from '../../../models/publisher';
import { Language } from '../../../models/language';
import { Store } from '../../../models/store';
import { Developer } from '../../../models/developer';
import { Tag } from '../../../models/tag';
import { PublisherService } from '../../../services/publisherService';
import { StoreService } from '../../../services/storeService';
import { DeveloperService } from '../../../services/developerService';
import { LanguageService } from '../../../services/languageService';
import { TagService } from '../../../services/tagService';
import { forkJoin } from 'rxjs';
import { PlatformService } from '../../../services/platformService';

@Component({
  selector: 'app-filter-component',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './filter-component.component.html',
  styleUrl: './filter-component.component.css'
})
export class FilterComponentComponent implements OnInit{
  filters: Partial<Filter> = {};
  private _platformService = inject(PlatformService);
  private _publisherService = inject(PublisherService);
  private _storeService = inject(StoreService);
  private _developerService = inject(DeveloperService);
  private _languageService = inject(LanguageService);
  private _tagService = inject(TagService);
  private _formBuilder = inject(FormBuilder);
  @Output() filtersChanged = new EventEmitter<Partial<Filter>>();
  platforms: Platform[] = [];
  publishers: Publisher[] = [];
  stores: Store[] = [];
  developers: Developer[] = [];
  languages: Language[] = [];
  tags: Tag[] = [];

  filtersForm: FormGroup;

  constructor(){
    this.filtersForm = this._formBuilder.group({
      title: "",
    });
  }


  ngOnInit(): void {
    forkJoin([this._platformService.getPlatforms(), this._publisherService.getPublishers(), 
              this._storeService.getStores(), this._developerService.getDevelopers(), 
              this._languageService.getLanguages(), this._tagService.getTags()])
          .subscribe({
            next: result => {
              this.platforms = result[0];
              this.publishers = result[1];
              this.stores = result[2];
              this.developers = result[3];
              this.languages = result[4];
              this.tags = result[5];
              this.setPlatformsControls();
              this.setPublishersControls();
              this.setStoresControls();
              this.setDevelopersControls();
              this.setLanguagesControls();
              this.setTagsControls();
            },
            error: e => console.log("Fork join error: " + e)
          });
  }

  searchGames(){
    const formValue = this.filtersForm.value;
    formValue.title == "" ? this.filters.title = undefined : this.filters.title  = formValue.title;
    this.filters.platforms = this.platforms.filter((_,i) => formValue.platforms[i]).map(p => p.name);
    this.filters.publishers = this.publishers.filter((_,i) => formValue.publishers[i]).map(p => p.name);
    this.filters.stores = this.stores.filter((_,i) => formValue.stores[i]).map(s => s.name);
    this.filters.developers = this.developers.filter((_,i) => formValue.developer[i]).map(d => d.name);
    this.filters.languages = this.languages.filter((_,i) => formValue.languages[i]).map(l => l.name);
    this.filters.tags = this.tags.filter((_,i) => formValue.tags[i]).map(t => t.name);
  }
  
  private setPlatformsControls(): void{
    this.filtersForm.setControl(
      'platforms',
      this._formBuilder.array(
        this.platforms.map(() => new FormControl(false))
      )
    );
  }
  
  private setPublishersControls(): void{
    this.filtersForm.setControl(
      'publishers',
      this._formBuilder.array(
        this.publishers.map(() => new FormControl(false))
      )
    );
  }
  private setStoresControls(): void{
    this.filtersForm.setControl(
      'stores',
      this._formBuilder.array(
        this.stores.map(() => new FormControl(false))
      )
    );
  }
  private setDevelopersControls(): void{
    this.filtersForm.setControl(
      'developers',
      this._formBuilder.array(
        this.developers.map(() => new FormControl(false))
      )
    );
  }
  private setLanguagesControls(): void{
    this.filtersForm.setControl(
      'languages',
      this._formBuilder.array(
        this.languages.map(() => new FormControl(false))
      )
    );
  }
  private setTagsControls(): void{
    this.filtersForm.setControl(
      'tags',
      this._formBuilder.array(
        this.tags.map(() => new FormControl(false))
      )
    );
  }
  

}
