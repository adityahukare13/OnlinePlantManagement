import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Toast } from 'bootstrap';
import { Observable } from 'rxjs';
import { Coupan } from '../coupan';
import { CoupanService } from '../coupan.service';

@Component({
  selector: 'app-coupan',
  templateUrl: './coupan.component.html',
  styleUrls: ['./coupan.component.css']
})
export class CoupanComponent implements OnInit {

  @ViewChild('myToast',{static:true}) toastEl: any
  isClosed(){
    return !this.toastEl.nativeElement.classList.contains('show')
  }
  toast:any

  title='Add Coupan';
  datasaved=false;
  coupanForm:FormGroup=this.formbuilder.group({
    coupanName:[' ',[Validators.required]],
    coupanType:[' ',[Validators.required]],
    coupanDescription:[' ',[Validators.required]],
    couponValue:[' ',[Validators.required]]


  });
  
  coupans:Observable<Coupan[]> | undefined;
  
  coupan:Coupan;

  
  constructor(private formbuilder:FormBuilder,private coupanService:CoupanService) { }

  ngOnInit(): void {

    // this.coupanForm

    this.toast=new Toast(this.toastEl.nativeElement,{})
    this.getallCoupans();
   
  }
  displayStyle = "none";
  toastMessage = "";
  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    
    this.displayStyle = "none";
    this.title='Add Coupan';
  }
  


  
  getallCoupans(){
      this.coupans=this.coupanService.getAllCoupans();
     }
  deleteOption(args:number) {
    let result = confirm("Are you sure you want to delete");
    if (result){
    console.log("deleted"+args);

    this.coupanService.deleteCoupanById(args).subscribe(data=>{
      this.toastMessage=data;
      this.getallCoupans();
      this.isClosed()?this.toast.show():this.toast.hide();
    })
    }
    else{
      console.log("not deleted"+args);
    }
  }
  

  onFormSubmit(){
    this.datasaved=false;
    let coupan=this.coupanForm.value;
    this.addCoupan(coupan);
    this.coupanForm.reset();
    
  }

  addCoupan(coupan:Coupan){
    
      this.closePopup()
    this.coupanService.addCoupan(coupan,2).subscribe(data=>{
      this.datasaved=true;
      this.getallCoupans();
      this.toastMessage="Coupan details inserted sucessfully";
      this.isClosed()?this.toast.show():this.toast.hide();
  
    });
  
  
}

  
}
