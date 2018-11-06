package cat.devsofthecoast.teammanagementdemo.feature.activities.login.presenter

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.GetTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.LoginContract
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class LoginPresenter(
        private val appConfig: BaseConfig,
        private val getTrainerUseCase: GetTrainerUseCase)
    : LoginContract.Presenter() {

    override fun logInUser(firebaseAuth: FirebaseAuth) {
        val currentUser = firebaseAuth.currentUser
        isLogged(currentUser)
    }

    private fun isLogged(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            view?.logginSucess(currentUser.uid)
        } else {
            view?.logginFailed()
        }
    }

    override fun firebaseAuthWithGoogle(firebaseAuth: FirebaseAuth, acct: GoogleSignInAccount) {
        //todo in future this should be usecase
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

    override fun logout(googleSignInClient: GoogleSignInClient) {
        FirebaseAuth.getInstance().signOut()
        googleSignInClient.signOut()
    }

    override fun getLoggedTrainer(trainerKey: String) {
        GetTrainerUseCase.Executor(appConfig) {
            useCase = getTrainerUseCase
            onSuccess = {
                view?.loggedTrainerOnSuccess(it)
            }
            onError = {
                view?.loggedTrainerOnError(it)
            }
        }.execute(trainerKey)
    }
}