package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String[][] array = new String[50][3];
    String id;
    private AdView mAdView;
    String answer;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String adMobApp = getString(R.string.banner_ad_app);
//        MobileAds.initialize(this, adMobApp);
//        mInterstitialAd = new InterstitialAd(this);
//        String adOnBack = getString(R.string.banner_ad_backbutton);
//        mInterstitialAd.setAdUnitId(adOnBack);
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        new GetTask().execute();
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
//        view2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
//                intent.putExtra("answer", answer);
//                startActivity(intent);
//            }
//        });
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

//            final String answer = getIntent().getExtras().getString("answer");
            try {
                JSONObject obj = new JSONObject(answer);
                String ideas = obj.getString("ideas");
                JSONObject offersObj = new JSONObject(ideas);
                for (int x = 1; x < offersObj.length()+1; x++) {
                    String ID = "id" + x;
                    id = offersObj.getString(ID);
                    JSONObject objID = new JSONObject(id);
                    array[x][0] = objID.getString("desc");
                    array[x][1] = objID.getString("pict");
                    array[x][2] = objID.getString("name");
                    Log.d("idgg22", ID);
                }
            }catch (Throwable t) {
                String ok;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        intent.putExtra("answer", answer);
        startActivity(intent);
    }

    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
//        StartAppAd.onBackPressed(this);
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onResume() {
//        mAdView.resume();
        super.onResume();
    }
}

