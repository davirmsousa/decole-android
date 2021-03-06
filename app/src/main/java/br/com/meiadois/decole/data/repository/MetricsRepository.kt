package br.com.meiadois.decole.data.repository

import br.com.meiadois.decole.data.network.RequestHandler
import br.com.meiadois.decole.data.network.client.DecoleClient
import br.com.meiadois.decole.data.network.response.*

class MetricsRepository(
    private val client: DecoleClient
) : RequestHandler() {

    suspend fun getUserMetrics(): AnalyticsResponse {
        return callClient {
            client.getUserMetrics()
        }
    }

}