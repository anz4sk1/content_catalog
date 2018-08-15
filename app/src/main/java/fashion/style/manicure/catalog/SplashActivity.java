package fashion.style.manicure.catalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cd = new ConnectionDetector(this);
        if (cd.isConnected()) {
            Toast.makeText(SplashActivity.this, "work", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SplashActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(SplashActivity.this, "need network", Toast.LENGTH_SHORT).show();

        }
    }
}

