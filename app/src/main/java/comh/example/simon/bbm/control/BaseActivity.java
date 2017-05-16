package comh.example.simon.bbm.control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Simon on 2017/3/24.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
