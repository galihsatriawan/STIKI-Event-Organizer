package id.ac.stiki.doleno.stikieventorganizer.transporter

import id.ac.stiki.doleno.stikieventorganizer.models.network.ParticipantsResponse

class ParticipantResponseTransporter : NetworkResponseTransporter<ParticipantsResponse> {
    /**
     * Remove Data that has added to database
     */
    override fun additionalData(response: ParticipantsResponse): ParticipantsResponse {
        response.result = ArrayList()
        return response
    }

}