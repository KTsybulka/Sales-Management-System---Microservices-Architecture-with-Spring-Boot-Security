import { Component, OnInit } from '@angular/core';
import { ICustomer, CustomerService } from '../../service/customer.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-customer-data',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './customer-data.component.html',
  styleUrl: './customer-data.component.css'
})
export class CustomerDataComponent implements OnInit {
  customers: ICustomer[] = [];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomers().subscribe((data) => {
      this.customers = data;
      console.log('Customers:', this.customers); // Debugging log
    });
  }
}
