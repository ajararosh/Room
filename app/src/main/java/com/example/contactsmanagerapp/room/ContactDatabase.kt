package com.example.contactsmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Array of entities classes
@Database(entities = [Contact::class], version = 1)
// abstract class to prevent any instances of this class
abstract class ContactDatabase: RoomDatabase() {

    abstract val contactDAO : ContactDAO

    // Singleton Design Pattern
    // You avoid unnecessary overhead
    // Only one instance of the database exists, avoiding
    // unnecessary overhead associated with repeated database creation


//    companion object: define a static singleton instance this DB Class
//    @volatile: prevents any possible race conditions in multithreading
    companion object{
        @Volatile
        private var INSTANCE: ContactDatabase?= null

        fun getInstance(context:Context): ContactDatabase {
            // only one thread can access it at a time
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){

                    // Creating the database object
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contacts_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }




}