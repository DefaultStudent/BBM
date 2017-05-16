package comh.example.simon.bbm.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import comh.example.simon.bbm.R;
import comh.example.simon.bbm.control.BaseActivity;

/**
 * Created by Simon on 2017/5/11.
 */

public class BookQueryActivity extends BaseActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_query);
        setSupportActionBar(toolbar);
    }
}
