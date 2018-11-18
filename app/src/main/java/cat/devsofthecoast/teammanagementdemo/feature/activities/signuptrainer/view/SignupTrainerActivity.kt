package cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.view.HeadActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.view.LoginActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.view.SelectTeamActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.SignupContract
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup_trainer.*
import java.util.regex.Pattern

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

    var trainer: Trainer? = null

    var numericPattern = Pattern.compile("[0-9]{9}+")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_trainer)
        if (intent.hasExtra(TRAINER_LOGGED)) {
            trainer = intent.getParcelableExtra(TRAINER_LOGGED)
        } else {
            trainer = Trainer()
            trainer?.key = FirebaseAuth.getInstance().currentUser?.uid
            trainer?.email = FirebaseAuth.getInstance().currentUser?.email
        }

        configureInteractions()
        initializeLayout()
    }

    private fun configureInteractions() {
        ibUploadPicture.setOnClickListener {
            // TODO request image to profile picture
        }

        btnCancel.setOnClickListener {
            startActivity(
                    LoginActivity.newIntent(this, true, true)
            )
        }

        btnNext.setOnClickListener {
            registerUpdateTrainer()
        }
    }

    private fun registerUpdateTrainer() {
        if (checkCorrectValues()) {
            trainer?.name = tvName.text.toString()
            trainer?.surname = tvSurname.text.toString()
            trainer?.phoneNumber = tvPhoneNumber.text.toString().toInt()
            presenter.createUpdateTrainer(trainer!!)
        }
    }

    private fun checkCorrectValues(): Boolean {
        val correctName = tvName.text != null && tvName.text.isNotEmpty()
        val correctSurname = tvSurname.text != null && tvSurname.text.isNotEmpty()
        val correctPhoneNumber = tvPhoneNumber.text != null && tvPhoneNumber.text.isNotEmpty() && numericPattern.matcher(tvPhoneNumber.text).matches()

        if (!correctName) {
            tvName.error = "incorrect name"
        } else {
            tvName.error = null
        }

        if (!correctSurname) {
            tvSurname.error = "incorrect surname"
        } else {
            tvSurname.error = null
        }

        if (!correctPhoneNumber) {
            tvPhoneNumber.error = "incorrect phone number"
        } else {
            tvPhoneNumber.error = null
        }
        return correctName && correctSurname && correctPhoneNumber
    }

    private fun initializeLayout() {
        tvName.setText(trainer?.name)
        tvSurname.setText(trainer?.surname)
        if(trainer?.phoneNumber != null) tvPhoneNumber.setText(trainer?.phoneNumber.toString())
        if (trainer?.picture_url != null) {
            Glide.with(this).load(trainer?.picture_url).into(ivPicture)
        }
    }

    override fun onUpdateCreatePlayerSuccess() {
        startActivity(
                SelectTeamActivity.newIntent(this, trainer!!)
        )
    }

    override fun onUpdateCreatePlayerError(throwable: Throwable) {
        toast("an error has ocurred ${throwable.message}")
    }
}
