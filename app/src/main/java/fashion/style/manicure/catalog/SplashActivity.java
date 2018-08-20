package fashion.style.manicure.catalog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppSDK;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    Timer timer = new Timer();
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StartAppSDK.init(this, "207550666", true);
        cd = new ConnectionDetector(this);
        if (cd.isConnected()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(SplashActivity.this, R.string.neednetwork, Toast.LENGTH_SHORT).show();
            timer.schedule(new TimerTask() {
                public void run() {
                    SplashActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            finish();
                        }
                    });
                }
            }, 5000);

        }
    }
}




