package cat.devsofthecoast.teammanagementdemo.feature.teamslist.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TMDRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.TMDService
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team
import cat.devsofthecoast.teammanagementdemo.feature.commons.useCase.RequestTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.TeamsListContract
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.presenter.TeamsListPresenter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


class TeamsListActivity : PresenterActivity<TeamsListContract.Presenter, TeamsListContract.View>(), TeamsListContract.View {

    var countEasterEggs: Int = 0
    val EASTER_EGG_WINCOUNT = 7

    val easterListener: View.OnClickListener = View.OnClickListener {
        if (!(it.tag as Boolean)) {
            it.setBackgroundColor(ContextCompat.getColor(this@TeamsListActivity, R.color.colorEasterEgg_on))
            it.tag = true
            udpateCountEasterEgg(true)
        } else {
            it.setBackgroundColor(ContextCompat.getColor(this@TeamsListActivity, R.color.colorEasterEgg_off))
            it.tag = false
            udpateCountEasterEgg(false)
        }
    }

    companion object {

        private val INTENT_USER_ID = "user_id"

        fun newIntent(context: Context): Intent {
            val intent = Intent(context, TeamsListActivity::class.java)
            //you can put extras here
            return intent
        }
    }

    private val service: TMDService by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.TEAMMANAGEMENT_API_BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerKotlinModule()))
                .build()
        retrofit.create<TMDService>(TMDService::class.java)
    }

    override val presenter: TeamsListContract.Presenter by lazy {
        TeamsListPresenter(appConfig, requestTeamsUseCase)
    }

    private val repository: TMDRepository by lazy {
        TMDRepositoryImpl(service)
    }

    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    private val requestTeamsUseCase: RequestTeamsUseCase by lazy {
        RequestTeamsUseCase(appConfig, repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.mainActivityTitle)

        createEasterEgg()
    }

    private fun createEasterEgg() {
        vLeftTop.tag = false
        vRightTop.tag = false
        vLeftBottom.tag = false
        vRightBottom.tag = false
        v05.tag = false
        v06.tag = false
        v07.tag = false

        vLeftTop.setOnClickListener(easterListener)
        vRightTop.setOnClickListener(easterListener)
        vLeftBottom.setOnClickListener(easterListener)
        vRightBottom.setOnClickListener(easterListener)
        v05.setOnClickListener(easterListener)
        v06.setOnClickListener(easterListener)
        v07.setOnClickListener(easterListener)
    }

    private fun udpateCountEasterEgg(increment: Boolean) {
        if (increment) {
            countEasterEggs++
            if (countEasterEggs == EASTER_EGG_WINCOUNT) {
                ivSolaireHavel.visibility = View.VISIBLE
                tvWinMessage.visibility = View.VISIBLE
            } else {
                ivSolaireHavel.visibility = View.GONE
                tvWinMessage.visibility = View.GONE
            }
        } else {
            countEasterEggs--
            if (countEasterEggs != EASTER_EGG_WINCOUNT) {
                ivSolaireHavel.visibility = View.GONE
                tvWinMessage.visibility = View.GONE
            }
        }
    }

    override fun showTeams(teamsResult: List<Team>) {}

    override fun onBackPressed() {}
}
