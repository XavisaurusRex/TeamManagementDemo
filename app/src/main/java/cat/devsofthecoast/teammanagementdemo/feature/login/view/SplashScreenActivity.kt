package cat.devsofthecoast.teammanagementdemo.feature.login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.impl.QuestionsRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.feature.login.LoginContract
import cat.devsofthecoast.teammanagementdemo.feature.login.presenter.LoginPresenter
import cat.devsofthecoast.teammanagementdemo.feature.surveyactivity.view.SurveyActivity
import cat.devsofthecoast.teammanagementdemo.feature.weekpreview.view.WeekPreviewActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : PresenterActivity<LoginContract.Presenter, LoginContract.View>(), LoginContract.View {


    private val RC_SIGN_IN: Int = 177
    private val googleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(this.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()!!
    }

    private val googleSignInClient by lazy {
        GoogleSignIn.getClient(this, googleSignInOptions)
    }

    private var firebaseAuth = FirebaseAuth.getInstance()

    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    private val repository: QuestionsRepository by lazy {
        QuestionsRepositoryImpl()
    }

    override val presenter: LoginContract.Presenter by lazy {
        LoginPresenter(appConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        checkAppVersion()

        ivLoadingBall.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate_inifinite))
        presenter.logInUser(firebaseAuth)
    }

    private fun checkAppVersion() {
        if (BuildConfig.VERSION_NAME != getString(R.string.app_version)) {
            throw Exception(getString(R.string.app_error_version))
        }
    }

    override fun launchGoogleSignInActivity() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d(this.javaClass.name, "Google Sign In was successful, authenticating with Firebase...")
                presenter.firebaseAuthWithGoogle(firebaseAuth, account!!)
            } catch (e: ApiException) {
                Log.w(this.javaClass.name, "Google sign in failed", e)
                launchGoogleSignInActivity()
            }
        }
    }

    override fun logginSucess() {
        ivLoadingBall.animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {}

            override fun onAnimationRepeat(p0: Animation?) {
                startActivity(WeekPreviewActivity.newIntent(this@SplashScreenActivity))
            }
        })
    }

    override fun logginFailed() {
        launchGoogleSignInActivity()
    }

}
