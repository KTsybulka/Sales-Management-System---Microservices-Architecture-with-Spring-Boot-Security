import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export interface ICustomer {
  customerId: number;
  customerName: string;
  customerPassword: string;
  active: boolean;
  role: string;
  createdAt: string;
}


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private apiUrlCustomers = 'http://localhost:8081/customers';
  constructor(private http:HttpClient) { }

  getCustomers() {
    return this.http.get<ICustomer[]>(this.apiUrlCustomers);
  }
}
