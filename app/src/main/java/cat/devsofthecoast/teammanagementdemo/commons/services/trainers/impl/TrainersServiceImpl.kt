package cat.devsofthecoast.teammanagementdemo.commons.services.trainers.impl

import cat.devsofthecoast.teammanagementdemo.commons.cache.TMDCache
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.PostingFirebaseException
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.services.BaseService
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.trainers.TrainersService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
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

    override fun setTrainer(trainer: Trainer, listener: ServiceCallback<Void?>?) {
        addNewData(trainer, OnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.onSuccess(null)
                }

                it.isCanceled -> listener?.onError(PostingFirebaseException())
                else -> listener?.onError(PostingFirebaseException())
            }
        })
    }

    override fun updateTrainer(trainer: Trainer, listener: ServiceCallback<Void?>?){
        updateData(trainer, OnCompleteListener {
            when {
                it.isSuccessful -> {
                    listener?.onSuccess(null)
                }

                it.isCanceled -> listener?.onError(PostingFirebaseException())
                else -> listener?.onError(PostingFirebaseException())
            }
        })
    }

    override fun getTrainer(trainerKey: String, serviceCallback: ServiceCallback<Trainer?>, cache: TMDCache<String, Trainer>) {
        getSingleSnapShot(refTable.child(trainerKey)) { dataSnapshot: DataSnapshot? ->
            if (dataSnapshot != null) {
                val trainer: Trainer? = dataSnapshot.getValue(Trainer::class.java)
                if (trainer != null) {
                    cache[trainerKey] = trainer
                    serviceCallback.onSuccess(trainer)
                } else {
                    serviceCallback.onError(NullResponseFirebase())
                }
            } else {
                serviceCallback.onError(NullResponseFirebase())
            }
        }
    }
}