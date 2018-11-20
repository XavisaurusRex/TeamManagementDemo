package cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterFragment
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.TrainerProfileContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.presenter.TrainerProfilePresenter
import kotlinx.android.synthetic.main.fragment_trainer_profile.*

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

        configureInteractions()
        presenter.getAllTeams()
    }

    private fun configureInteractions() {
        tvName.onChangeValue {
            if (it != loggedTrainer?.name) {
                loggedTrainer?.name = it
                presenter.updateTrainer(loggedTrainer!!)
            }
        }

        tvSurname.onChangeValue {
            if (it != loggedTrainer?.surname) {
                loggedTrainer?.surname = it
                presenter.updateTrainer(loggedTrainer!!)
            }
        }
        tvPhoneNumber.onChangeValue {
            if (it != loggedTrainer?.phoneNumber.toString()) {
                loggedTrainer?.phoneNumber = it.toInt()
                presenter.updateTrainer(loggedTrainer!!)
            }
        }

        spnTeam.onChangeValue {
            if (loggedTrainer?.team != it.key) {
                loggedTrainer?.team = it.key
                presenter.updateTrainer(loggedTrainer!!)
            }
        }
    }

    override fun onGetLoggedTrainerSuccess(loggedTrainer: Trainer) {
        this.loggedTrainer = loggedTrainer
        if (spnTeam.selectedItem?.key != loggedTrainer.key) {
            spnTeam.selectTeamByKey(loggedTrainer.team)
        }
    }

    override fun onGetLoggedTrainerError(throwable: Throwable) {
        activity?.toast("onGetLoggedTrainerError -> ${throwable.message}")
    }

    override fun onGetTeamsSuccess(teams: ArrayList<Team>) {
        spnTeam.setSource(teams)
        presenter.getLoggedTrainer()
    }

    override fun onGetTeamsError(throwable: Throwable) {
        activity?.toast("onGetTeamsError -> ${throwable.message}")
    }

    override fun onUpdateTrainerSuccess() {
        activity?.toast("trainer update success")
    }

    override fun onUpdateTrainerError(throwable: Throwable) {
        activity?.toast("Updating trainer error -> ${throwable.message}")
    }
}
