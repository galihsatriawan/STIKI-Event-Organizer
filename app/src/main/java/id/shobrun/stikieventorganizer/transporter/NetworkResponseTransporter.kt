package id.shobrun.stikieventorganizer.transporter

import id.shobrun.stikieventorganizer.models.NetworkResponseModel

interface NetworkResponseTransporter<FROM : NetworkResponseModel>{
    fun additionalData(response : FROM) : FROM
}