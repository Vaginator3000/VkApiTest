package com.template.vkapitest.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.template.vkapitest.R
import com.template.vkapitest.presenters.LoginPresenter
import com.template.vkapitest.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import com.vk.api.sdk.utils.VKUtils
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login.setOnClickListener {
        //    loginPresenter.login(isSuccess = true)
            VK.login(this, arrayListOf(VKScope.FRIENDS))
        }

        // val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)
        // Log.e("MyLog", "---------- ${fingerprints?.get(0)}")
        // 51635EEEC576AA065370A489CB2B06FFEBFC1381
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!loginPresenter.login(requestCode = requestCode, resultCode = resultCode, data = data))
            super.onActivityResult(requestCode, resultCode, data)

    }

    override fun startLoading() {
        btn_login.visibility = View.GONE
        cpv_login.visibility = View.VISIBLE
    }

    override fun endLoading() {
        btn_login.visibility = View.VISIBLE
        cpv_login.visibility = View.GONE
    }

    override fun openFriends() {
        startActivity(Intent(this, FriendsActivity::class.java))
    }

    override fun showError(txtRes: Int) {
        Toast.makeText(this, getString(txtRes), Toast.LENGTH_SHORT).show()
    }
}