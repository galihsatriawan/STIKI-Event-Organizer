package id.shobrun.stikieventorganizer.transporter

import id.shobrun.stikieventorganizer.models.network.EventsResponse

class EventResponseTransporter : NetworkResponseTransporter<EventsResponse> {
    override fun additionalData(response: EventsResponse): EventsResponse {
        response.result = ArrayList()
        return response
    }

}