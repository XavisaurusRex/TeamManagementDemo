package cat.devsofthecoast.teammanagementdemo.feature.devoptions.usecase

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.feature.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository

class FillDatabaseUseCase(val appConfig: BaseConfig,
                          private val repository: TMDRepository,
                          private val context: Context) : UseCase<Void?, Boolean>(appConfig) {


    override fun run(input: Void?, callback: Callback<Boolean>?) {
        try {
            repository.setDummieDatabase()
            callback?.onSuccess(true)
        } catch (ex: Exception){
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void?, Boolean>.() -> Unit)?) :
            UseCaseExecutor<Void?, Boolean>(config, builder)
}