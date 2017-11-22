package sagar.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdaptor extends BaseAdapter {
    ArrayList<Contact> list;
    Context context;

    public ContactAdaptor(Context context,ArrayList<Contact> l )
    {
        this.context=context;
        this.list=l;
    }


    @Override
    public int getCount() {
        return list.size();

    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Contact contact=list.get(position);
        LayoutInflater inflater=LayoutInflater.from(context);
        View contactView=inflater.inflate(R.layout.list_item,viewGroup,false);

        TextView textViewName=contactView.findViewById(R.id.textViewName);
        TextView textViewNumber=contactView.findViewById(R.id.textViewNumber);

        textViewName.setText(contact.getName());
        textViewNumber.setText(contact.getNumbere());

        return contactView;

    }

    public void addContact(Contact contact)
    {
        list.add(contact);
    }

    public  void removeContact(int position)
    {
        list.remove(position);
    }
}
