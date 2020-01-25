package id.ac.stiki.doleno.stikieventorganizer.models.network

import id.ac.stiki.doleno.stikieventorganizer.models.NetworkResponseModel
import id.ac.stiki.doleno.stikieventorganizer.models.entity.User

class UsersResponse(
    var result: List<User>,
    var status: String,
    var message: String
) : NetworkResponseModel