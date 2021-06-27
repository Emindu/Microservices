import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {User} from '../../../core/models/User';
import {FacadeService} from '../../../core/services/facade.service';

@Component({
  selector: 'app-topnav',
  templateUrl: './topnav.component.html',
  styleUrls: ['./topnav.component.scss']
})
export class TopnavComponent implements OnInit {

  user: User = null;
  isAuthenticated = false;

  constructor(
    private facadeService: FacadeService,
    private changeDetectRef: ChangeDetectorRef
  ) { }

  ngOnInit(): void {

    this.facadeService.userService.getCurrentUser().subscribe(user => {
      if ((user || {}).id) {
        this.isAuthenticated = true;
        this.user = user;
      }else{
        this.isAuthenticated = false;
        this.user = null;
      }
      this.changeDetectRef.markForCheck();
    });

  }

  signOut(): void {
      this.facadeService.userService.purgeAuth();
      this.isAuthenticated = false;
      this.user = null;
      this.changeDetectRef.markForCheck();
  }

}
