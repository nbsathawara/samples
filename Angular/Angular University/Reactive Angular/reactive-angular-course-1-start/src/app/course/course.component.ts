import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../model/course';
import { merge, fromEvent, Observable, concat, throwError, combineLatest } from 'rxjs';
import { Lesson } from '../model/lesson';
import { CoursesService } from '../services/courses.service';
import { map, startWith, tap } from 'rxjs/operators';

interface CourseData {
  course: Course;
  courseLessons: Lesson[]
}

@Component({
  selector: 'course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CourseComponent implements OnInit {

  data$: Observable<CourseData>;
  activeLesson: Lesson

  constructor(private route: ActivatedRoute, private courseService: CoursesService) {


  }

  ngOnInit() {

    const courseId = parseInt(this.route.snapshot.paramMap.get('courseId'))

    const course$ = this.courseService.loadCourseById(courseId).pipe(
      startWith(null)
    )

    const lessons$ = this.courseService.loadAllCourseLessions(courseId).pipe(
      startWith([])
    )

    this.data$ = combineLatest([course$, lessons$]).pipe(
      map(([course, lessons]) => {
        return { course, lessons }
      }),
      tap(console.log)
    )

  }

  onLessonClicked(lesson: Lesson) { 
    this.activeLesson = lesson
  }

  onBackToLessons() {
    this.activeLesson = null
  }


}











