package cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.view

import android.os.Bundle
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.WeekPreviewContract
import kotlinx.android.synthetic.main.activity_header.*

class WeekPreviewFragment: PresenterFragment<WeekPreviewContract.Presenter, WeekPreviewContract.View>(), WeekPreviewContract.View {
    override val presenter: WeekPreviewContract.Presenter by lazy {
        (activity!!.application as TMDApp).presenterModule.weekpreviewPresenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_week_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureInteractions()
    }

    private fun configureInteractions() {

    }
}