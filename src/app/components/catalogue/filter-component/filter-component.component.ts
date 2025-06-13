import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Filter } from '../../../models/filter';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-filter-component',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './filter-component.component.html',
  styleUrl: './filter-component.component.css'
})
export class FilterComponentComponent {

  filters: Partial<Filter> = {};
  @Input()  filter: Partial<Filter> = {};
  @Output() filtersChanged = new EventEmitter<Partial<Filter>>();

  applyFilters() {
    this.filtersChanged.emit(this.filters);
}
  

}
