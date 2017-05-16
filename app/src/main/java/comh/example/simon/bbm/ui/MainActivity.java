package comh.example.simon.bbm.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import comh.example.simon.bbm.control.BaseActivity;
import comh.example.simon.bbm.control.MyDatabaseHelper;
import comh.example.simon.bbm.R;

/**
 * Created by Simon on 2017/3/24.
 */

    public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;

    SQLiteDatabase db;

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_2);
        setSupportActionBar(toolbar);

        try {
            Intent intent = getIntent();
            String data = intent.getStringExtra("data");
            String data1 = intent.getStringExtra("data1");
            dbHelper = new MyDatabaseHelper(MainActivity.this, "BBM.db", null, 1);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select name from users where pwd=? and account=?", new String[]{data1,data});
            if (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                setTitle("欢迎您 " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add:
                        Intent intent = new Intent(MainActivity.this, BookAddActivity.class);
                        startActivity(intent);
                        break;
                    case  R.id.query:
                        Intent intent1 = new Intent(MainActivity.this, BookQueryActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.delete:
                        Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.update:
                        Intent intent3 = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(intent3);
                        break;
                    default:
                }
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);

                break;
            case R.id.backup:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.settings:
                Toast.makeText(MainActivity.this, "你点击了设置按钮", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
