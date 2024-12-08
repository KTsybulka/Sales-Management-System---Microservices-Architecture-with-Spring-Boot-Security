import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navigate',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navigate.component.html',
  styleUrl: './navigate.component.css'
})
export class NavigateComponent {

}
