package com.busymodernpeople.core.common.base

object AuthDestinations {
    const val ROUTE = "authRoute"

    object Login {
        const val ROUTE = "loginRoute"

        const val LOGIN = "login"

        const val EMAIL_LOGIN = "emailLogin"
    }

    object ResetPassword {
        const val ROUTE = "resetPasswordRoute"
        const val EMAIL = "resetPasswordEmail"
        const val COMPLETE = "resetPasswordComplete"
    }

    object Join {
        const val ROUTE = "joinRoute"

        const val TERMS = "joinTerms"
        const val EMAIL = "joinEmail"
        const val PASSWORD = "joinPassword"
        const val NICKNAME = "joinNickname"
        const val COMPLETE = "joinComplete"
    }
}

object HomeDestinations {
    const val ROUTE = "homeRoute"

    const val HOME = "home"
}

object DiaryDestinations {
    const val ROUTE = "diaryRoute"

    const val DIARY = "diary"
}

object CommunityDestinations {
    const val ROUTE = "communityRoute"

    const val COMMUNITY = "community"
}