package cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView


abstract class PresenterActivity<P : BasePresenter<V>, V : BaseView> :
        AppCompatActivity(), BaseView {

    protected abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.view = this as V
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.view = null
    }
}