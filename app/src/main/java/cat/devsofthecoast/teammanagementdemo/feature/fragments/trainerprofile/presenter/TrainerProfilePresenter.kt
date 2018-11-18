package cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.TrainerProfileContract

class TrainerProfilePresenter(
        private val appConfig: BaseConfig)
    : TrainerProfileContract.Presenter() {

}