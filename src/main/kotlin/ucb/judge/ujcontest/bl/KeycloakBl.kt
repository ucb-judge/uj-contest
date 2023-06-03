package ucb.judge.ujcontest.bl
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ucb.judge.ujcontest.dto.KeycloakTokenReqDto
import ucb.judge.ujcontest.service.KeycloakService

@Service
class KeycloakBl constructor(
    private val keycloakService: KeycloakService
) {
    @Value("\${keycloak.resource}")
    private lateinit var resource: String

    @Value("\${keycloak.credentials.secret}")
    private lateinit var credentialsSecret: String

    fun getToken(): String {
        val tokenReqDto = KeycloakTokenReqDto(
            "client_credentials",
            resource,
            credentialsSecret
        )
        val response = keycloakService.getToken(tokenReqDto)
        return response.accessToken;
    }
}