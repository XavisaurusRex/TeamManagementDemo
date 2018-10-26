package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter

interface ViewProvider<V : BaseView?> {
    var view: V?
}