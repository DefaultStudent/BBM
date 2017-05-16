package comh.example.simon.bbm.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import comh.example.simon.bbm.R;

/**
 * Created by Simon on 2017/5/15.
 */

public class BookUpdateActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookupdate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_update);
        setSupportActionBar(toolbar);
    }
}
