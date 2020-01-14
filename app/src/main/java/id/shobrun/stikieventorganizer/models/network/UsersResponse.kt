package id.shobrun.stikieventorganizer.models.network

import id.shobrun.stikieventorganizer.models.NetworkResponseModel
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.models.entity.User

class UsersResponse (
    var result: List<User>,
    var status : String,
    var message : String
) : NetworkResponseModel