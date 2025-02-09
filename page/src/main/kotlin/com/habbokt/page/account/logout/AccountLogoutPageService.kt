package com.habbokt.page.account.logout

import com.google.inject.Inject
import com.google.inject.Singleton
import com.habbokt.page.Html
import com.habbokt.page.None
import com.habbokt.page.PageService
import com.habbokt.page.html
import com.habbokt.session.UserSession
import io.ktor.server.sessions.clear

/**
 * @author Jordan Abraham
 */
@Singleton
class AccountLogoutPageService @Inject constructor(
    private val page: AccountLogoutPage
) : PageService<AccountLogoutPageRequest, Html, None>(
    get = {
        session.clear<UserSession>()
        page.html(this)
    }
)
