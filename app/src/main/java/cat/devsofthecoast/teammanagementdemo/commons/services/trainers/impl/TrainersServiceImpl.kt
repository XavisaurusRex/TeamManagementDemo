package cat.devsofthecoast.teammanagementdemo.commons.services.trainers.impl

import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.trainers.TrainersService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference

class TrainersServiceImpl : BaseService(), TrainersService {
    private val TRAINERS_LOCATION = "trainers"
    override val refTable: DatabaseReference = firebaseDatabase.child(TRAINERS_LOCATION)

    override fun setTrainers(trainers: List<Trainer>, listener: ServiceCallback<Boolean>?) {
        val keysTrainers = mutableMapOf<String, Trainer>()
        for (trainer: Trainer in trainers) {
            if (trainer.key == null) assignNewKey(trainer)
            keysTrainers[trainer.key!!] = trainer
        }
        if (keysTrainers.isNotEmpty()) {
            addNewDataList(keysTrainers, OnCompleteListener {
                when {
                    it.isSuccessful -> {
                        listener?.onSuccess(it.isSuccessful)
                    }
                    it.isCanceled -> listener?.onError(PostingFirebaseException())
                    else -> listener?.onError(PostingFirebaseException())
                }
            })
        } else {
            listener?.onError(PostingFirebaseException())
        }
    }
}