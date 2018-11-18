package cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView

interface TrainerProfileContract {
    interface View: BaseView {

    }

    abstract class Presenter: BasePresenter<View>() {

    }
}