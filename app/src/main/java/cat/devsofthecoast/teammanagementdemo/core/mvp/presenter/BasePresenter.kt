package cat.devsofthecoast.teammanagementdemo.core.mvp.presenter

abstract class BasePresenter<V : BaseView> {
    var view: V? = null
}