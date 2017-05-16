package comh.example.simon.bbm.ui;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comh.example.simon.bbm.control.BaseActivity;
import comh.example.simon.bbm.R;
import comh.example.simon.bbm.control.MyDatabaseHelper;

/**
 * Created by Simon on 2017/3/24.
 */

public class RegisterActivity extends BaseActivity {

    private MyDatabaseHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new MyDatabaseHelper(this, "BBM.db", null, 2);
        Button regis = (Button) findViewById(R.id.reg);

        final EditText accountEdit = (EditText) findViewById(R.id.account_1);
        final EditText passwordEdit = (EditText) findViewById(R.id.password_1);
        final EditText nameEdit = (EditText) findViewById(R.id.edit_name);
        final EditText sexEdit = (EditText) findViewById(R.id.edit_sex);
        final EditText ageEdit = (EditText) findViewById(R.id.edit_age);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
                String account = accountEdit.getText().toString();
                String pwd = passwordEdit.getText().toString();
                String name = nameEdit.getText().toString();
                String sex = sexEdit.getText().toString();
                String age = ageEdit.getText().toString();


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("account", account);
                values.put("pwd", pwd);
                values.put("name", name);
                values.put("sex", sex);
                values.put("age", age);
                db.insert("users", null, values);
                Toast.makeText(RegisterActivity.this,"注册成功，请返回登陆界面登陆", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
