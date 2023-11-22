import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddCarHistoryComponent } from './components/add-car-history/add-car-history.component';
import { TreeSelectModule } from 'primeng/treeselect';
import { TableModule } from 'primeng/table';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [ AddCarHistoryComponent],
  imports: [
    CommonModule,
    TreeSelectModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    TableModule
  ],
  exports: [AddCarHistoryComponent],
})
export class CarManagementModule {}
