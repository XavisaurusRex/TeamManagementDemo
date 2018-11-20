package cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer

interface TrainerProfileContract {
    interface View: BaseView {
        fun onGetLoggedTrainerSuccess(loggedTrainer: Trainer)
        fun onGetLoggedTrainerError(throwable: Throwable)

        fun onGetTeamsSuccess(teams: ArrayList<Team>)
        fun onGetTeamsError(throwable: Throwable)

        fun onUpdateTrainerSuccess()
        fun onUpdateTrainerError(throwable: Throwable)
    }

    abstract class Presenter: BasePresenter<View>() {
        abstract fun getLoggedTrainer()
        abstract fun getAllTeams()
        abstract fun updateTrainer(trainer: Trainer)
    }
}