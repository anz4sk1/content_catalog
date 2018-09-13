package fashion.style.manicure.catalog;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONObject;

import java.util.Arrays;

public class FavoritesActivity extends AppCompatActivity {

    Button gonext;
    String[][] array = new String[50][3];
    String[] array2 = new String[50];
    Context context = this;
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    OfferActivity.DBHelper dbHelper;
    final String LOG_TAG = "myLogs";
    String id;
    String saved1, saved2, saved3, saved4, saved5, saved6, saved7, saved8, saved9, saved10;
    ConstraintLayout layoutOffer1, layoutOffer2, layoutOffer3, layoutOffer4, layoutOffer5, layoutOffer6, layoutOffer7, layoutOffer8, layoutOffer9, layoutOffer10;
    ImageView pictOffer1, pictOffer2, pictOffer3, pictOffer4, pictOffer5, pictOffer6, pictOffer7, pictOffer8, pictOffer9, pictOffer10;
    TextView nameOffer1, nameOffer2, nameOffer3, nameOffer4, nameOffer5, nameOffer6, nameOffer7, nameOffer8, nameOffer9, nameOffer10;
    TextView descOffer1, descOffer2, descOffer3, descOffer4, descOffer5, descOffer6, descOffer7, descOffer8, descOffer9, descOffer10;
    Button btnAddID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setTitle(R.string.favorites);



        layoutOffer1 = (ConstraintLayout) findViewById(R.id.layoutOffer1);
        layoutOffer2 = (ConstraintLayout) findViewById(R.id.layoutOffer2);
        layoutOffer3 = (ConstraintLayout) findViewById(R.id.layoutOffer3);
        layoutOffer4 = (ConstraintLayout) findViewById(R.id.layoutOffer4);
        layoutOffer5 = (ConstraintLayout) findViewById(R.id.layoutOffer5);
        layoutOffer6 = (ConstraintLayout) findViewById(R.id.layoutOffer6);
        layoutOffer7 = (ConstraintLayout) findViewById(R.id.layoutOffer7);
        layoutOffer8 = (ConstraintLayout) findViewById(R.id.layoutOffer8);
        layoutOffer9 = (ConstraintLayout) findViewById(R.id.layoutOffer9);
        layoutOffer10 = (ConstraintLayout) findViewById(R.id.layoutOffer10);

        pictOffer1 = (ImageView) findViewById(R.id.imageView1);
        pictOffer2 = (ImageView) findViewById(R.id.imageView2);
        pictOffer3 = (ImageView) findViewById(R.id.imageView3);
        pictOffer4 = (ImageView) findViewById(R.id.imageView4);
        pictOffer5 = (ImageView) findViewById(R.id.imageView5);
        pictOffer6 = (ImageView) findViewById(R.id.imageView6);
        pictOffer7 = (ImageView) findViewById(R.id.imageView7);
        pictOffer8 = (ImageView) findViewById(R.id.imageView8);
        pictOffer9 = (ImageView) findViewById(R.id.imageView9);
        pictOffer10 = (ImageView) findViewById(R.id.imageView10);

        nameOffer1 = (TextView) findViewById(R.id.textViewTitle1);
        nameOffer2 = (TextView) findViewById(R.id.textViewTitle2);
        nameOffer3 = (TextView) findViewById(R.id.textViewTitle3);
        nameOffer4 = (TextView) findViewById(R.id.textViewTitle4);
        nameOffer5 = (TextView) findViewById(R.id.textViewTitle5);
        nameOffer6 = (TextView) findViewById(R.id.textViewTitle6);
        nameOffer7 = (TextView) findViewById(R.id.textViewTitle7);
        nameOffer8 = (TextView) findViewById(R.id.textViewTitle8);
        nameOffer9 = (TextView) findViewById(R.id.textViewTitle9);
        nameOffer10 = (TextView) findViewById(R.id.textViewTitle10);

        descOffer1 = (TextView) findViewById(R.id.textViewDesc1);
        descOffer2 = (TextView) findViewById(R.id.textViewDesc2);
        descOffer3 = (TextView) findViewById(R.id.textViewDesc3);
        descOffer4 = (TextView) findViewById(R.id.textViewDesc4);
        descOffer5 = (TextView) findViewById(R.id.textViewDesc5);
        descOffer6 = (TextView) findViewById(R.id.textViewDesc6);
        descOffer7 = (TextView) findViewById(R.id.textViewDesc7);
        descOffer8 = (TextView) findViewById(R.id.textViewDesc8);
        descOffer9 = (TextView) findViewById(R.id.textViewDesc9);
        descOffer10 = (TextView) findViewById(R.id.textViewDesc10);



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

