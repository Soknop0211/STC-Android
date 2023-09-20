package com.eazy.stcbusiness.model

import java.io.Serializable

open class LocationModel(var id : String? = null,
                         var name : String? = null,
                         var isClicked : Boolean = false) : Serializable

class StarRatingModel : LocationModel()

data class LanguageModel(var icon : String? = null) : LocationModel()