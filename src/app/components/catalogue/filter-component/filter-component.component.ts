import { Component, EventEmitter, HostBinding, inject, Input, OnInit, Output } from '@angular/core';
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
  private _platformService = inject(PlatformService);
  private _publisherService = inject(PublisherService);
  private _storeService = inject(StoreService);
  private _developerService = inject(DeveloperService);
  private _languageService = inject(LanguageService);
  private _tagService = inject(TagService);
  private _formBuilder = inject(FormBuilder);
  @Output() filtersChanged = new EventEmitter<Partial<Filter>>();
  filters: Partial<Filter> = {};
  platforms: Platform[] = [];
  publishers: Publisher[] = [];
  stores: Store[] = [];
  developers: Developer[] = [];
  languages: Language[] = [];
  tags: Tag[] = [];
  filtersForm: FormGroup;
  isPlatformOpen = false;
  isPublisherOpen = false;
  isStoreOpen = false;
  isDeveloperOpen = false;
  isLanguageOpen = false;
  isTagOpen = false;
  @HostBinding("class.side-menu-open") isSideMenuOpen = false;

  constructor(){
    this.filtersForm = this._formBuilder.group({
      title: "",
      platforms: this._formBuilder.group({}),
      publishers: this._formBuilder.group({}),
      stores: this._formBuilder.group({}),
      developers: this._formBuilder.group({}),
      languages: this._formBuilder.group({}),
      tags: this._formBuilder.group({}),
      sorts: ""
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
              this.setGroupControls("platforms", this.platforms);
              this.setGroupControls("publishers", this.publishers);
              this.setGroupControls("stores", this.stores);
              this.setGroupControls("developers", this.developers);
              this.setGroupControls("languages", this.languages);
              this.setGroupControls("tags", this.tags);
              console.log(this.filtersForm);
              console.log(this.filtersForm.value);
            },
            error: e => console.log("Fork join error: " + e)
          });
  }

  searchGames(){
    this.setFilters();
    this.filtersChanged.emit(this.filters);
  }

  private setFilters(): void{
    const formValue = this.filtersForm.value;
    formValue.title == "" ? this.filters.title = undefined : this.filters.title  = formValue.title;
    this.filters.platforms = Object.keys(formValue.platforms).filter(pName => formValue.platforms[pName]);
    this.filters.publishers = Object.keys(formValue.publishers).filter(pName => formValue.publishers[pName]);
    const storeNames = Object.keys(formValue.stores).filter(sName => formValue.stores[sName]);
    this.filters.developers = Object.keys(formValue.developers).filter(dName => formValue.stores[dName]);
    this.filters.languages = Object.keys(formValue.languages).filter(lName => formValue.languages[lName]);
    this.filters.tags = Object.keys(formValue.tags).filter(tName => formValue.tags[tName]);
    this.filters.stores = storeNames.map(s => this.replaceUnderscore(s));
    this.filters.sortByName = (formValue.sorts && formValue.sorts == "alphabetical" ? true : false);
    this.filters.sortByDate = (formValue.sorts && formValue.sorts == "date" ? true : false);
  }

  private setGroupControls(groupName: string, list: any[]): void{
    const group = list.reduce((acc: {[key: string]: FormControl}, item) => {
      item.name = this.replaceDot(item.name);
      acc[item.name] = new FormControl(false);
      return acc;
    }, {});
    this.filtersForm.setControl(groupName, this._formBuilder.group(group));
  }

  replaceUnderscore(s: string){
    return s.replace("_", ".");
  }

  replaceDot(s: string){
    return s.replace(".", "_");
  }

  resetFilters(){
    this.filtersForm.reset({
      platforms: [],
      publishers: [],
      stores: [],
      developers: [],
      languages: [],
      tags: []
    })
  }


  toggleSideMenu() {
    this.isSideMenuOpen = !this.isSideMenuOpen;
  }
  togglePlatformMenu() {
    this.isPlatformOpen = !this.isPlatformOpen;
  }
  togglePublisherMenu() {
    this.isPublisherOpen = !this.isPublisherOpen;
  }
  toggleStoreMenu() {
    this.isStoreOpen = !this.isStoreOpen;
  }
  toggleDeveloperMenu() {
    this.isDeveloperOpen = !this.isDeveloperOpen;
  }
  toggleLanguageMenu() {
    this.isLanguageOpen = !this.isLanguageOpen;
  }
  toggleTagMenu() {
    this.isTagOpen = !this.isTagOpen;
  }
}
