package com.eazy.stcbusiness.utils.listener

import android.text.Editable
import android.text.TextWatcher

abstract class BaseEdittextListener : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        if (editable.toString().isNotEmpty()) {
            editable?.let { afterTextChangedNotEmpty(it) }
        } else {
            afterTextChangedIsEmpty()
        }
    }

    abstract fun afterTextChangedNotEmpty(editable: Editable)

    abstract fun afterTextChangedIsEmpty()
}
