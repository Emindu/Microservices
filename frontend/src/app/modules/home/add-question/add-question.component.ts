import { Component, OnInit } from '@angular/core';
import {FacadeService} from '../../../core/services/facade.service';
import {User} from '../../../core/models/User';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.scss']
})
export class AddQuestionComponent implements OnInit {

  newQuestionTitle: string;
  newQuestionText: string;
  user: User = null;
  isAuthenticated = false;
  loaded = true;

  constructor(
    private facadeService: FacadeService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.facadeService.userService.currentUser.subscribe(user => {
      if ((user || {}).id) {
        this.isAuthenticated = true;
        this.user = user;
      }else{
        this.isAuthenticated = false;
        this.user = null;
      }
    });
  }

  onSubmit(): void {
    if ((this.user || {}).id){
      this.loaded = false;
      this.facadeService.questionService.submitQuestion(
        this.user.id,
        this.newQuestionTitle,
        this.newQuestionText
      ).subscribe(newQuestion => {
        console.log('Submitted', newQuestion);
        this.router.navigateByUrl(`/questions/${newQuestion.id}`).then(r => {
          this.loaded = true;
          console.log('Navigated to', r);
        });
      }, error => {
        console.log(error);
      });
    }
  }

  isQuestionEmpty(): boolean {
    return (
      this.newQuestionTitle == null ||
      this.newQuestionText == null ||
      this.newQuestionTitle.trim().length < 1 ||
      this.newQuestionText.trim().length < 1
    );
  }
}
