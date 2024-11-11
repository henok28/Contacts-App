package school.project.contactsapplicaitonmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")//here we can specify the name of our table or otherwise the class name would be the name fo the table

public class Contacts {
    //and fields defined here are considered as a column in out table
    @ColumnInfo(name = "contact_id")//here i am giving a name for my column "in the table!!" not in the class
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "contact_name")
    private String name;

    @ColumnInfo(name = "contact_email")
    private String email;

    public Contacts(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Contacts() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
