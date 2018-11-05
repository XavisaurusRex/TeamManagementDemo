package cat.devsofthecoast.teammanagementdemo.feature.weekpreview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.weekpreview.WeekPreviewContract


class WeekPreviewActivity : PresenterActivity<WeekPreviewContract.Presenter, WeekPreviewContract.View>(), WeekPreviewContract.View {
    override val presenter: WeekPreviewContract.Presenter by lazy {
        (application as TMDApp).presenterModule.weekpreviewPresenter
    }


    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WeekPreviewActivity::class.java)
        }
    }

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_preview)
    }
}
