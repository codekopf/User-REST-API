package com.codekopf.api.application.users;

@SuppressWarnings("WeakerAccess")
public class AuthorityHelper {

    public static final String AUTHORIZED_TO_DO_ANYTHING =
            "(" +
            "T(com.codekopf.api.application.users.Permission).CREATE_USERS," +
            "T(com.codekopf.api.application.users.Permission).READ_USERS," +
            "T(com.codekopf.api.application.users.Permission).UPDATE_USERS," +
            "T(com.codekopf.api.application.users.Permission).DELETE_USERS)";
}
