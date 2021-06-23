import { Component, OnInit } from '@angular/core';
import {User} from "../../../core/models/User";
import {FacadeService} from "../../../core/services/facade.service";

@Component({
  selector: 'app-topnav',
  templateUrl: './topnav.component.html',
  styleUrls: ['./topnav.component.scss']
})
export class TopnavComponent implements OnInit {

  user: User = null;

  constructor(private facadeService: FacadeService) { }

  ngOnInit(): void {
    this.facadeService.userService.getCurrentUser().subscribe(user => {
      this.user = user;
    });
  }

}
