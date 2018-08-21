package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;
import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONObject;

public class IdeasActivity extends AppCompatActivity {
Context context = this;
    String[][] array = new String[50][3];
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);
        this.setTitle(R.string.Ideas);
        mInterstitialAd = new InterstitialAd(this);
        String adOnBack = getString(R.string.banner_ad_backbutton);
        mInterstitialAd.setAdUnitId(adOnBack);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final String answer = getIntent().getExtras().getString("answer");
        try {
            JSONObject obj = new JSONObject(answer);
            String ideas = obj.getString("ideas");
            JSONObject offersObj = new JSONObject(ideas);
            for (int x = 1; x < offersObj.length()+1; x++) {
                String ID = "id" + x;
                String id = offersObj.getString(ID);
                JSONObject objID = new JSONObject(id);
                array[x][0] = objID.getString("desc");
                array[x][1] = objID.getString("pict");
                array[x][2] = objID.getString("name");
            }
        }catch (Throwable t) {
            String ok;
        }
        for(int i = 1; i<11;i++){
            String ID = "imageView" + i;
            int resID = getResources().getIdentifier(ID, "id", getPackageName());
            ImageView IV = (ImageView) findViewById(resID);
            Picasso.with(context).load(array[i][1]).into(IV);
        }
    }
    public void nextPage(View v){
        String ID = v.getContentDescription().toString();
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(IdeasActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        startActivity(infoIntent);
    }
    public void onClick(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        StartAppAd.showAd(this);
        finish();
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
