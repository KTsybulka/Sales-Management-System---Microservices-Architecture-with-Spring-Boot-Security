import { Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { CustomerDataComponent } from './component/customer-data/customer-data.component';
import { CustomerFormComponent } from './component/customer-form/customer-form.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'customer-data',
        component: CustomerDataComponent
    },
    {
        path: 'customer-form',
        component: CustomerFormComponent
    },
    {
        path: 'home',
        redirectTo: '',
        pathMatch: 'full'
    },
    {
        path: '**',
        component: PageNotFoundComponent
    },
];
