import { createAction, props } from "@ngrx/store";
import { User } from "./model/user.model";

export const logIn = createAction(
    "[Login Page] User Login",
    props<{ user: User }>()
)

export const logOut=createAction(
    "[Side Menu] LogOut"
)

