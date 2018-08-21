package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONObject;

public class OurAppsActivity extends AppCompatActivity {
    Context context = this;
    String[][] array2 = new String[3][5];
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourapps);
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
            String apps = obj.getString("apps");
            for (int x = 1; x < 3; x++) {
                String ID = "id" + x;
                JSONObject offersObj = new JSONObject(apps);
                String id = offersObj.getString(ID);
                JSONObject objID = new JSONObject(id);
                array2[x][0] = objID.getString("desc");
                array2[x][1] = objID.getString("link");
                array2[x][2] = objID.getString("name");
                array2[x][3] = objID.getString("pict");
            }
        }catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + answer + "\"");
        }
        // 1 app
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        Picasso.with(context).load(array2[1][3]).into(imageView1);

        View view = (View) findViewById(R.id.view1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(array2[1][1]));
                startActivity(browserIntent);
            }
        });
        TextView offertitle1 = (TextView) findViewById(R.id.textViewTitle1);
        offertitle1.setText(array2[1][2]);

        TextView offerdesc1 = (TextView) findViewById(R.id.textViewDesc1);
        offerdesc1.setText(array2[1][0]);
        // 2app
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(context).load(array2[2][3]).into(imageView2);
        View view2 = (View) findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(array2[2][1]));
                startActivity(browserIntent);
            }
        });
        TextView offertitle2 = (TextView) findViewById(R.id.textViewTitle2);
        offertitle2.setText(array2[2][2]);
        TextView offerdesc2 = (TextView) findViewById(R.id.textViewDesc2);
        offerdesc2.setText(array2[2][0]);
    }
    public void nextPage(View v){
        String ID = v.getContentDescription().toString();
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(OurAppsActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array2[i][0]);
        infoIntent.putExtra("link", array2[i][1]);
        infoIntent.putExtra("name", array2[i][2]);
        infoIntent.putExtra("pict", array2[i][3]);
        startActivity(infoIntent);
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
