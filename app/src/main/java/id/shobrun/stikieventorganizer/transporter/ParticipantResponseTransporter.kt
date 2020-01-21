package id.shobrun.stikieventorganizer.transporter

import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse

class ParticipantResponseTransporter : NetworkResponseTransporter<ParticipantsResponse> {
    /**
     * Remove Data that has added to database
     */
    override fun additionalData(response: ParticipantsResponse): ParticipantsResponse {
        response.result = ArrayList()
        return response
    }

}