package comh.example.simon.bbm.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.style.EasyEditSpan;
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

        final EditText addId = (EditText) findViewById(R.id.bookid_edit);
        final EditText addName = (EditText) findViewById(R.id.bookadd_edit1);
        final EditText addType = (EditText) findViewById(R.id.bookadd_edit2);
        final EditText addAuthor = (EditText) findViewById(R.id.bookadd_edit3);
        final EditText addPublish = (EditText) findViewById(R.id.bookadd_edit4);
        final EditText addPrice = (EditText) findViewById(R.id.bookadd_edit5);

        bookAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
                //获取添加的值
                String bookId = addId.getText().toString();
                String bookName = addName.getText().toString();
                String bookType = addType.getText().toString();
                String author = addAuthor.getText().toString();
                String publisher = addPublish.getText().toString();
                String price = addPrice.getText().toString();

                //将获取到的值添加到数据库中
                ContentValues values = new ContentValues();
                values.put("bookId", bookId);
                values.put("bookName", bookName);
                values.put("bookType", bookType);
                values.put("author", author);
                values.put("publisher", publisher);
                values.put("price", price);
                dbHelper.getWritableDatabase().insert("Book", null, values);
                Toast.makeText(BookAddActivity.this,"添加成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
