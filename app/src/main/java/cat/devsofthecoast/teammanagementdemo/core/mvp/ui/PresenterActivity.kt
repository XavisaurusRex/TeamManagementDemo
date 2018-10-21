package cat.devsofthecoast.teammanagementdemo.core.mvp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BaseView

@Suppress("UNCHECKED_CAST")
abstract class PresenterActivity<P : BasePresenter<V>, V : BaseView> :
        AppCompatActivity(), BaseView {

    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.view = this as V
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.view = null
    }

}