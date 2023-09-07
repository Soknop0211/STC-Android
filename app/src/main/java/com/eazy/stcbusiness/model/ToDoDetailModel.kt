package com.eazy.stcbusiness.model

import com.google.gson.annotations.SerializedName

data class ToDoDetailModel (
    var name : String? = null,
    var location : String? = null,
    @SerializedName("open_from") var openFrom : String? = null,
    @SerializedName("close_at") var closeAt : String? = null,
    @SerializedName("category_type") var categoryType : String? = null,
    var images : ArrayList<String>,
    var rating : Float,
    var distance : String? = null,
    var duration : String? = null,
    var total : Double,
    var discount : String? = null,
    @SerializedName("term_and_condition ") var termAndCondition  : String? = null,
    @SerializedName("you_will_know ") var youWillKnow  : ArrayList<YouWillKnow>,
    ) : BaseModel()

class YouWillKnow(
    var pickup : String? = null,
    var allow : String? = null,
    var requirement : String? = null,
    ) : BaseModel()
