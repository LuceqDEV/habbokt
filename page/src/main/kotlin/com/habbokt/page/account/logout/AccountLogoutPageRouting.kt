package com.habbokt.page.account.logout

import com.google.inject.Inject
import com.google.inject.Singleton
import com.habbokt.page.Authentications
import com.habbokt.page.PageRouting
import com.habbokt.page.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.get
import io.ktor.server.sessions.sessions

/**
 * @author Jordan Abraham
 */
@Singleton
class AccountLogoutPageRouting @Inject constructor(
    private val service: AccountLogoutPageService
) : PageRouting(
    routing = {
        authenticate(Authentications.User) {
            get("/account/logout") {
                val request = AccountLogoutPageRequest(
                    session = call.sessions
                )
                call.respondHtml(HttpStatusCode.OK, service.getRequest(request))
            }
        }
    }
)