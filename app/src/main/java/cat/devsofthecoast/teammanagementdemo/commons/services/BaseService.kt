package cat.devsofthecoast.teammanagementdemo.commons.services

import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.DatabaseModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.*


abstract class BaseService {
    protected val firebaseDatabase = FirebaseDatabase.getInstance().reference.child(BuildConfig.TEAMMANAGEMENT_BASE_LOCATION)

    abstract val refTable: DatabaseReference

    private fun DatabaseReference.getNewKey(): String {
        return this.push().key!!
    }

    fun assignNewKey(databaseModel: DatabaseModel) {
        databaseModel.key = refTable.getNewKey()
    }

    protected fun getSingleSnapShot(databaseReference: DatabaseReference, listener: ((DataSnapshot?) -> Unit)) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listener.invoke(dataSnapshot)
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {
                listener.invoke(null)
            }
        })
    }

    protected fun addNewDataList(databaseModels: Map<String, DatabaseModel>, onCompleteListener: OnCompleteListener<Void>) {
        refTable.updateChildren(databaseModels).addOnCompleteListener(onCompleteListener)
    }

    protected fun addNewData(databaseModel: DatabaseModel, onCompleteListener: OnCompleteListener<Void>) {
        refTable.child(databaseModel.key!!).setValue(databaseModel).addOnCompleteListener(onCompleteListener)
    }

    protected fun updateData(databaseModel: DatabaseModel, onCompleteListener: OnCompleteListener<Void>) {
        val map = hashMapOf<String, DatabaseModel>()
        map[databaseModel.key!!] = databaseModel
        refTable.updateChildren(map.toMap()).addOnCompleteListener(onCompleteListener)
    }

    protected fun removeAll(onCompleteListener: OnCompleteListener<Void>) {
        refTable.removeValue().addOnCompleteListener(onCompleteListener)
    }
}