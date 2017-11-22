package sagar.contactapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView textViewname, textViewnumber;
    ContactAdaptor contactAdaptor;
    ArrayList<Contact> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewname = findViewById(R.id.textViewName);
        textViewnumber = findViewById(R.id.textViewNumber);

        listView = findViewById(R.id.listview);

//        Contact contact = new Contact();
//        contact.setName("Sagar");
//        contact.setNumbere("9860903042");
//
//        Contact contact1 = new Contact();
//        contact1.setName("Rohit");
//        contact1.setNumbere("9833900916");

//            list = new ArrayList<>();
//        list.add(contact);
//        list.add(contact1);

        MyDataBase myDataBase=new MyDataBase(MainActivity.this);

        contactAdaptor = new ContactAdaptor(MainActivity.this,myDataBase.getAllContact());
        listView.setAdapter(contactAdaptor);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           showPopupMenu(view,i);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectItem = item.getItemId();

        if (selectItem == R.id.actionAdd) {

            addContact(textViewname,textViewnumber);
            Toast.makeText(MainActivity.this, "add item", Toast.LENGTH_SHORT).show();

        } else if (selectItem == R.id.actionSetting) {
            Toast.makeText(MainActivity.this, "setting item", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Options");


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position=adapterContextMenuInfo.position;

        Contact contact= (Contact) contactAdaptor.getItem(position);

        MyDataBase myDataBase=new MyDataBase(MainActivity.this);
        int row =myDataBase.delete(contact.getId());

        if(row>0)
        {
            contactAdaptor.removeContact(position);
            contactAdaptor.notifyDataSetChanged();
        }


        int selectedItem = item.getItemId();
        if (selectedItem == R.id.actionDelete) ;
        {
            Toast.makeText(MainActivity.this, "delete item", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }


     public void showPopupMenu(View view, final int position)
    {
        PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);
        popupMenu.inflate(R.menu.popup_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int selectedIteam = menuItem.getItemId();

                if (selectedIteam == R.id.actionView) {

                    Contact contact=(Contact)contactAdaptor.getItem(position);
                    ViewContact(contact);
                    Toast.makeText(MainActivity.this, "this is view", Toast.LENGTH_SHORT).show();
                }

                else if (selectedIteam == R.id.actionCall) {
                    Toast.makeText(MainActivity.this, "this is call", Toast.LENGTH_SHORT).show();
                    Contact contact=(Contact) contactAdaptor.getItem(position);
                    call(contact);

                }

                else if (selectedIteam == R.id.actionEdit) {
                    Toast.makeText(MainActivity.this, "this is edit", Toast.LENGTH_SHORT).show();

                }

                return false;
            }

        });
        popupMenu.show();
    }



   public void ViewContact(Contact contact)
   {
      String name=contact.getName();
      String number=contact.getNumbere();

       Intent intent=new Intent(MainActivity.this,ViewContactActivity.class);
       intent.putExtra("name",name);
       intent.putExtra("number",number);

       startActivity(intent);
   }

   public  void addContact(TextView name, TextView phone) {
       Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
       startActivityForResult(intent, 1);

   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                String name=data.getStringExtra("name");
                String number=data.getStringExtra("number");

                Contact contact=new Contact();
                contact.setName(name);
                contact.setNumbere(number);

                contactAdaptor.addContact(contact);
                contactAdaptor.notifyDataSetChanged();

                MyDataBase myDataBase=new MyDataBase(MainActivity.this);
                myDataBase.Insert(contact);
            }
        }
    }

    public void call(Contact contact) {
        String number = contact.getNumbere();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + number));
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ;
        String permission[] = {Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(MainActivity.this, permission, 123);
    }
    startActivity(intent);

}

//public int edit()
//{
//
//}

}