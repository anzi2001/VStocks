package si.kocjancic.vstocks.api

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import si.kocjancic.vstocks.models.MyStock
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object FirestoreApi {
    suspend fun FirebaseUser.pullMyStocks(uid: String) : List<MyStock>{
        return suspendCoroutine { cont ->
            Firebase.firestore.collection("users").document(uid).collection("myStocks").get().addOnSuccessListener {
                cont.resume(it.toObjects(MyStock::class.java))
            }
        }
    }
    suspend fun FirebaseUser.addMyStock(uid:String,myStock: MyStock) : String{
        return suspendCoroutine { cont->
            Firebase.firestore.collection("users").document(uid).collection("myStocks").add(myStock).addOnSuccessListener {
                cont.resume("OK")
            }
        }
    }

}