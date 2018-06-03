package com.mohan.prepare.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohan.prepare.BR

abstract class ListAdapter<T>(var list: List<T> = emptyList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(list: List<T>) {
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = DataBindingUtil.bind<ViewDataBinding>(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
        return ViewHolder<T>(binding)
    }



    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return getViewType(position)
    }

    abstract fun getViewType(position: Int): Int

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder<T>).bind(list.get(position))
    }

    class ViewHolder<T>(val binding: ViewDataBinding?) : RecyclerView.ViewHolder(binding?.root) {

        fun bind(t: T) {
            binding?.setVariable(BR.item, t)
            binding?.executePendingBindings()
        }

    }


}