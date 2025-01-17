package mx.cazv.todasbrillamos.model.services

import mx.cazv.todasbrillamos.model.API
import mx.cazv.todasbrillamos.model.ApiConfig
import mx.cazv.todasbrillamos.model.apiCall
import mx.cazv.todasbrillamos.model.models.ChatMessage
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatService {
    private val retrofitApi by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService by lazy {
        retrofitApi.create(API::class.java)
    }

    suspend fun sendMessage(token: String, content: String): String {
        return apiCall { apiService.sendMessage("Bearer $token", ChatMessage(content)) }.getOrNull()!!.message
    }
}