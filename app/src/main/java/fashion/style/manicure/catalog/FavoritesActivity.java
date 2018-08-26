package fashion.style.manicure.catalog;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONObject;

public class FavoritesActivity extends AppCompatActivity {
    String[][] array = new String[50][3];
    String[] array2 = new String[50];
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    OfferActivity.DBHelper dbHelper;
    final String LOG_TAG = "myLogs";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setTitle(R.string.favorites);

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
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable", null, null, null, null, null, null);

                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {

                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int nameColIndex = c.getColumnIndex("name");
                    int emailColIndex = c.getColumnIndex("email");
                    int i = 0;
                    do {
                        String aString = c.getString(nameColIndex);
                       // Log.d("idgg567", aString);
                        array2[i] = aString;
                        i++;


                        //Log.d("idgg777", array[0]);
                        // получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + c.getInt(idColIndex) +
                                        ", name = " + c.getString(nameColIndex) +
                                        ", email = " + c.getString(emailColIndex));
                        // переход на следующую строку
                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();

                TextView text3 = (TextView) findViewById(R.id.textView3);
                text3.setText(array2[2]);




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
}
