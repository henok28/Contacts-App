package school.project.contactsapplicaitonmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import school.project.contactsapplicaitonmanager.databinding.ContactListItemBinding;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactsViewHolder> {
    ArrayList<Contacts> contacts;



    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }



    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false
        );
//        ContactsViewHolder viewHolder = new ContactsViewHolder(contactListItemBinding);
        return new ContactsViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        //Called by the recyclerView when it needs to display or update an item
        //at a specific location in the grid or list
        //it is called by each scroll to update the data of the new Item in the recyclerView
        Contacts currentContact = contacts.get(position);
        holder.contactListItemBinding.setContact(currentContact);


    }

    @Override
    public int getItemCount() {
        if(contacts !=null){
            return contacts.size();
        }else{
            return 0;
        }
    }


    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        notifyDataSetChanged();
        //This method informs the associated recyclerView that the underlying data set has changed,
        //and the recycler view refresh its view to reflect its changes.
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder{
        private ContactListItemBinding contactListItemBinding;


        public ContactsViewHolder(ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
