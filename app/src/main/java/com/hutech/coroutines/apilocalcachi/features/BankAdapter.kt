package com.hutech.coroutines.apilocalcachi.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hutech.coroutines.apilocalcachi.bank
import com.hutech.coroutines.databinding.BankItemBinding

class BankAdapter : ListAdapter<bank,BankAdapter.BankViewHolder>(BankComparitor()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        val binding = BankItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BankViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        val currentitem = getItem(position)
        if (currentitem != null){
            holder.bind(currentitem)
        }
    }

    class BankViewHolder(private val binding:BankItemBinding) :
         RecyclerView.ViewHolder(binding.root){
             fun bind(bank: bank){
                 binding.apply {
                     textViewName.text = bank.bank_name
                     textViewAccount.text = bank.account_number
                     textViewRouting.text = bank.routing_number
                 }
             }
         }

  class BankComparitor : DiffUtil.ItemCallback<bank>(){
      override fun areItemsTheSame(oldItem: bank, newItem: bank) =
          oldItem.bank_name == newItem.bank_name

      override fun areContentsTheSame(oldItem: bank, newItem: bank) =
          oldItem == newItem
  }



}
