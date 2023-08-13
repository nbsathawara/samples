import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../model/course';
import {
  debounceTime,
  distinctUntilChanged,
  startWith,
  tap,
  delay,
  map,
  concatMap,
  switchMap,
  withLatestFrom,
  concatAll, shareReplay
} from 'rxjs/operators';
import { merge, fromEvent, Observable, concat } from 'rxjs';
import { Lesson, sortLessonBySeqNo } from '../model/lesson';
import { CoursesService } from '../services/courses.service';


@Component({
  selector: 'course',
  templateUrl: './search-lessons.component.html',
  styleUrls: ['./search-lessons.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class SearchLessonsComponent implements OnInit {

  searchResults$: Observable<Lesson[]>
  activeLession: Lesson

  constructor(private courseService: CoursesService) {


  }

  ngOnInit() {


  }

  onSearch(search: string) {
    this.searchResults$ = this.courseService.searchLessons(search)
  }

  openLession(lession: Lesson) {
    this.activeLession = lession
  }

  onBackToSearch() {
    this.activeLession = null
  }

}











