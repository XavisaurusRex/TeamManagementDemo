package cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.impl

import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.trainers.impl.TrainersServiceImpl

class TrainersRepositoryImpl : TrainersRepository {
    val service = TrainersServiceImpl()

    override fun setTrainers(trainers: List<Trainer>, listener: ServiceCallback<Boolean>?) {
        service.setTrainers(trainers, listener)
    }

    override fun getTrainer(trainerKey: String, serviceCallback: ServiceCallback<Trainer?>) {
        service.getTrainer(trainerKey, serviceCallback)
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