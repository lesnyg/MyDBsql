package com.lesnyg.edu.mydbsql;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    MyDBOpenHelper dbHelper;
    SQLiteDatabase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDBOpenHelper(this, "awe.db", null, 1);
        mdb = dbHelper.getWritableDatabase();

        Button buttoninsert = findViewById(R.id.buttoninsert);
        buttoninsert.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        EditText editTextcountry = findViewById(R.id.editTextcountry);
        EditText editTextcapital = findViewById(R.id.editTextcapital);

        String country = editTextcountry.getText().toString();
        String capital = editTextcapital.getText().toString();

        mdb.execSQL("INSERT INTO awe_country VALUES(null,'"+country+"','"+capital+"');");

    }
}