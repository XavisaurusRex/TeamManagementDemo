package cat.devsofthecoast.teammanagementdemo.feature.teamslist

import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team

interface TeamsListContract {
    interface View : BaseView {
        fun showTeams(teamsResult: List<Team>)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getTeams()
    }
}