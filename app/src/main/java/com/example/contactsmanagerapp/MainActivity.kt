package com.example.contactsmanagerapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.contactsmanagerapp.databinding.ActivityMainBinding
import com.example.contactsmanagerapp.repository.ContactRepository
import com.example.contactsmanagerapp.room.Contact
import com.example.contactsmanagerapp.room.ContactDatabase
import com.example.contactsmanagerapp.view.MyRecyclerViewAdapter
import com.example.contactsmanagerapp.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        // ROOM Database
        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)

        // View Model
        contactViewModel = ViewModelProvider(
            this
        ).get(ContactViewModel::class.java)

        binding.contactViewModel = contactViewModel

        binding.lifecycleOwner = this // update live data view

        initRecyclerView()




    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        DisplayUserList()
    }

    private fun DisplayUserList() {
        contactViewModel.contacts.observe(
            this,
            Observer{
                binding.recyclerView.adapter = MyRecyclerViewAdapter(
                    // it refers to the implicit single parameter representing each item of the collections
                    // the second parameter takes an item an call the method in the selected item
                    it,{selectedItem: Contact -> listItemClicked(selectedItem)}
                )
            }
        )
    }

    private fun listItemClicked(selectedItem: Contact){
        Toast.makeText(this,
            "Selected name is ${selectedItem.contact_name}",
            Toast.LENGTH_SHORT).show()

        contactViewModel.initUpdateAndDelete(selectedItem)
    }
}