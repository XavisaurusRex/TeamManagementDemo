package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView

abstract class PresenterFragment<P : BasePresenter<V>, V : BaseView> :
        Fragment(), BaseView {
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