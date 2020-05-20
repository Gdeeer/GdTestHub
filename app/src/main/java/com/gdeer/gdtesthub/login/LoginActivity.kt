package com.gdeer.gdtesthub.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.gdeer.gdtesthub.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val RC_GOOGLE_SIGN_IN = 9001
    }

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initFaceBookLogin()

        tv_login_google.setOnClickListener {
            googleSignIn()
        }

        tv_logout_google.setOnClickListener {
            googleSignOut()
        }

        tv_login_facebook.setOnClickListener {
            facebookSignIn()
        }
    }

    private fun initFaceBookLogin() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                showTs("facebook success")
                getFbLoginInfo(loginResult.accessToken)
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onCancel() {
                showTs("facebook cancel")
                // 网上文章里看到的，官方文档里没有，先注掉
//                CookieManager.getInstance().apply {
//                    removeAllCookies(null)
//                    flush()
//                }
            }

            override fun onError(error: FacebookException) {
                showTs("facebook error " + error.message)
            }
        })
    }

    private fun getFbLoginInfo(accessToken: AccessToken?) {
        val request = GraphRequest.newMeRequest(accessToken) { `object`, _ ->
            if (`object` != null) {
                val id = `object`.optString("id")
                val name = `object`.optString("name")
                val gender = `object`.optString("gender") //性别
                val email = `object`.optString("email")
                //获取用户头像
                val objectPic: JSONObject = `object`.optJSONObject("picture")
                val objectData: JSONObject = objectPic.optJSONObject("data")
                val photo: String = objectData.optString("url")
                //获取地域信息
                val locale = `object`.optString("locale") //zh_CN 代表中文简体
                Log.d("zhangjl", "fb profile---id---$id---name----$name--gender---$gender----email---$email---photo---$photo----locale---$locale")
            }
        }

        val parameters = Bundle()
        parameters.putString("fields", "id,name,link,gender,birthday,email,picture,locale,updated_time,timezone,age_range,first_name,last_name")
        request.parameters = parameters
        request.executeAsync()
    }

    private fun facebookSignIn() {
        //判断当前token，如果不为空，则已经获取过权限，否则读取权限走registerCallback回调
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (!isLoggedIn) {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile, email"))
        } else {
            getFbLoginInfo(accessToken)
        }
    }

    private fun googleSignIn() {
        // [configure_signin]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build()

        // [build_client]
        // Build a GoogleSignInClient with the options specified by gso.
        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInIntent = googleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN)
    }

    private fun googleSignOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build()
        // 可以与 signIn 用同一个 googleSignInClient，也可以不同
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient!!.signOut()
                .addOnCompleteListener(this) {
                    Toast.makeText(this, "sign out", Toast.LENGTH_SHORT).show()
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.w("zhangjl", "success " + account?.zac())
            showTs("google success")
            // Signed in successfully, show authenticated UI.
            // updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            // updateUI(null)
            val errorMsg = e.statusCode.toString() + " " + GoogleSignInStatusCodes.getStatusCodeString(e.statusCode)
            Log.w("zhangjl", "signInResult:failed code=$errorMsg")
            showTs("google fail: $errorMsg")
        }
    }

    private fun showTs(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
