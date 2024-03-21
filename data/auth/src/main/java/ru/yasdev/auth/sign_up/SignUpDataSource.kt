package ru.yasdev.auth.sign_up

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject
import ru.yasdev.auth.sign_up.models.SignUpDTO
import ru.yasdev.sign_up.models.SignUpState

class SignUpDataSource(private val client: HttpClient) {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun signUp(password: String): SignUpState {
        return try{
            val response = client.get("https://randomuser.me/api/")
            val jsonString: String = response.bodyAsText()
            val results = JSONObject(jsonString).get("results").toString()
            val result = JSONArray(results).get(0).toString()
            println(result)
            val signUpDTO: SignUpDTO = json.decodeFromString<SignUpDTO>(string = result)
            println(signUpDTO)
            SignUpState.Success
        }catch (e: Exception){
            println(e.message)
            SignUpState.ErrorOnReceipt
        }
    }
}