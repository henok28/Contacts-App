package school.project.contactsapplicaitonmanager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDAO getContactDAO();//this method is used by the Room to have the implementation of the DAO methods


    //here we i am going to create a singleton pattern
    //a singleton pattern enable the app to only create a single instance of the DB
    //so that it wouldn't unnecessarily create instances and usage of memory

    /*
    * steps to create it
    * */

    private static ContactDatabase dbInstance;
    public static synchronized ContactDatabase getInstance(Context context){
        if(dbInstance == null){
            dbInstance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ContactDatabase.class,
                    "contacts_db"
            ).fallbackToDestructiveMigration().build();
        }

        return dbInstance;
    }
}
