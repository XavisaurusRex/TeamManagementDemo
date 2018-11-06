package cat.devsofthecoast.teammanagementdemo.feature.activities.login.view

import android.content.Context
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
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.view.HeadActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.LoginContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.view.SignupTrainerActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_splash_screen.*

class LoginActivity : PresenterActivity<LoginContract.Presenter, LoginContract.View>(), LoginContract.View {

    companion object {
        val LOGOUT_USER = "logout_user"

        fun newIntent(context: Context, relaunchApp: Boolean, signOut: Boolean): Intent {
            val intent = Intent(context, LoginActivity::class.java)
            if (relaunchApp) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

            intent.putExtra(LOGOUT_USER, signOut)
            return intent
        }
    }

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

    override val presenter: LoginContract.Presenter by lazy {
        (application as TMDApp).presenterModule.loginPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        checkAppVersion()
        if (intent != null && intent.hasExtra(LOGOUT_USER) && intent.getBooleanExtra(LOGOUT_USER, false)) {
            presenter.logout(googleSignInClient)
        }

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

    override fun logginSucess(userUid: String) {
        presenter.getLoggedTrainer(userUid)

    }

    override fun logginFailed() {
        launchGoogleSignInActivity()
    }

    override fun loggedTrainerOnSuccess(trainer: Trainer) {
        //TODO CONTINUE HERE
        val validName = trainer.name != null && trainer.surname != null
        val validTeam = trainer.team != null
        if(validName && validTeam){
            allright()
        }
        else {
            startActivity(
                    SignupTrainerActivity.newIntent(this@LoginActivity, trainer)
            )
        }
    }

    fun allright(){
        ivLoadingBall.animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {}

            override fun onAnimationRepeat(p0: Animation?) {
                startActivity(HeadActivity.newIntent(this@LoginActivity))
                finish()
            }
        })
    }

    override fun loggedTrainerOnError(throwable: Throwable) {
        startActivity(
                SignupTrainerActivity.newIntent(this@LoginActivity)
        )
    }
}