        dbHelper = new OfferActivity.DBHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        new Thread(new Runnable() {
            public void run(){

                Cursor c = db.query("mytable", null, null, null, null, null, null);

                if (c.moveToPosition(0)) {

                    int nameColIndex = c.getColumnIndex("name");
                    saved1 = c.getString(nameColIndex);
                    Log.d("position1", saved1);

                    if (saved1 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved1);
                                layoutOffer1.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer1);
                                nameOffer1.setText(array[i][2]);
                                descOffer1.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer1.setVisibility(View.GONE);


                if (c.moveToPosition(1)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved2 = c.getString(nameColIndex);
                    Log.d("position2", saved2);
                    if (saved2 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved2);
                                layoutOffer2.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer2);
                                nameOffer2.setText(array[i][2]);
                                descOffer2.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer2.setVisibility(View.GONE);


                if (c.moveToPosition(2)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved3 = c.getString(nameColIndex);
                    Log.d("position3", saved3);
                    if (saved3 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved3);
                                layoutOffer3.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer3);
                                nameOffer3.setText(array[i][2]);
                                descOffer3.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer3.setVisibility(View.GONE);

                if (c.moveToPosition(3)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved4 = c.getString(nameColIndex);
                    Log.d("position3", saved4);
                    if (saved4 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved4);
                                layoutOffer4.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer4);
                                nameOffer4.setText(array[i][2]);
                                descOffer4.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer4.setVisibility(View.GONE);

                if (c.moveToPosition(4)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved5 = c.getString(nameColIndex);
                    Log.d("position3", saved5);
                    if (saved5 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved5);
                                layoutOffer5.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer5);
                                nameOffer5.setText(array[i][2]);
                                descOffer5.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer5.setVisibility(View.GONE);

                if (c.moveToPosition(5)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved6 = c.getString(nameColIndex);
                    Log.d("position3", saved6);
                    if (saved6 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved6);
                                layoutOffer6.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer6);
                                nameOffer6.setText(array[i][2]);
                                descOffer6.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer6.setVisibility(View.GONE);

                if (c.moveToPosition(6)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved7 = c.getString(nameColIndex);
                    Log.d("position3", saved7);
                    if (saved7 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved7);
                                layoutOffer7.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer7);
                                nameOffer7.setText(array[i][2]);
                                descOffer7.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer7.setVisibility(View.GONE);

                if (c.moveToPosition(7)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved8 = c.getString(nameColIndex);
                    Log.d("position3", saved8);
                    if (saved8 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved8);
                                layoutOffer8.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer8);
                                nameOffer8.setText(array[i][2]);
                                descOffer8.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer8.setVisibility(View.GONE);

                if (c.moveToPosition(8)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved9 = c.getString(nameColIndex);
                    Log.d("position3", saved9);
                    if (saved9 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved9);
                                layoutOffer9.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer9);
                                nameOffer9.setText(array[i][2]);
                                descOffer9.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer9.setVisibility(View.GONE);

                if (c.moveToPosition(9)) {
                    int nameColIndex = c.getColumnIndex("name");
                    saved10 = c.getString(nameColIndex);
                    Log.d("position3", saved10);
                    if (saved10 != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(saved3);
                                layoutOffer10.setVisibility(View.VISIBLE);
                                String pict = array[i][1];
                                Picasso.with(context).load(pict).into(pictOffer10);
                                nameOffer10.setText(array[i][2]);
                                descOffer10.setText(array[i][0]);
                            }
                        });
                    }
                } else
                    layoutOffer10.setVisibility(View.GONE);
                c.close();






                // закрываем подключение к БД
                dbHelper.close();
            }
        }).start();


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
    public void nextPage(View v){
        String ID = saved1;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage2(View v){
        String ID = saved2;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage3(View v){
        String ID = saved3;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage4(View v){
        String ID = saved4;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage5(View v){
        String ID = saved5;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage6(View v){
        String ID = saved6;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage7(View v){
        String ID = saved7;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage8(View v){
        String ID = saved8;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage9(View v){
        String ID = saved9;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }
    public void nextPage10(View v){
        String ID = saved10;
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(FavoritesActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);
        infoIntent.putExtra("id", ID);
        startActivity(infoIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        finish();
//        startActivity(getIntent());
    }
    public void onClickUpdate(View view){
        finish();
        startActivity(getIntent());
    }
}
