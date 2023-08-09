import { AfterViewInit, ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../model/course';
import { Observable, of } from 'rxjs';
import { Lesson } from '../model/lesson';
import { concatMap, delay, filter, first, map, shareReplay, tap, withLatestFrom } from 'rxjs/operators';
import { CourseEntityService } from '../services/course-entity.service';
import { LessonEntityService } from '../services/lession-entity.service';


@Component({
  selector: 'course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CourseComponent implements OnInit {

  course$: Observable<Course>;

  lessons$: Observable<Lesson[]>;

  loading$: Observable<boolean>

  displayedColumns = ['seqNo', 'description', 'duration'];

  nextPage = 0;

  constructor(
    private courseService: CourseEntityService,
    private lessonService: LessonEntityService,
    private route: ActivatedRoute) {

  }

  ngOnInit() {

    const courseUrl = this.route.snapshot.paramMap.get("courseUrl");

    this.course$ = this.courseService.entities$.pipe(
      map(courses => courses.find(course => course.url == courseUrl))
    )

    this.lessons$ = this.lessonService.entities$
      .pipe(
        withLatestFrom(this.course$),
        tap(([lessions, course]) => {
          if (this.nextPage == 0) {
            this.loadLessonsPage(course)
          }
        }
        ),
        map(([lessions, course]) => lessions.filter(lession => lession.courseId == course.id))
      )

    this.loading$ = this.lessonService.loading$.pipe(delay(0))
  }


  loadLessonsPage(course: Course) {
    this.lessonService.getWithQuery({
      'courseId': course.id.toString(),
      'pageNumber': this.nextPage.toString(),
      'pageSize': '3'
    })
    this.nextPage += 1;
  }

}
