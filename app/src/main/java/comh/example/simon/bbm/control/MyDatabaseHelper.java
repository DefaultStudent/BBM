package comh.example.simon.bbm.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Simon on 2017/3/24.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USERS = "create table users(" + "account integer primary key autoincrement," + "pwd text," + "name text," + "sex text," + "age integer)";
    public static final String CREATE_BOOKS = "create table Book(" + "bookName text primary key," + "bookType text," + "author text," + "publisher text," + "price text)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_BOOKS);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists Book");
        onCreate(db);
    }
}
