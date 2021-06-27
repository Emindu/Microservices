import { Component, OnInit } from '@angular/core';
import {FacadeService} from '../../../core/services/facade.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  username: string;
  password: string;
  loaded = true;

  constructor(
    private facadeService: FacadeService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void{
    this.loaded = false;
    this.facadeService.userService.purgeAuth();
    this.facadeService.userService.attemptAuth(this.username, this.password).subscribe(
      success => {
        if (success) {
          this.router.navigateByUrl(`/`).then(r => {
            this.loaded = true;
            console.log('Navigated to', r);
          });
        }else{
          throw new Error('Not success');
        }
      },
      error => {
        this.resetForm();
        console.log('Not Auth!', error);
      }
    );
  }

  isEmpty(): boolean {
    return (this.username || '').length < 1 || (this.password || '').length < 1;
  }

  resetForm(): void {
    this.username = '';
    this.password = '';
  }
}
