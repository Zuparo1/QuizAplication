package com.example.quizaplication.service.impl

import com.example.quizaplication.model.User
import com.example.quizaplication.service.AccountService
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService{
    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()
    override val hasUser: Boolean
        get() = auth.currentUser != null
    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User() )
            }
            auth.addAuthStateListener(listener)
            awaitClose {auth.removeAuthStateListener(listener)}
        }

    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

    override suspend fun authenticate(
        email: String,
        password: String,
        onResult: (Throwable?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { onResult(it.exception) }.await()
    }

    override suspend fun createAccount(
        email: String,
        password: String,
        onResult: (Throwable?) -> Unit
    ) {
        /* This might work but not sure
        val credential = EmailAuthProvider.getCredential(email, password)
        auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener { onResult(it.exception) }.await()
         */

        auth.createUserWithEmailAndPassword(email, password)

    }

    override suspend fun signOut() {
        auth.signOut()

        auth.signInAnonymously()
    }

}