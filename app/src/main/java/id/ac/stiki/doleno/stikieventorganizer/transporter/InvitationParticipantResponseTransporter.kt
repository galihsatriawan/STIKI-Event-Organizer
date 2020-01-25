package id.ac.stiki.doleno.stikieventorganizer.transporter

import id.ac.stiki.doleno.stikieventorganizer.models.network.InvitationsResponse

class InvitationParticipantResponseTransporter : NetworkResponseTransporter<InvitationsResponse> {
    override fun additionalData(response: InvitationsResponse): InvitationsResponse {
        return response
    }
}