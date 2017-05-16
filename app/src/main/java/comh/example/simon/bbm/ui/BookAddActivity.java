package comh.example.simon.bbm.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comh.example.simon.bbm.control.BaseActivity;
import comh.example.simon.bbm.R;
import comh.example.simon.bbm.control.MyDatabaseHelper;

/**
 * Created by Simon on 2017/4/1.
 */

public class BookAddActivity extends BaseActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookadd);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        dbHelper = new MyDatabaseHelper(this, "BBM.db", null, 2);
        Button bookAdd = (Button) findViewById(R.id.bookadd_button);

        final EditText addName = (EditText) findViewById(R.id.bookadd_edit1);
        final EditText addType = (EditText) findViewById(R.id.bookadd_edit2);
        final EditText addAuthor = (EditText) findViewById(R.id.bookadd_edit3);
        final EditText addPublish = (EditText) findViewById(R.id.bookadd_edit4);
        final EditText addPrice = (EditText) findViewById(R.id.bookadd_edit5);

        bookAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
                /*String bookName = addName.getText().toString();
                String bookType = addType.getText().toString();
                String author = addAuthor.getText().toString();
                String publish = addPublish.getText().toString();
                String price = addPrice.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("bookName", bookName);
                values.put("bookType", bookType);
                values.put("author", author);
                values.put("publish", publish);
                values.put("price", price);
                db.insert("Book", null, values);
                Toast.makeText(BookAddActivity.this,"添加成功", Toast.LENGTH_SHORT).show();*/
            }
        });
    }

}
