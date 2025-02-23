package com.habbokt.page.index

import com.google.inject.Inject
import com.google.inject.Singleton
import com.habbokt.page.PageRouting
import com.habbokt.page.respondHtml
import com.habbokt.session.UserSession
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.sessions.get
import io.ktor.server.sessions.sessions

/**
 * <b>Information</b>
 *
 * ```
 * GET {host}/
 * QUERY page
 * QUERY rememberme
 * QUERY username
 * ```
 *
 * <b>Example</b>
 *
 * ```
 * http://localhost/?page=submit&username=jordan&rememberme=true
 * ```
 *
 * @author Jordan Abraham
 *
 * @see IndexPage
 * @see IndexPageRequest
 * @see IndexPageService
 *
 * @constructor Injects the associated [service] for handling the request set by the [routing].
 *
 * @property routing The routing for this page. "/".
 */
@Singleton
class IndexPageRouting @Inject constructor(
    private val service: IndexPageService
) : PageRouting(
    routing = {
        get("/") {
            val userSession = call.sessions.get<UserSession>()
            if (userSession != null && userSession.authenticated) {
                return@get call.respondRedirect("/me")
            }

            val rememberMe = call.request.queryParameters["rememberme"].toBoolean()
            val username = call.request.queryParameters["username"]

            val request = IndexPageRequest(
                rememberMe = rememberMe,
                username = username
            )
            call.respondHtml(HttpStatusCode.OK, service.getRequest(request))
        }
    }
)