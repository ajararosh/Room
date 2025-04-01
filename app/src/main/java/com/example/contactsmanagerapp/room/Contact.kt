package com.example.contactsmanagerapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// Each instance of this class represents a row in the table

// This tells room database to treat as a table
// If no provided, ROOM will use the name of the class as a
// default table name
@Entity(tableName = "contacts_table")
data class Contact(

    // Set primary key and assign automatically
    // unique values
    @PrimaryKey(autoGenerate = true)
    val contact_id : Int,
//    // allows you to customize the details of a column in
//    // the database table
//    @ColumnInfo(name = "contact_name")
    var contact_name : String,
    var contact_email : String
)
