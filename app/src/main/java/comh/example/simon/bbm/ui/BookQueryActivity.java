package comh.example.simon.bbm.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comh.example.simon.bbm.R;
import comh.example.simon.bbm.control.BaseActivity;
import comh.example.simon.bbm.control.Book;
import comh.example.simon.bbm.control.BookAdapter;
import comh.example.simon.bbm.control.MyDatabaseHelper;

/**
 * Created by Simon on 2017/5/11.
 */

public class BookQueryActivity extends BaseActivity {

    private MyDatabaseHelper dbHelper;

    private List<Book> bookList = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_query);
        setSupportActionBar(toolbar);
        Button query_button = (Button) findViewById(R.id.query_button);
        final EditText id = (EditText) findViewById(R.id.query_edit1);

        initBooks();//初始化listview
        BookAdapter adapter = new BookAdapter(BookQueryActivity.this, R.layout.book_info, bookList);
        ListView listView = (ListView) findViewById(R.id.query_list);
        listView.setAdapter(adapter);

        //设置listview单击响应事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Book book = bookList.get(position);
                dbHelper = new MyDatabaseHelper(BookQueryActivity.this, "BBM.db", null, 2);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String bookid = book.getId().toString();
                Cursor cursor = db.rawQuery("select * from book where bookName = ?", new String[]{bookid});//查询语句
                    if (cursor.moveToNext()){
                        String bid = cursor.getString(cursor.getColumnIndex("bookId"));
                        String name = cursor.getString(cursor.getColumnIndex("bookName"));
                        String type = cursor.getString(cursor.getColumnIndex("bookType"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        String publish = cursor.getString(cursor.getColumnIndex("publisher"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));

                    //将查询出来的值传递到修改页面去
                    Intent intent = new Intent(BookQueryActivity.this, BookUpdateActivity.class);
                        intent.putExtra("id", bid);
                        intent.putExtra("name", name);
                        intent.putExtra("type", type);
                        intent.putExtra("author", author);
                        intent.putExtra("publish", publish);
                        intent.putExtra("price", price);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //查询按钮响应事件
        query_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (id.getText().toString().equals("")&&id.getText().toString().equals(null)){
                        Toast.makeText(BookQueryActivity.this, "请输入图书编号", Toast.LENGTH_SHORT).show();
                    }else {
                        dbHelper = new MyDatabaseHelper(BookQueryActivity.this, "BBM.db", null, 2);
                        SQLiteDatabase db = dbHelper.getReadableDatabase();
                        String bookid1 = id.getText().toString();
                        Cursor cursor = db.rawQuery("select * from book where bookId = ?", new String[]{bookid1});//查询语句

                        //将数据全部取出来
                        if (cursor.moveToNext()){
                            String id = cursor.getString(cursor.getColumnIndex("bookId"));
                            String name = cursor.getString(cursor.getColumnIndex("bookName"));
                            String type = cursor.getString(cursor.getColumnIndex("bookType"));
                            String author = cursor.getString(cursor.getColumnIndex("author"));
                            String publish = cursor.getString(cursor.getColumnIndex("publisher"));
                            String price = cursor.getString(cursor.getColumnIndex("price"));
                            Book book = new Book(id, name, type, author, publish, price);
                            bookList.add(book);
                        }else {
                            Toast.makeText(BookQueryActivity.this, "查无此书", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void initBooks(){
        dbHelper = new MyDatabaseHelper(BookQueryActivity.this, "BBM.db", null, 2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from book", null);//查询语句
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("bookId"));
            String name = cursor.getString(cursor.getColumnIndex("bookName"));
            String type = cursor.getString(cursor.getColumnIndex("bookType"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String publish = cursor.getString(cursor.getColumnIndex("publisher"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            Book book = new Book(id, name, type, author, publish, price);
            bookList.add(book);
        }
    }
}
