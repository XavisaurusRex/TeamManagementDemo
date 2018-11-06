package cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.SignupContract

class SignupPresenter(
        private val appConfig: BaseConfig)
    : SignupContract.Presenter() {
}