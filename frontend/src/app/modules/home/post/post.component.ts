import {Component, Input,  OnInit} from '@angular/core';
import {Post} from "../../../core/models/Post";
import {User} from "../../../core/models/User";
import {FacadeService} from "../../../core/services/facade.service";
import {vote_state_t} from "../../../core/types/vote-state.type";

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {

  @Input('post') post: Post;
  voteState: vote_state_t = 0;
  loggedInUser: User;

  constructor(
    private facadeService: FacadeService,
  ) { }

  ngOnInit(): void {
    this.facadeService.userService.getCurrentUser().subscribe(
      user => {
        this.loggedInUser = user;
        if((user || {}).id && (this.post || {}).id ) {
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

}
