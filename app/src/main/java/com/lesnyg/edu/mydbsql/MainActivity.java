package com.lesnyg.edu.mydbsql;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        Button buttonread = findViewById(R.id.buttonread);
        buttonread.setOnClickListener(this);
        Button buttonupdata = findViewById(R.id.buttonupdata);
        buttonupdata.setOnClickListener(this);
        Button buttondele = findViewById(R.id.buttondele);
        buttondele.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        EditText editTextcountry = findViewById(R.id.editTextcountry);
        EditText editTextcapital = findViewById(R.id.editTextcapital);

        String country = editTextcountry.getText().toString();
        String capital = editTextcapital.getText().toString();

        TextView tvResult = findViewById(R.id.textViewResult);
        int id;
        switch (v.getId()){
            case R.id.buttoninsert:
                mdb.execSQL("INSERT INTO awe_country VALUES(null,'"+country+"','"+capital+"');");
                break;
            case R.id.buttonread:

                String query = "SELECT * FROM awe_country";  //order by _id desc
                Cursor cursor = mdb.rawQuery(query, null);
                String str = "";
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    country = cursor.getString(cursor.getColumnIndex("country"));
                    capital = cursor.getString(2);
                    str += (id + " : " + country + " - " + capital + "\n");
                    tvResult.setText(str);
                    }
                break;
            case R.id.buttonupdata:
                String updata ="UPDATE awe_country SET capital='"+capital+"' WHERE country='"+country+"'";
                mdb.execSQL(updata);
                break;
            case R.id.buttondele:
                String dele = "DELETE FROM awe_country WHERE country='"+country+"'";
                mdb.execSQL(dele);
                break;
        }


    }
}