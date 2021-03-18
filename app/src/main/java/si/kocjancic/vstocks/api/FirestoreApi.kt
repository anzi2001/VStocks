package si.kocjancic.vstocks.api

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import si.kocjancic.vstocks.models.MyStock
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun FirebaseUser.pullMyStocks() : List<MyStock>{
    return suspendCoroutine { cont ->
        Firebase.firestore.collection("users").document(uid).collection("myStocks").get().addOnSuccessListener {
            cont.resume(it.toObjects(MyStock::class.java))
        }
    }
}
suspend fun FirebaseUser.addMyStock(myStock: MyStock) : String{
    return suspendCoroutine { cont->
        Firebase.firestore.collection("users").document(uid).collection("myStocks").add(myStock).addOnSuccessListener {
            cont.resume("OK")
        }
    }
}