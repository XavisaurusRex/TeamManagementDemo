package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.controllers.TMDActionBarDrawerToggle

interface HeadContract {
    interface View : BaseView, TMDActionBarDrawerToggle.OnDrawerChange {
        fun onGetTeamSuccess(team: Team)
        fun onGetTeamError(throwable: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getTeam(teamKey: String)
    }
}