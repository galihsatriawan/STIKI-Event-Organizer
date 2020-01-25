package id.ac.stiki.doleno.stikieventorganizer.transporter

import id.ac.stiki.doleno.stikieventorganizer.models.NetworkResponseModel

interface NetworkResponseTransporter<FROM : NetworkResponseModel> {
    fun additionalData(response: FROM): FROM
}