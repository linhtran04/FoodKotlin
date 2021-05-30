package com.marknkamau.FoodKotlin.data.network

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.marknjunge.core.data.network.service.GoogleSignInService
import kotlinx.coroutines.tasks.await

class GoogleSignInServiceImpl(private val googleSignInClient: GoogleSignInClient) :
    GoogleSignInService {
    override suspend fun signOut() {
        googleSignInClient.signOut().await()
    }
}
