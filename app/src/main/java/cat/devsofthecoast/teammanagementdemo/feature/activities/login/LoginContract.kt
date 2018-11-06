package cat.devsofthecoast.teammanagementdemo.feature.activities.login

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

interface LoginContract {
    interface View : BaseView {
        fun logginSucess(userUid: String)
        fun logginFailed()

        fun loggedTrainerOnSuccess(trainer: Trainer)
        fun loggedTrainerOnError(throwable: Throwable)

        fun launchGoogleSignInActivity()
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun logInUser(firebaseAuth: FirebaseAuth)
        abstract fun firebaseAuthWithGoogle(firebaseAuth: FirebaseAuth, acct: GoogleSignInAccount)
        abstract fun logout(googleSignInClient: GoogleSignInClient)
        abstract fun getLoggedTrainer(trainerKey: String)
    }
}