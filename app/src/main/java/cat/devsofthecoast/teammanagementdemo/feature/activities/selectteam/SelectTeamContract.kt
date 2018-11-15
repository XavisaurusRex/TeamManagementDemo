package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer

interface SelectTeamContract {
    interface View : BaseView {
        fun onGetTeamsSuccess(list: List<Team>)
        fun onGetTeamsError(throwable: Throwable)
        fun onLinkTeamTrainerSuccess()
        fun onLinkTeamTrainerError(it: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getTeams()
        abstract fun associateTeamToTrainer(team: Team, loggedTrainer: Trainer)
    }
}