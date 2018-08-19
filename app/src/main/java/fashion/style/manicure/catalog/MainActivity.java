package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.adsCommon.StartAppAd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private AdView mAdView;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        new GetTask().execute();

        Context context = this;
        View view1 = (View) findViewById(R.id.view1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, IdeasActivity.class);
                intent.putExtra("answer", answer);
                startActivity(intent);


            }
        });

        View view2 = (View) findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        View view3 = (View) findViewById(R.id.view3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OurAppsActivity.class);
                intent.putExtra("answer", answer);
                startActivity(intent);
            }
        });

        View view4 = (View) findViewById(R.id.view4);
        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }

    private class GetTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getAnswer();
            return null;
        }
    }
    public void getAnswer() {
        try {
            String urljson = getString(R.string.json);
            URL url = new URL(urljson);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            final int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));
                answer = "";
                String line = null;
                while ((line = reader.readLine()) != null) {
                    answer += line;
                }
                reader.close();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        mAdView.resume();
        super.onResume();
    }
}

