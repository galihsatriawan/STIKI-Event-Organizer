package id.shobrun.stikieventorganizer.transporter

import id.shobrun.stikieventorganizer.models.network.UsersResponse

class UserResponseTransporter : NetworkResponseTransporter<UsersResponse> {
    override fun additionalData(response: UsersResponse): UsersResponse {
        response.result = ArrayList()
        return response
    }
}