package fashion.style.manicure.catalog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.startapp.android.publish.adsCommon.StartAppAd;

public class OfferActivity extends AppCompatActivity {
    Context context = this;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        mInterstitialAd = new InterstitialAd(this);
        String adOnBack = getString(R.string.banner_ad_backbutton);
        mInterstitialAd.setAdUnitId(adOnBack);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        String offer1avastring = getIntent().getExtras().getString("pict");
        ImageView offer1ava = (ImageView) findViewById(R.id.imageOfferAva);
        Picasso.with(context).load(offer1avastring).into(offer1ava);

        TextView offerTitleText = (TextView) findViewById(R.id.offerTitle);
        offerTitleText.setText(getIntent().getExtras().getString("name"));

        TextView offerDescText = (TextView) findViewById(R.id.textDescOffer);
        offerDescText.setText(getIntent().getExtras().getString("desc"));

    }

    public void onClick(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
//        StartAppAd.showAd(this);
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
