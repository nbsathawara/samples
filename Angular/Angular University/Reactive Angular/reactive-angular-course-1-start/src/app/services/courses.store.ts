import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { Course, sortCoursesBySeqNo } from "../model/course";
import { catchError, map, shareReplay, tap } from "rxjs/operators";
import { HttpClient } from "@angular/common/http";
import { LoadingService } from "../loading/loading.service";
import { MessageService } from "../messages/message.service";

@Injectable({ providedIn: 'root' })
export class CoursesStore {

    private subject = new BehaviorSubject<Course[]>([])
    courses$: Observable<Course[]> = this.subject.asObservable()


    constructor(private http: HttpClient, private loading: LoadingService, private msgService: MessageService) {
        this.loadAllCourses()
    }

    private loadAllCourses() {
        const loadCourse$ = this.http.get<Course[]>('api/courses').pipe(
            map(res => res['payload']),
            catchError(err => {
                const msg = 'Unable to load courses!!'
                this.msgService.showErrors(msg)
                console.log(msg, err)
                return throwError(err)
            }),
            tap(courses => this.subject.next(courses))
        )

        this.loading.showLoaderUntillCompleted(loadCourse$).subscribe()
    }

    filterByCategory(category: string): Observable<Course[]> {
        return this.courses$
            .pipe(
                map(courses => courses.filter(course => course.category == category)
                    .sort(sortCoursesBySeqNo)
                )
            )
    }

    editCourse(courseId: string, changes: Partial<Course>): Observable<any> {

        const courses = this.subject.getValue()
        const index = courses.findIndex(course => course.id == courseId)
        const newCourse: Course = {
            ...courses[index],
            ...changes
        }

        const newCourses = courses.slice(0)
        newCourses[index] = newCourse
        this.subject.next(newCourses)

        return this.http.put(`api/courses/${courseId}`, changes).pipe(
            catchError(err => {
                const msg = 'Unable to edit course. Please try again later.'
                this.msgService.showErrors(msg)
                console.log(msg, err)
                return throwError(err)
            }),
            shareReplay()
        )


    }

}