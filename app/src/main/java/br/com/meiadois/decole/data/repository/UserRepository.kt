package br.com.meiadois.decole.data.repository

import br.com.meiadois.decole.data.localdb.AppDatabase
import br.com.meiadois.decole.data.localdb.entity.User
import br.com.meiadois.decole.data.network.RequestHandler
import br.com.meiadois.decole.data.network.client.DecoleClient
import br.com.meiadois.decole.data.network.request.LoginRequest
import br.com.meiadois.decole.data.network.request.RegisterRequest
import br.com.meiadois.decole.data.network.response.IntroduceResponse
import br.com.meiadois.decole.data.network.response.CompanyResponse
import br.com.meiadois.decole.data.network.response.LikeResponse
import br.com.meiadois.decole.data.network.response.LoginResponse
import br.com.meiadois.decole.data.network.response.RegisterResponse

class UserRepository(
    private val client: DecoleClient,
    private val db: AppDatabase
) : RequestHandler() {

    suspend fun login(email: String, password: String): LoginResponse {
        return callClient {
            client.login(LoginRequest(email, password))
        }
    }

    suspend fun register(username: String, email: String, password: String): RegisterResponse {
        return callClient {
            client.register(RegisterRequest(username, email, password))
        }
    }

    suspend fun introduce(): IntroduceResponse {
        db.getUserDao().updateIntroducedStatus(
            db.getUserDao().findJWT()!!,
            true
        )
        return callClient {
            client.introduce()
        }
    }

    suspend fun getUserCompany(): CompanyResponse {
        return callClient {
            client.getUserCompany()
        }
    }

    suspend fun listUserMatches(): List<LikeResponse> {
        return callClient {
            client.listUserMatches()
        }
    }


    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().find()

}