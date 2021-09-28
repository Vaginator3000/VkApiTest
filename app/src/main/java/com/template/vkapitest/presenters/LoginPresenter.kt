package com.template.vkapitest.presenters

import android.content.Intent
import android.os.Handler
import com.template.vkapitest.R
import com.template.vkapitest.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {
    fun loginTest(isSuccess: Boolean) {
        viewState.startLoading()

        Handler().postDelayed({
            viewState.endLoading()
            if (isSuccess) {
                viewState.openFriends()
            }
            else  {
                viewState.showError(txtRes = R.string.login_error_toast)
            }
        },2000)
    }

    fun login(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                viewState.openFriends()
            }

            override fun onLoginFailed(errorCode: Int) {
                viewState.showError(txtRes = R.string.login_error_toast)
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            return false
        }
        return true

    }
}