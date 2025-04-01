package com.example.contactsmanagerapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
import com.example.contactsmanagerapp.R
import com.example.contactsmanagerapp.databinding.CardItemBinding
import com.example.contactsmanagerapp.room.Contact

class MyRecyclerViewAdapter(
    private val contactList: List<Contact>,
    private val clickListener: (Contact) -> Unit // lambda expression
): RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolderClass>(){

    class MyViewHolderClass(val binding: CardItemBinding)
        : RecyclerView.ViewHolder(binding.root){

            fun bind(contact: Contact, clickListener: (Contact) -> Unit){
                // Asigning this values to the object attribute name
                binding.nameTextView.text = contact.contact_name
                binding.emailTextView.text = contact.contact_email

                binding.listItemLayout.setOnClickListener{
                    clickListener(contact)
                }
            }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderClass {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CardItemBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.card_item,
            parent,
            false)

        return MyViewHolderClass(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: MyViewHolderClass, position: Int) {
        holder.bind(contactList[position],clickListener)
    }
}