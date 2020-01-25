package id.ac.stiki.doleno.stikieventorganizer.transporter

import id.ac.stiki.doleno.stikieventorganizer.models.network.EventsResponse

class EventResponseTransporter : NetworkResponseTransporter<EventsResponse> {
    override fun additionalData(response: EventsResponse): EventsResponse {
        response.result = ArrayList()
        return response
    }

}