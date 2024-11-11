package school.project.contactsapplicaitonmanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDAO {//it species a contract on how to interact with the table aka entity.
    @Insert
    void insert(Contacts contacts);

    @Delete
    void delete(Contacts contacts);

    //but here what if i want to make a custom query and relate it to a method in my interface
    //i will use the annotation @Query
    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getAllContacts();//LiveData is used because we want to show our update in the UI
}
