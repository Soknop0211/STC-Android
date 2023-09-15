package com.eazy.stcbusiness.model

class CarRentalSuggestedRideModel (var name : String? = null,
                                   var image : String ?= null,
                                   var price : Double ?= null,
                                   var description  : String ?= null,
                                   var mItemCount: Int = 1,
                                   var priceBase : Double ?= null,
) : BaseModel() {
    fun setItemCount(cartCount: Int) {
        var mCartCount = cartCount
        if (mCartCount < 0) {
            mCartCount = 0
        }
        mItemCount = mCartCount
        updateItemPrice()
    }

    private fun updateItemPrice() {
        price = priceBase?.times(mItemCount)
    }
}