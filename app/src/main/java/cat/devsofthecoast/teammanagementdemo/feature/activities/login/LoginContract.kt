package cat.devsofthecoast.teammanagementdemo.feature.activities.login

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

interface LoginContract {
    interface View : BaseView {
        fun logginSucess()
        fun logginFailed()

        fun launchGoogleSignInActivity()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun logInUser(firebaseAuth: FirebaseAuth)
        abstract fun firebaseAuthWithGoogle(firebaseAuth: FirebaseAuth, acct: GoogleSignInAccount)
    }
}