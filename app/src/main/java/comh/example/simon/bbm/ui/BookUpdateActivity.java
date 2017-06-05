package comh.example.simon.bbm.ui;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import comh.example.simon.bbm.R;
import comh.example.simon.bbm.control.Book;
import comh.example.simon.bbm.control.MyDatabaseHelper;

/**
 * Created by Simon on 2017/5/15.
 */

public class BookUpdateActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    //实例化组件
    private EditText editText_id, editText_name, editText_type, editText_author, editText_publish, editText_price;
    private Button button_Update, button_Delete;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookupdate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_update);
        setSupportActionBar(toolbar);

        dbHelper = new MyDatabaseHelper(BookUpdateActivity.this, "BBM.db", null, 2);

        //获取参数
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        final String name = intent.getStringExtra("name");
        final String type = intent.getStringExtra("type");
        final String author = intent.getStringExtra("author");
        final String publish = intent.getStringExtra("publish");
        final String price = intent.getStringExtra("price");

        editText_id = (EditText) findViewById(R.id.update_id);
        editText_name = (EditText) findViewById(R.id.update_name);
        editText_type = (EditText) findViewById(R.id.update_type);
        editText_author = (EditText) findViewById(R.id.update_author);
        editText_publish = (EditText) findViewById(R.id.update_publish);
        editText_price = (EditText) findViewById(R.id.update_price);

        //将查询页面查询出来的值放置到EditText组件中
        editText_id.setText(id);
        editText_name.setText(name);
        editText_type.setText(type);
        editText_author.setText(author);
        editText_publish.setText(publish);
        editText_price.setText(price);

        button_Update = (Button) findViewById(R.id.update_button);

        //设置监听器
        button_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("bookName", editText_name.getText().toString());
                    values.put("bookType", editText_type.getText().toString());
                    values.put("author", editText_author.getText().toString());
                    values.put("publisher", editText_publish.getText().toString());
                    values.put("price", editText_price.getText().toString());
                    db.update("book", values, "bookId=?", new String[]{editText_id.getText().toString()});
                    Toast.makeText(BookUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(BookUpdateActivity.this, BookQueryActivity.class);
                    startActivity(intent2);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        button_Delete = (Button) findViewById(R.id.delete_button);

        //设置监听器
        button_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookUpdateActivity.this);
                builder.setTitle("确定删除");
                builder.setMessage("你确定删除"+ editText_name.getText().toString()+"的信息么?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete("book", "bookId = ?", new String[]{id});
                        Toast.makeText(BookUpdateActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(BookUpdateActivity.this, BookQueryActivity.class);
                        startActivity(intent1);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
