package id.ac.stiki.doleno.stikieventorganizer.transporter

import id.ac.stiki.doleno.stikieventorganizer.models.network.UsersResponse

class UserResponseTransporter : NetworkResponseTransporter<UsersResponse> {
    override fun additionalData(response: UsersResponse): UsersResponse {
        response.result = ArrayList()
        return response
    }
}