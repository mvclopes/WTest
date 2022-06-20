package com.mvclopes.wtest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mvclopes.wtest.databinding.PostalCodeItemLayoutBinding
import com.mvclopes.wtest.domain.model.PostalCode

class PostalCodeAdapter: ListAdapter<PostalCode, PostalCodeViewHolder>(
    PostalCodeDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostalCodeViewHolder {
        return PostalCodeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostalCodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object PostalCodeDiffCallback: DiffUtil.ItemCallback<PostalCode>() {
    override fun areItemsTheSame(oldItem: PostalCode, newItem: PostalCode): Boolean {
        return oldItem.postalCodeNumber == newItem.postalCodeNumber
    }

    override fun areContentsTheSame(oldItem: PostalCode, newItem: PostalCode): Boolean {
        return oldItem == newItem
    }

}

class PostalCodeViewHolder private constructor(private val binding: PostalCodeItemLayoutBinding):
    RecyclerView.ViewHolder(binding.root) {
    fun bind(postalCode: PostalCode){
        binding.postalCodeLabel.text = postalCode.postalCodeNumber
        binding.postalDesignationLabel.text = postalCode.postalDesignation
    }

    companion object{
        fun from(parent: ViewGroup): PostalCodeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PostalCodeItemLayoutBinding.inflate(inflater, parent, false)
            return PostalCodeViewHolder(binding)
        }
    }
}