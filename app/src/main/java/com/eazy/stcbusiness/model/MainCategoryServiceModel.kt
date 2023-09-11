package com.eazy.stcbusiness.model

data class ServiceListModel(var data : MutableList<MainCategoryServiceModel> = mutableListOf()) : BaseModel()

data class MainCategoryServiceModel(
    var category_name : String?,
    var service_name: String?,
    var service_item_list : ArrayList<ServiceByCategoryModel>
) : BaseModel()

data class ServiceByCategoryModel(
    var sv_item_name : String?,
    var sv_item_photo : String?,
    var sv_item_price : String?,
    var sv_item_working_period : String
) : BaseModel()

// Detail Service
data class ServiceDetail(val data : ServiceDetailModel?)
data class ServiceDetailModel(
    var sv_name : String?,
    var sv_item_name : String?,
    var sv_item_price : String?,
    var sv_item_tax : String?,
    var sv_item_term : String?,

    var sv_item_total_selectable_day : String?,
    var sv_item_working_period : String?,
    var sv_item_photo_path : String?,
    var sv_item_working_day : ArrayList<WorkingDayModel> = ArrayList()
) : BaseModel()

data class WorkingDayModel(
    var day_string : String?,
    var start_times : ArrayList<WorkingTimeModel> = ArrayList()
) : BaseModel()

data class WorkingTimeModel(
    var start_time : String?,
    var end_time : String?,
) : BaseModel()