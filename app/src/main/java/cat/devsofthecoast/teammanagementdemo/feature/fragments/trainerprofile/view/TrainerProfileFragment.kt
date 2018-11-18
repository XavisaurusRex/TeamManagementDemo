package cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.controllers.othercontroller.TMDTextWatcher
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterFragment
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.TrainerProfileContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.presenter.TrainerProfilePresenter
import kotlinx.android.synthetic.main.fragment_trainer_profile.*
import java.util.concurrent.TimeUnit

class TrainerProfileFragment : PresenterFragment<TrainerProfilePresenter, TrainerProfileContract.View>(), TrainerProfileContract.View {
    override val presenter: TrainerProfilePresenter by lazy {
        (activity!!.application as TMDApp).presenterModule.trainerProfilePresenter
    }

    var loggedTrainer: Trainer? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_trainer_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArguents()
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LOGGED_TRAINER)) {
                loggedTrainer = savedInstanceState.getParcelable(LOGGED_TRAINER)
            }
        }

        configureInteractions()
    }

    private fun getArguents() {
        if (arguments != null) {
            val bundle: Bundle = arguments as Bundle
            if (bundle.containsKey(LOGGED_TRAINER)) {
                loggedTrainer = bundle.getParcelable(LOGGED_TRAINER)
            }
        }
    }

    private fun configureInteractions() {
        tvName.addTextChangedListener(
                TMDTextWatcher(
                        TimeUnit.SECONDS.toMillis(3),
                        object : TMDTextWatcher.TextWatcherListener {
                            override fun onTextChange(text: String) {
                                if (text != loggedTrainer?.name) {
                                    tvSurname.setText(text)
                                }
                            }
                        })
        )
    }

    companion object {
        private val LOGGED_TRAINER = "loggedTrainer"

        fun newInstance(trainer: Trainer): TrainerProfileFragment {
            val trainerProfileFragment = TrainerProfileFragment()
            val bundle = Bundle()
            bundle.putParcelable(LOGGED_TRAINER, trainer)
            trainerProfileFragment.arguments = bundle
            return trainerProfileFragment
        }
    }

}
