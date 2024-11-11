package school.project.contactsapplicaitonmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final ContactDAO contactDAO;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {

        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

    //now the idea of createing a repo is that to access DAO class methods here

/*
  Here in our repo we have to execute those methods in another thread to make out UI(View)
  Responsive all the time in nature the Database functions takes time to process if we execute
  Those methods in the same trade with the UI the user wont be able to access or see the UI
  In the intended time so for that reason we have to create a new thread and execute any
  Database related tasks there in our thread and once we are done processing that method
  and if the method needs to return some data that is going to be populated in the main
  thread (UI thread) we will use a Handler(class in java that sends the result to the main thread)
  to update the UI in the Main Thread
*/
    //
    public void addContacts(Contacts contacts){
        /*
        This is used to run the methods in the background thread,
        <CODE
        Any Concurrent access is managed by the ExecutorManager class.
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CODE>
        */

        /*
        //And this class is responsible to send and update the UI in the Main Thread.
        <Code>Handler handler = new Handler(Looper.getMainLooper());<Code>
        */
        //Now we can run the the method in a separate thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contacts);
            }
        });



    }

    public void deleteContacts(Contacts contacts){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contacts);
            }
        });
    }

    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }
}
