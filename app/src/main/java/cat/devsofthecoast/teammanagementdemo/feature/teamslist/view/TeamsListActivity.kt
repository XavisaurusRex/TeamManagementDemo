package cat.devsofthecoast.teammanagementdemo.feature.teamslist.view

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.Interpolator
import android.view.animation.RotateAnimation
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
import android.view.animation.LinearInterpolator
import android.R.attr.animation
import android.R.attr.animation






class TeamsListActivity : PresenterActivity<TeamsListContract.Presenter, TeamsListContract.View>(), TeamsListContract.View {

    var rocketAnimation: AnimationDrawable? = null

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

        btnGetDatabaseEntry.setOnClickListener {
            presenter.getTeams()
        }

        val rotate = RotateAnimation(0f, 358f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f)
        rotate.interpolator = LinearInterpolator()
        rotate.duration = 3000
        rotate.fillAfter = true
        rotate.fillBefore = true
        rotate.repeatCount = Animation.INFINITE
        rotate.repeatMode = RotateAnimation.RESTART
        ball.setOnClickListener {
            ball.startAnimation(rotate)
        }
    }

    override fun showTeams(teamsResult: List<Team>) {
        tvLogs.text = ""
        for (team: Team in teamsResult) {
            tvLogs.append("TEAM NAME --> " + team.team_name + "\n")
            tvLogs.append("TEAM ID -->" + team.team_id + "\n")
            tvLogs.append("------------------------\n")
        }
    }
}
