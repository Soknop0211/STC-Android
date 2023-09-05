package com.eazy.stcbusiness.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(
    var items: ArrayList<T> = ArrayList()
) : RecyclerView.Adapter<VH>() {

    protected var completion: ((item: T, position: Int) -> Unit)? = null

    var recyclerView: RecyclerView? = null
    var paginationTotal: Int = -1

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun set(data: T) {
        items.clear()
        items.add(data)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun set(data: List<T>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    open fun add(data: T) {
        val startIndex = items.size
        items.add(data)
        notifyItemRangeInserted(startIndex, 1)
    }

    open fun add(data: T, position: Int) {
        items.add(position, data)
        notifyItemInserted(position)
    }

    open fun add(data: List<T>) {
        val startIndex = items.size
        items.addAll(data)
        notifyItemRangeInserted(startIndex, data.size)
    }

    open fun update(position: Int, item: T) {
        items[position] = item
        notifyItemChanged(position)
    }

    open fun update(item: T) {
        val position = items.indexOf(item)
        update(position, item)
    }

    open fun removeAt(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    open fun removeItem(item: T) {
        val position = items.indexOf(item)
        removeAt(position)
    }

    open fun removeFirst() {
        removeItem(items[0])
    }

    open fun removeLast() {
        removeItem(items[items.lastIndex])
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun notifyFirstItemChange() {
        notifyItemChanged(0)
    }

    fun notifyLastItemChanged() {
        notifyItemChanged(items.lastIndex)
    }

    fun setListener(completion: ((item: T, position: Int) -> Unit)?) {
        this.completion = completion
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateItemHolder(LayoutInflater.from(parent.context), parent, viewType)
    }

    final override fun onBindViewHolder(holder: VH, position: Int) {
        onBindItemHolder(holder, position, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    protected abstract fun onCreateItemHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): VH

    protected abstract fun onBindItemHolder(holder: VH, position: Int, context: Context)
}