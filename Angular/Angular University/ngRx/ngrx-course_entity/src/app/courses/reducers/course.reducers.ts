import { EntityState, createEntityAdapter } from "@ngrx/entity";
import { Course, compareCourses } from "../model/course";
import { createReducer, on } from "@ngrx/store";
import { CourseActions } from "../action-types";

export interface CourseState extends EntityState<Course> {
    allCoursesLoaded: boolean
}

export const adapter = createEntityAdapter<Course>({
    sortComparer: compareCourses
})

export const initialcourseState = adapter.getInitialState({
    allCoursesLoaded: false
})

export const courseReducer = createReducer(
    initialcourseState,
    on(CourseActions.allCoursesLoaded
        , (state, action) => adapter.addMany(action.courses, { ...state, allCoursesLoaded: true })),
    on(CourseActions.courseUpdated
        , (state, action) => adapter.updateOne(action.update, state))
)

export const { selectAll } = adapter.getSelectors()