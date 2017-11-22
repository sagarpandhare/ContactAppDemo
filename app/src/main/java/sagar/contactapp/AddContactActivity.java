package sagar.contactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class AddContactActivity extends AppCompatActivity {


    EditText edittextname,edittextnumber;
    Button buttonadd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        edittextname=findViewById(R.id.editTextName);
        edittextnumber=findViewById(R.id.editTextNumber);

        buttonadd=findViewById(R.id.buttonAdd);

        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String name=edittextname.getText().toString();
            String numbere=edittextnumber.getText().toString();

                Intent intent=new Intent();
                intent.putExtra("name",name);
                intent.putExtra("number",numbere);

          Contact contact=new Contact();
          contact.setName(name);
          contact.setNumbere(numbere);

          MyDataBase myDataBase=new MyDataBase(AddContactActivity.this);
//          long insertId=myDataBase.Insert(contact);

            setResult(RESULT_OK,intent);
            finish();

            }
        });
    }
}
