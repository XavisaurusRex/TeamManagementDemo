package cat.devsofthecoast.teammanagementdemo.feature.weekoverview

import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BaseView

interface WeekOverviewContract {
    interface View : BaseView

    abstract class Presenter : BasePresenter<View>()
}