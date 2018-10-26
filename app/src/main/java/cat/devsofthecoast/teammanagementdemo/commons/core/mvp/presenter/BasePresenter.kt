package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter

abstract class BasePresenter<V : BaseView> {
    var view: V? = null
}