package cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterFragment
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.DailyEntriesContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.adapter.DailyEntriesAdapter
import kotlinx.android.synthetic.main.fragment_dailyentries.*
import kotlinx.android.synthetic.main.fragment_survey.*

class DailyEntriesFragment : PresenterFragment<DailyEntriesContract.Presenter, DailyEntriesContract.View>(), DailyEntriesContract.View {
    override val presenter: DailyEntriesContract.Presenter by lazy {
        (activity?.application as TMDApp).presenterModule.dailyEntriesPresenter
    }

    private val dailyEntriesAdapter: DailyEntriesAdapter by lazy {
        DailyEntriesAdapter(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dailyentries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureInteractions()
        presenter.getDailyEntries(Team())
    }

    private fun configureInteractions() {
        rcyDailyEntries.layoutManager = LinearLayoutManager(activity)
        rcyDailyEntries.adapter = dailyEntriesAdapter
    }

    override fun onGetDailyEntriesSuccess(dailyEntries: List<DailyEntry>) {
        dailyEntriesAdapter.add(dailyEntries)
    }

    override fun onGetDailyEntriesError(throwable: Throwable) {
        activity?.toast("Error -> ${throwable.message}")
    }
}