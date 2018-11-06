package cat.devsofthecoast.teammanagementdemo.feature.activities.login.presenter

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.LoginContract
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginPresenter(
        private val appConfig: BaseConfig)
    : LoginContract.Presenter() {

    override fun logInUser(firebaseAuth: FirebaseAuth) {
        val currentUser = firebaseAuth.currentUser
        isLogged(currentUser)
    }

    private fun isLogged(currentUser: FirebaseUser?) {
        //hideProgressDialog()
        if (currentUser != null) {
            view?.logginSucess()
        } else {
            view?.logginFailed()
        }
    }

    override fun firebaseAuthWithGoogle(firebaseAuth: FirebaseAuth, acct: GoogleSignInAccount) {
        Log.d(this.javaClass.name, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(this.javaClass.name, "signInWithCredential:success")
                        isLogged(firebaseAuth.currentUser)
                    } else {
                        Log.w(this.javaClass.name, "signInWithCredential:failure", task.exception)
                        isLogged(null)
                    }
                }
    }
}