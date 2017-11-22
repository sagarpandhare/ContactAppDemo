package sagar.contactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewContactActivity extends AppCompatActivity {

    TextView textViewName,textViewNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        textViewName=findViewById(R.id.textViewName);
        textViewNumber=findViewById(R.id.textViewNumber);

        String name=getIntent().getStringExtra("name");
        String number=getIntent().getStringExtra("number");

        textViewName.setText(name);
        textViewNumber.setText(number);

    }
}
