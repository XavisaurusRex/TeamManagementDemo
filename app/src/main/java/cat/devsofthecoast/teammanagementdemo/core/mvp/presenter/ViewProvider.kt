package cat.devsofthecoast.teammanagementdemo.core.mvp.presenter

interface ViewProvider<V : BaseView?> {
    var view: V?
}