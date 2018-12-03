package cat.devsofthecoast.teammanagementdemo.commons.useCase.dailyEntries

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.DailyEntriesRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetDailyEntriesUseCase(val appConfig: BaseConfig,
                             private val dailyEntriesRepository: DailyEntriesRepository) : UseCase<String, ArrayList<DailyEntry>>(appConfig) {
    override fun run(input: String?, callback: Callback<ArrayList<DailyEntry>>?) {
        try {
            dailyEntriesRepository.getDailyEntries(input!!, object : ServiceCallback<ArrayList<DailyEntry>> {
                override fun onSuccess(value: ArrayList<DailyEntry>) {
                    callback?.onSuccess(value)
                }

                override fun <E : Throwable> onError(error: E) {
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, ArrayList<DailyEntry>>.() -> Unit)?) :
            UseCaseExecutor<String, ArrayList<DailyEntry>>(config, builder)
}