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
  loggedInUser: User = null;
  loaded = true;

  constructor(
    private facadeService: FacadeService,
    private router: Router,
  ) { }

  ngOnInit(): void {
      this.facadeService.userService.getCurrentUser().subscribe(user => {
        this.loggedInUser = user;
      });
  }

  onSubmit(): void {
    if ((this.loggedInUser || {}).id){
      this.loaded = false;
      this.facadeService.questionService.submitQuestion(
        this.loggedInUser.id,
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
