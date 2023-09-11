package com.eazy.stcbusiness.utils.listener;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.view.inputmethod.InputConnectionCompat;
import androidx.core.view.inputmethod.InputContentInfoCompat;
import androidx.databinding.BindingAdapter;

import com.eazy.stcbusiness.R;

/**
 * Created by nuonveyo on 4/24/18.
 */

public final class EditTextBindingUtil {
    private EditTextBindingUtil() {
    }

    @BindingAdapter("addTextChangeListener")
    public static void addTextChangeListener(EditText editText, TextWatcher textWatcher) {
        if (textWatcher == null) {
            return;
        }
        String text = editText.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            editText.setSelection(text.length());
        }
        editText.addTextChangedListener(textWatcher);
    }

    @BindingAdapter("editTextErrorMessage")
    public static void editTextErrorMessage(EditText editText, String errorMessage) {
        editText.setError(errorMessage);
    }

    @BindingAdapter(value = {"validateField", "errorMessage"}, requireAll = false)
    public static void validateField(EditText editText, boolean isError, String errorMessage) {
        if (isError) {
            if (!TextUtils.isEmpty(errorMessage)) {
                editText.setError(errorMessage);
            } else {
                editText.setError(editText.getContext().getString(R.string.login_error_field_required));
            }
        } else {
            editText.setError(null);
        }
    }

    @BindingAdapter("clearText")
    public static void editTextErrorMessage(EditText editText, final View errorMessage) {
        if (TextUtils.isEmpty(editText.getText())) {
            errorMessage.setVisibility(View.GONE);
        } else {
            errorMessage.setVisibility(View.VISIBLE);
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(charSequence)) {
                    errorMessage.setVisibility(View.GONE);
                } else {
                    errorMessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        errorMessage.setOnClickListener(view -> editText.setText(""));
    }

//    @BindingAdapter("setKeyBoardInputCallbackListener")
//    public static void setKeyBoardInputCallbackListener(KeyboardInputEditor editor,
//                                                        KeyboardInputEditor.KeyBoardInputCallbackListener listener) {
//        editor.setKeyBoardInputCallbackListener(listener);
//    }
}


