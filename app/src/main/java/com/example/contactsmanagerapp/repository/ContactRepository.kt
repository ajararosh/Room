package com.example.contactsmanagerapp.repository

import com.example.contactsmanagerapp.room.Contact
import com.example.contactsmanagerapp.room.ContactDAO

// Repository: acts a bridge between the ViewModel and
// Data Source. Provide a clean API to interact wiht the
// ROOM database
// ContactDAO Provides methods for interacting with the room
// database
class ContactRepository(
    private val contactDAO: ContactDAO
) {
    val contacts = contactDAO.getAllContactsInDB()

    suspend fun insert(contact: Contact): Long{
        return contactDAO.insertContact(contact)
    }

    suspend fun delete(contact: Contact){
        return contactDAO.deleteContact(contact)
    }

    suspend fun update(contact: Contact){
        return contactDAO.updateContact(contact)
    }

    suspend fun deleteAll(){
        return contactDAO.deleteAll()
    }
}