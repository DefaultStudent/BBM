package comh.example.simon.bbm.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import comh.example.simon.bbm.control.BaseActivity;
import comh.example.simon.bbm.control.MyDatabaseHelper;
import comh.example.simon.bbm.R;

public class LoginActivity extends BaseActivity {

    SQLiteDatabase db;

    private MyDatabaseHelper dbHelper;

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login, regis;

    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        //实例化
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember);
        login = (Button) findViewById(R.id.login);

        //记住密码
        boolean isRemember = pref.getBoolean("remember_password", false);//默认不选择记住密码
        if (isRemember) {
            //将账号和密码都放置到文本框中
            String account = pref.getString("account", "");
            String pwd = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(pwd);
            rememberPass.setChecked(true);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new MyDatabaseHelper(LoginActivity.this, "BBM.db", null, 2);
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                //查询功能的实现
                Cursor cursor = db.rawQuery("select*from users where account=? and pwd=?",new String[]{account, password});
                if (cursor.moveToNext()) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    String data = account;
                    String data1 = password;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("data" , data);
                    intent.putExtra("data1", data1);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"您输入的用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });

        regis = (Button) findViewById(R.id.register);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

}
