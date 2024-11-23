package com.amir.aram.feature.sessionActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amir.aram.R
import com.amir.aram.databinding.ItemSessionBinding
import com.amir.aram.model.dataClass.SessionItem
import com.bumptech.glide.Glide


class SessionAdapter(
    private var data: List<SessionItem.SessionItem>
) : RecyclerView.Adapter<SessionAdapter.SessionViewHolder>() {

    inner class SessionViewHolder(private val binding: ItemSessionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(item: SessionItem.SessionItem) {
            binding.sessionName.text = item.session
            binding.sessionTime.text = "${item.time} دقیقه"
            // بررسی وضعیت رایگان یا قفل بودن جلسه
            if (item.free == 1) {
                binding.sessionIcon.setImageResource(R.drawable.ic_check)
            } else {
                binding.sessionIcon.setImageResource(R.drawable.ic_lock)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val binding = ItemSessionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SessionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bindViews(data[position])
    }

    override fun getItemCount(): Int = data.size


    fun updateData(newData: List<SessionItem.SessionItem>) {
        data = newData
        notifyDataSetChanged()
    }
}
