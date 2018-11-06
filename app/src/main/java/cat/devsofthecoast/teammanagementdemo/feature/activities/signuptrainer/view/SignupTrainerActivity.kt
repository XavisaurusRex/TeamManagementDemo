package cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.SignupContract
import kotlinx.android.synthetic.main.activity_signup_trainer.*

class SignupTrainerActivity : PresenterActivity<SignupContract.Presenter, SignupContract.View>(), SignupContract.View {
    companion object {
        val TRAINER_LOGGED = "logged_trainer"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, SignupTrainerActivity::class.java)
            return intent
        }

        fun newIntent(context: Context, trainer: Trainer): Intent {
            val intent = Intent(context, SignupTrainerActivity::class.java)
            intent.putExtra(TRAINER_LOGGED, trainer)
            return intent
        }
    }

    override val presenter: SignupContract.Presenter by lazy {
        (application as TMDApp).presenterModule.signupPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_trainer)
        var trainer: Trainer? = null
        if (intent.hasExtra(TRAINER_LOGGED)) {
            trainer = intent.getParcelableExtra<Trainer>(TRAINER_LOGGED)
        }

        if (trainer != null) {
            tvParcelgetter.text = "name ${trainer.name} \n surname ${trainer.surname}"
        } else {
            tvParcelgetter.text = "NO TRAINER AVAILABLE"
        }
    }

}
