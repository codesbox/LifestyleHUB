package ru.yasdev.auth.sign_up

import android.content.Context
import androidx.room.Room
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject
import ru.yasdev.auth.Constants
import ru.yasdev.auth.PasswordHasher
import ru.yasdev.auth.SaltGenerator
import ru.yasdev.auth.dataBase.AuthDataBase
import ru.yasdev.auth.dataBase.mappers.toAuthEntity
import ru.yasdev.auth.sign_up.models.SignUpDTO
import ru.yasdev.sign_up.data.SaveIdRepository
import ru.yasdev.sign_up.models.SignUpState

class SignUpDataSource(
    private val client: HttpClient, context: Context, private val saveIdRepository: SaveIdRepository
) {

    private val json = Json { ignoreUnknownKeys = true }

    private val db = Room.databaseBuilder(
        context, AuthDataBase::class.java, Constants.DB_NAME
    ).build()

    suspend fun signUp(password: String): SignUpState {
        return try {
            val response = client.get("https://randomuser.me/api/")
            val jsonString: String = response.bodyAsText()
            val results = JSONObject(jsonString).get("results").toString()
            val result = JSONArray(results).get(0).toString()
            val signUpDTO: SignUpDTO = json.decodeFromString<SignUpDTO>(string = result)
            val salt = SaltGenerator.generateSaltString()
            val passwordHash = PasswordHasher.hashPassword(password, salt)
            db.dao.insert(
                signUpDTO.toAuthEntity(hashPassword = passwordHash, salt = salt)
            )
            saveIdRepository.saveId(signUpDTO.login.username)
            SignUpState.Success
        } catch (e: Exception) {
            SignUpState.ErrorOnReceipt
        }
    }
}