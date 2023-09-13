package com.eazy.stcbusiness.ui.transportation

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.base.SampleBaseBottomSheetDialogFragment
import com.eazy.stcbusiness.databinding.FragmentAddNoteBottomSheetFragementBinding
import com.eazy.stcbusiness.utils.backgroundTint
import com.eazy.stcbusiness.utils.listener.BaseEdittextListener
import com.eazy.stcbusiness.utils.listener.CustomResponseOnClickListener
import com.eazy.stcbusiness.utils.listener.CustomSetOnClickViewListener

class AddNoteBottomSheetFragment : SampleBaseBottomSheetDialogFragment<FragmentAddNoteBottomSheetFragementBinding>() {

    private var mEditTextSt: String? = null
    override val layoutResource: Int = R.layout.fragment_add_note_bottom_sheet_fragement
    private var mOnClickCallBackListener : OnClickCallBackListener?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Init Data **/
        setKeyboardWithEditText()

        arguments?.let {
            mEditTextSt = it.getString(ITEM_EDIT_TEXT)
        }

        /** Init Edit Text **/
        if (mEditTextSt != null){
            mBinding.noteEdt.setText(mEditTextSt)
        } else {
            mBinding.btnConfirm.isEnabled = false
            mBinding.btnConfirm.backgroundTint(R.color.gray)
        }
        mBinding.noteEdt.addTextChangedListener(object : BaseEdittextListener() {
            override fun afterTextChangedNotEmpty(editable: Editable) {
                mBinding.btnConfirm.isEnabled = true
                mBinding.btnConfirm.backgroundTint(R.color.colorAppPrimary)
            }

            override fun afterTextChangedIsEmpty() {
                mBinding.btnConfirm.isEnabled = false
                mBinding.btnConfirm.backgroundTint(R.color.gray)
            }

        })

        /** Init Click **/
        mBinding.btnConfirm.setOnClickListener(CustomSetOnClickViewListener (object : CustomResponseOnClickListener {
            override fun onClick(view: View) {
                if (mOnClickCallBackListener != null && !TextUtils.isEmpty(mBinding.noteEdt.text.toString())) {
                    mOnClickCallBackListener?.onCallBackItemListener(mBinding.noteEdt.text.toString())
                    dismiss()
                }
            }

        }))

        mBinding.iconBack.setOnClickListener { dismiss() }

    }

    companion object {

        private const val ITEM_EDIT_TEXT = "ITEM_EDIT_TEXT"

        @JvmStatic
        fun newInstance(param1: String?) =
            AddNoteBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(ITEM_EDIT_TEXT, param1)
                }
            }
    }

    fun initListener(mOnClickCallBackListener: OnClickCallBackListener) {
        this.mOnClickCallBackListener = mOnClickCallBackListener
    }

    interface OnClickCallBackListener {
        fun onCallBackItemListener(mText : String)
    }
}