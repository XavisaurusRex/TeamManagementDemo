package cat.devsofthecoast.teammanagementdemo.feature.teamslist

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView

interface TeamsListContract {
    interface View : BaseView {
    }

    abstract class Presenter : BasePresenter<View>() {
    }
}