import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Toast } from 'bootstrap';
import { Observable } from 'rxjs';

import { Plant } from '../plant';
import { PlantService } from '../plant.service';

@Component({
  selector: 'app-plant-list',
  templateUrl: './plant-list.component.html',
  styleUrls: ['./plant-list.component.css']
})
export class PlantListComponent implements OnInit {

  @ViewChild('myToast',{static:true}) toastEl: any
  isClosed(){
    return !this.toastEl.nativeElement.classList.contains('show')
  }
  toast:any

  title='Add Plant';
  datasaved=false;
  plantForm:FormGroup=this.formbuilder.group({
    plantName:[' ',[Validators.required]],
    plantDesccription:[' ',[Validators.required]],
    plantCost:[' ',[Validators.required]]

  });
  
  plants:Observable<Plant[]> | undefined;
  PlantitoUpdate:boolean=false;
  plant:Plant;

  // plants:Array<Iplant> | undefined;
  constructor(private formbuilder:FormBuilder,private plantService:PlantService) { }

  ngOnInit(): void {

    // this.plantForm

    this.toast=new Toast(this.toastEl.nativeElement,{})
    this.getallPlants();
   
  }
  displayStyle = "none";
  toastMessage = "";
  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.PlantitoUpdate=false;
    this.displayStyle = "none";
    this.title='Add Plant';
  }
  updatePlant(plantDetails:Plant){
    this.title='Update Plant';
this.plant=plantDetails;
console.log(this.plant);
console.log(plantDetails);
    this.plantForm.controls["plantName"].setValue(this.plant.plantName);
    this.plantForm.controls["plantDesccription"].setValue(this.plant.plantDesccription);
    this.plantForm.controls["plantCost"].setValue(this.plant.plantCost);
    this.openPopup()
    this.PlantitoUpdate=true;
  }


  
  getallPlants(){
      this.plants=this.plantService.getAllPlants();
     }
  deleteOption(args:number) {
    let result = confirm("Are you sure you want to delete");
    if (result){
    console.log("deleted"+args);

    this.plantService.deletePlantById(args,2).subscribe(data=>{
      this.toastMessage=data;
      this.getallPlants();
      this.isClosed()?this.toast.show():this.toast.hide();
    })
    }
    else{
      console.log("not deleted"+args);
    }
  }
  

  onFormSubmit(){
    this.datasaved=false;
    let plant=this.plantForm.value;
    this.addOrUpdatePlants(plant);
    this.plantForm.reset();
    this.PlantitoUpdate=false;
  }

  addOrUpdatePlants(plant:Plant){
    if(!this.PlantitoUpdate){
      this.closePopup()
    this.plantService.addPlant(plant,2).subscribe(data=>{
      this.datasaved=true;
      this.getallPlants();
      this.toastMessage="Plant details inserted sucessfully";
      this.isClosed()?this.toast.show():this.toast.hide();
  
    });
  }
  else{
    this.closePopup()
    plant.plantId=this.plant.plantId;
    this.plantService.updatePlant(plant,2).subscribe(data=>{
      this.datasaved=true;
      this.getallPlants();
      this.toastMessage="Plant details updated sucessfully";
      this.isClosed()?this.toast.show():this.toast.hide();
  
    });
  }
}

  
}
