package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView

interface HeadContract {
    interface View : BaseView {
    }

    abstract class Presenter : BasePresenter<View>() {
    }
}