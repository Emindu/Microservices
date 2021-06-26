import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {Post} from '../../../core/models/Post';
import {User} from '../../../core/models/User';
import {FacadeService} from '../../../core/services/facade.service';
import {vote_state_t} from '../../../core/types/vote-state.type';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  @Input() post: Post;
  voteState: vote_state_t = 0;
  loggedInUser: User;
  loaded = true;

  constructor(
    private facadeService: FacadeService,
    private router: Router,
    private changeDetectRef: ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.facadeService.userService.getCurrentUser().subscribe(
      user => {
        this.loggedInUser = user;
        if ((user || {}).id && (this.post || {}).id ) {
          this.facadeService.voteService.getPostVoteStateOfPostForUser(this.post, user.id).subscribe(
            voteState => {
              this.voteState = voteState;
            },
            error => {
              console.log(error);
            }
          );
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  navigateToSignIn(): void {
    this.router.navigateByUrl(`/auth/signIn`).then(r => {
      console.log('Navigated to', r);
    });
  }

  setVote(oldVote: vote_state_t, vote: vote_state_t): void {
    this.facadeService.voteService.setPostVoteStateOfPostForUser(this.post, this.loggedInUser.id, vote).subscribe(
      result => {
        console.log(result);
        this.voteState = result;
        this.post.votesCount = this.post.votesCount + (vote - oldVote);
        this.changeDetectRef.markForCheck();
        this.loaded = true;
      },
      error => {
        console.log(error);
        this.loaded = true;
      }
    );
  }

  onClickUpVote(): void {
    if (!this.loaded) {
      return;
    }
    if (!this.loggedInUser) {
      this.navigateToSignIn();
    }else{
      this.loaded = false;
      if (this.voteState !== 1) {
        this.setVote(this.voteState, 1);
      }else{
        this.setVote(this.voteState, 0);
      }
    }
  }

  onClickDownVote(): void {
    if (!this.loaded) {
      return;
    }
    if (!this.loggedInUser) {
      this.navigateToSignIn();
    }else{
      this.loaded = false;
      if (this.voteState !== -1) {
        this.setVote(this.voteState, -1);
      }else{
        this.setVote(this.voteState, 0);
      }
    }
  }
}
