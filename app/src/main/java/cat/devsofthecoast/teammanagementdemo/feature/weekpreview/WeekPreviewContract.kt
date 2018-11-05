package cat.devsofthecoast.teammanagementdemo.feature.weekpreview

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView

interface WeekPreviewContract {
    interface View : BaseView {
    }

    abstract class Presenter : BasePresenter<View>() {
    }
}