package school.project.contactsapplicaitonmanager;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import school.project.contactsapplicaitonmanager.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //here i am going to define Data sources, adapter and Binding

    //Data Source
    private ContactDatabase contactDatabase; //IDK why this obj is important
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();


    //Adapter
    private MyAdapter myAdapter;
    //Objects for DataBinding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //setContentView() method is a method that allows you to set the content view of an activity
        //or fragment with dataBinding Enabled
        handlers = new MainActivityClickHandler(this);


        mainBinding.setClickHandler(handlers);

        //RecyclerView
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        //Database
        contactDatabase = ContactDatabase.getInstance(this);

        //ViewModel
        MyViewModel viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);



        //Inserting a new Contact Object For Testing
//        Contacts c1 = new Contacts("Henok", "henok@gmail.com");

//        viewModel.addNewContact(c1);

        //Load the data From Room DB
        viewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {
                        contactsArrayList.clear();
                        for(Contacts c: contacts){
//                            Log.v("Tagy", c.getName());
                            contactsArrayList.add(c);
                        }

                        //this is the one which is responsible for Updating the UI
                        myAdapter.notifyDataSetChanged();

                    }
                });
        //Adapter
        myAdapter = new MyAdapter(contactsArrayList);

        //setting adapter
        recyclerView.setAdapter(myAdapter);

        //Swipe Delete

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {//the first parameter supports for drag and drop in this case we assign it to 0.



            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //if swipe left delete the item
                Contacts c = contactsArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteContact(c);
            }
        }).attachToRecyclerView(recyclerView);
    }
}