import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoadingComponent } from './components/loading/loading.component';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { TopnavComponent } from './components/topnav/topnav.component';
import {RouterModule} from '@angular/router';



@NgModule({
    declarations: [LoadingComponent, TopnavComponent],
    exports: [
        LoadingComponent,
        TopnavComponent,
        CommonModule,
        RouterModule,
    ],
    imports: [
        CommonModule,
        MatProgressSpinnerModule,
        RouterModule,
    ]
})
export class SharedModule { }
