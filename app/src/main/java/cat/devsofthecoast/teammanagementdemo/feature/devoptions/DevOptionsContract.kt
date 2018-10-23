package cat.devsofthecoast.teammanagementdemo.feature.devoptions

import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BaseView

interface DevOptionsContract {
    interface View : BaseView {
        fun onDatabaseFilledSuccess()
        fun onDatabaseFilledError(ex: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun fillDatabase()
    }
}