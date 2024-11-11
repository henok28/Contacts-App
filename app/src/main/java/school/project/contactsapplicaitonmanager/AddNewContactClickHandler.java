package school.project.contactsapplicaitonmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
    MyViewModel myViewModel;
    Contacts contact;
    Context context;

    public AddNewContactClickHandler(Contacts contact, Context context, MyViewModel myViewModel) {
        this.contact = contact;
        this.context = context;
        this.myViewModel = myViewModel;
    }

    public void onSubmitBtnClicked(View view){
        if (contact.getEmail() == null || contact.getName() == null){
            Toast.makeText(context, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("Name", contact.getName());
//            i.putExtra("Email", contact.getEmail());
//            context.startActivity(i);

            Contacts c = new Contacts(
                    contact.getName(),
                    contact.getEmail()
            );

            myViewModel.addNewContact(c);
            context.startActivity(i);
        }
    }
}
