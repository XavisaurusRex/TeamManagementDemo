package cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.impl

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.trainers.impl.TrainersServiceImpl

class TrainersRepositoryImpl : TrainersRepository {
    private val LOG_TAG = "TrainersRepositoryImpl"

    private val trainerCache: TMDCache<String, Trainer> = TMDCache()
    private val service = TrainersServiceImpl()

    override fun setTrainers(trainers: List<Trainer>, listener: ServiceCallback<Boolean>?) {
        service.setTrainers(trainers, listener)
    }

    override fun getTrainer(trainerKey: String, serviceCallback: ServiceCallback<Trainer?>) {
        if (trainerCache.contains(trainerKey)) {
            Log.d(LOG_TAG, "getTrainer from Cache")
            serviceCallback.onSuccess(trainerCache[trainerKey])
        } else {
            Log.d(LOG_TAG, "getTrainer from Firebase")
            service.getTrainer(trainerKey, serviceCallback, trainerCache)
        }
    }

    override fun setTrainer(trainer: Trainer, listener: ServiceCallback<Void?>?) {
        service.setTrainer(trainer, listener)
    }

    override fun updateTrainer(trainer: Trainer, listener: ServiceCallback<Void?>?) {
        service.updateTrainer(trainer, listener)
    }

    override fun assignKey(databaseModel: DatabaseModel) {
        service.assignNewKey(databaseModel)
    }

    override fun assignKeys(databaseModels: List<DatabaseModel>) {
        for (databaseModel in databaseModels) {
            assignKey(databaseModel)
        }
    }
}