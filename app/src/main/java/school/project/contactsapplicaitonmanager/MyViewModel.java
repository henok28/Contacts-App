package school.project.contactsapplicaitonmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private final Repository myRepository;
    LiveData<List<Contacts>> allContacts;
    public MyViewModel(@NonNull Application application) {//if i need an Application context in side my ViewModel i must extend AndroidViewModel
                                                          //Instead of ViewModel this is because the AndroidViewModel has an Application Parameter
        super(application);
        this.myRepository = new Repository(application);
    }
    /*
    * AndroidViewModel is the subclass of ViewModel and similar to them,
    * they are designed to store and manage UI Related Data are responsible to
    * prepare and provide data for UI and automatically allow data to survive
    * Configuration changes
    * */

    //after creating my constructor the second step to initialize the LiveData(i created it in the above"LiveData<List<Contacts>> allContacts;" )
    //and after that i can call the method that was inside my Repository (Remember i have an instance of that class and that class also have the entire implementation)
    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myRepository.getAllContacts();
        return allContacts;
    }
    public void addNewContact(Contacts contact){
        myRepository.addContacts(contact);
    }
    public void deleteContact(Contacts contact){
        myRepository.deleteContacts(contact);
    }

}
