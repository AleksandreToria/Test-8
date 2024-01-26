package com.example.test7.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test7.R
import com.example.test7.databinding.ItemLayoutBinding
import com.example.test7.presentation.model.DrawerMenuItem

class DrawerMenuAdapter(private val onItemClick: (DrawerMenuItem) -> Unit)
    : ListAdapter<DrawerMenuItem, DrawerMenuAdapter.ViewHolder>(DrawerMenuItemDiffCallback()) {

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DrawerMenuItem) {
            with(binding) {
                itemIcon.setImageResource(item.icon)
                itemTitle.text = item.title

                item.notification?.let {
                    tvNotification.visibility = View.VISIBLE
                    tvNotification.text = it.toString()
                }

                root.setOnClickListener { onItemClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DrawerMenuItemDiffCallback : DiffUtil.ItemCallback<DrawerMenuItem>() {
        override fun areItemsTheSame(oldItem: DrawerMenuItem, newItem: DrawerMenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DrawerMenuItem, newItem: DrawerMenuItem): Boolean {
            return oldItem == newItem
        }
    }
}