package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.controllers.TMDActionBarDrawerToggle

interface HeadContract {
    interface View : BaseView, TMDActionBarDrawerToggle.OnDrawerChange{
    }

    abstract class Presenter : BasePresenter<View>() {
    }
}