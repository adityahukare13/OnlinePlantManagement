import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CoupanComponent } from './Coupan/coupan/coupan.component';
import { PlantListComponent } from './Plant/plant-list/plant-list.component';

const routes:Routes=[
  {path:'',component:PlantListComponent},
  {path:'view-coupan',component:CoupanComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
