import { Component } from '@angular/core';
import { Form, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.css'
})
export class CustomerFormComponent {
  feedbackForm : FormGroup;
  submittedData: any | null = null;
  apiCustomerUrl = 'http://localhost:8081/addCustomer';

  constructor(private http: HttpClient, private formBuilder: FormBuilder){
    this.feedbackForm = this.formBuilder.group({
      customerName: ['', [Validators.required]],
      customerPassword: [ '', [Validators.required]],
      active: [ true ],
      role: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.feedbackForm.valid) {
      this.submittedData = this.feedbackForm.value;

      this.http.post(this.apiCustomerUrl, this.feedbackForm.value).subscribe(
        (response) => {
          console.log('Form submitted', response);
          alert('Form submitted successfully');
        },
        (error) => {
          console.error('Error in form submission', error);
          alert('Error in form submission');
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }
}

