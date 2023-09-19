package com.busymodernpeople.core.common.base

object AuthDestinations {
    const val ROUTE = "authRoute"

    object Login {
        const val ROUTE = "loginRoute"

        const val LOGIN = "login"

        const val EMAIL_LOGIN = "emailLogin"
    }

    object FindPassword {
        const val ROUTE = "findPasswordRoute"
        const val EMAIL = "findPasswordEmail"
        const val RESET_PASSWORD = "findPasswordResetPassword"
        const val COMPLETE = "findPasswordComplete"
    }

    object Join {
        const val ROUTE = "joinRoute"

        const val AGREE = "joinAgree"
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