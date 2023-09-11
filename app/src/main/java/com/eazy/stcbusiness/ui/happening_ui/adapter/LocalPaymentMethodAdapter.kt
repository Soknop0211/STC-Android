package com.eazy.stcbusiness.ui.happening_ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eazy.stcbusiness.R
import com.eazy.stcbusiness.model.LocalPaymentModel
import com.eazy.stcbusiness.utils.initText

class LocalPaymentMethodAdapter(private val listName: List<LocalPaymentModel>, private val isCardPayment : Boolean, private val mListener : (LocalPaymentModel) -> Unit) : RecyclerView.Adapter<LocalPaymentMethodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.payment_method_online_option, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindingView(listName[position], isCardPayment, mListener)
    }

    override fun getItemCount(): Int {
        return listName.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var nameTv : TextView = view.findViewById(R.id.nameTv)
        private var descriptionTv : TextView = view.findViewById(R.id.descriptionTv)
        private var iconBank : ImageView = view.findViewById(R.id.icon_bank)
        private var selectButton : ImageView = view.findViewById(R.id.selectButton)
        private var mainLayout : LinearLayout = view.findViewById(R.id.mainLayout)
        private var cardPayments : ImageView = view.findViewById(R.id.cardPayments)

        fun onBindingView(item : LocalPaymentModel, isCardPayment : Boolean, mListener : (LocalPaymentModel) -> Unit){
            nameTv.initText(item.name)
            descriptionTv.initText(item.description)
            Glide.with(iconBank).load(item.drawable ?: R.drawable.no_image).circleCrop().into(iconBank)
            Glide.with(selectButton).load(if (item.isClick) R.drawable.selected_button else R.drawable.un_selected_button).into(selectButton)

            cardPayments.visibility = if (isCardPayment) View.VISIBLE else View.GONE
            descriptionTv.visibility = if (isCardPayment) View.GONE else View.VISIBLE

            mainLayout.setOnClickListener { mListener.invoke(item) }
        }
    }

}