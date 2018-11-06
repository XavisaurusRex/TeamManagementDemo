package cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

interface SignupContract {
    interface View : BaseView {
    }

    abstract class Presenter : BasePresenter<View>() {
    }
}