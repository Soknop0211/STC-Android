package com.eazy.stcbusiness.model

data class UserPeopleModel (
    var firstName : String? = null,
    var lastName : String? = null,
    var phoneNumber : String?= null,
    var emailAddress : String?= null
) : BaseModel()