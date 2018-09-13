package fashion.style.manicure.catalog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.startapp.android.publish.adsCommon.StartAppAd;

public class OfferActivity extends AppCompatActivity implements OnClickListener {
    String saved1, saved2, saved3, saved4, saved5, saved6, saved7, saved8, saved9, saved10;
    String currentOfferID;
    final String LOG_TAG = "myLogs";

    DBHelper dbHelper;
    Button btnAddID, btnClear;

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

        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
        currentOfferID = getIntent().getExtras().getString("id");
        btnAddID = (Button) findViewById(R.id.btnAdd);
        btnAddID.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);


        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        new Thread(new Runnable() {
            public void run() {

                Cursor c = db.query("mytable", null, null, null, null, null, null);

                if (c.moveToPosition(0)) {

                    int nameColIndex = c.getColumnIndex("name");
                    saved1 = c.getString(nameColIndex);
                    Log.d("position1", saved1);

                    if (saved1.equals(currentOfferID)) {
                        btnAddID.setVisibility(View.GONE);
                        btnClear.setVisibility(View.VISIBLE);
                    } else {
                        if (c.moveToPosition(1)) {
                            int nameColIndex2 = c.getColumnIndex("name");
                            saved2 = c.getString(nameColIndex2);
                            Log.d("position2", saved2);
                            if (saved2.equals(currentOfferID)) {
                                btnAddID.setVisibility(View.GONE);
                                btnClear.setVisibility(View.VISIBLE);
                            } else {
                                if (c.moveToPosition(2)) {
                                    int nameColIndex3 = c.getColumnIndex("name");
                                    saved3 = c.getString(nameColIndex3);
                                    Log.d("position3", saved3);
                                    if (saved3.equals(currentOfferID)) {
                                        btnAddID.setVisibility(View.GONE);
                                        btnClear.setVisibility(View.VISIBLE);
                                    } else {
                                        if (c.moveToPosition(3)) {
                                            int nameColIndex4 = c.getColumnIndex("name");
                                            saved4 = c.getString(nameColIndex4);
                                            Log.d("position3", saved4);
                                            if (saved4.equals(currentOfferID)) {
                                                btnAddID.setVisibility(View.GONE);
                                                btnClear.setVisibility(View.VISIBLE);
                                            } else {
                                                if (c.moveToPosition(4)) {
                                                    int nameColIndex5 = c.getColumnIndex("name");
                                                    saved5 = c.getString(nameColIndex5);
                                                    Log.d("position3", saved5);
                                                    if (saved5.equals(currentOfferID)) {
                                                        btnAddID.setVisibility(View.GONE);
                                                        btnClear.setVisibility(View.VISIBLE);
                                                    } else {
                                                        if (c.moveToPosition(5)) {
                                                            int nameColIndex6 = c.getColumnIndex("name");
                                                            saved6 = c.getString(nameColIndex6);
                                                            Log.d("position3", saved6);
                                                            if (saved6.equals(currentOfferID)) {
                                                                btnAddID.setVisibility(View.GONE);
                                                                btnClear.setVisibility(View.VISIBLE);
                                                            } else {
                                                                if (c.moveToPosition(6)) {
                                                                    int nameColIndex7 = c.getColumnIndex("name");
                                                                    saved7 = c.getString(nameColIndex7);
                                                                    Log.d("position3", saved7);
                                                                    if (saved7.equals(currentOfferID)) {
                                                                        btnAddID.setVisibility(View.GONE);
                                                                        btnClear.setVisibility(View.VISIBLE);
                                                                    } else {
                                                                        if (c.moveToPosition(7)) {
                                                                            int nameColIndex8 = c.getColumnIndex("name");
                                                                            saved8 = c.getString(nameColIndex8);
                                                                            Log.d("position3", saved8);
                                                                            if (saved8.equals(currentOfferID)) {
                                                                                btnAddID.setVisibility(View.GONE);
                                                                                btnClear.setVisibility(View.VISIBLE);
                                                                            } else {
                                                                                if (c.moveToPosition(8)) {
                                                                                    int nameColIndex9 = c.getColumnIndex("name");
                                                                                    saved9 = c.getString(nameColIndex9);
                                                                                    Log.d("position3", saved9);
                                                                                    if (saved9.equals(currentOfferID)) {
                                                                                        btnAddID.setVisibility(View.GONE);
                                                                                        btnClear.setVisibility(View.VISIBLE);
                                                                                    } else {
                                                                                        if (c.moveToPosition(9)) {
                                                                                            int nameColIndex10 = c.getColumnIndex("name");
                                                                                            saved10 = c.getString(nameColIndex10);
                                                                                            Log.d("position3", saved10);
                                                                                            if (saved10.equals(currentOfferID)) {
                                                                                                btnAddID.setVisibility(View.GONE);
                                                                                                btnClear.setVisibility(View.VISIBLE);
                                                                                            } else {
                                                                                                btnClear.setVisibility(View.GONE);
                                                                                                btnAddID.setVisibility(View.VISIBLE);
                                                                                            }
                                                                                        } else {
                                                                                            btnClear.setVisibility(View.GONE);
                                                                                            btnAddID.setVisibility(View.VISIBLE);
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    btnClear.setVisibility(View.GONE);
                                                                                    btnAddID.setVisibility(View.VISIBLE);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            btnClear.setVisibility(View.GONE);
                                                                            btnAddID.setVisibility(View.VISIBLE);
                                                                        }

                                                                    }
                                                                } else {
                                                                    btnClear.setVisibility(View.GONE);
                                                                    btnAddID.setVisibility(View.VISIBLE);
                                                                }
                                                            }
                                                        } else {
                                                            btnClear.setVisibility(View.GONE);
                                                            btnAddID.setVisibility(View.VISIBLE);
                                                        }
                                                    }
                                                } else {
                                                    btnClear.setVisibility(View.GONE);
                                                    btnAddID.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        } else {
                                            btnClear.setVisibility(View.GONE);
                                            btnAddID.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } else {
                                    btnClear.setVisibility(View.GONE);
                                    btnAddID.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            btnClear.setVisibility(View.GONE);
                            btnAddID.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    btnClear.setVisibility(View.GONE);
                    btnAddID.setVisibility(View.VISIBLE);
                    c.close();
                    dbHelper.close();
                }
            }
        }).start();
    }

    public void onClick1(View view) {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        } else {
//            Log.d("TAG", "The interstitial wasn't loaded yet.");
//        }
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

    public void onClick (View view) {

        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String name = getIntent().getExtras().getString("id");
        Log.d("idgg5544", name);


        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        switch (view.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "--- Insert in mytable: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение

                cv.put("name", name);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                btnAddID.setVisibility(View.GONE);
                btnClear.setVisibility(View.VISIBLE);
                break;

//            case R.id.btnRead:
//                Log.d(LOG_TAG, "--- Rows in mytable: ---");
//                // делаем запрос всех данных из таблицы mytable, получаем Cursor
//                Cursor c = db.query("mytable", null, null, null, null, null, null);
//
//                // ставим позицию курсора на первую строку выборки
//                // если в выборке нет строк, вернется false
//                if (c.moveToFirst()) {
//
//                    // определяем номера столбцов по имени в выборке
//                    int idColIndex = c.getColumnIndex("id");
//                    int nameColIndex = c.getColumnIndex("name");
//                    int emailColIndex = c.getColumnIndex("email");
//
//                    do {
//                        // получаем значения по номерам столбцов и пишем все в лог
//                        Log.d(LOG_TAG,
//                                "ID = " + c.getInt(idColIndex) +
//                                        ", name = " + c.getString(nameColIndex) +
//                                        ", email = " + c.getString(emailColIndex));
//                        // переход на следующую строку
//                        // а если следующей нет (текущая - последняя), то false - выходим из цикла
//                    } while (c.moveToNext());
//                } else
//                    Log.d(LOG_TAG, "0 rows");
//                c.close();
//                break;
            case R.id.btnClear:
//                Log.d(LOG_TAG, "--- Clear mytable: ---");
//                // удаляем все записи
//                int clearCount = db.delete("mytable", "name", null);
//                Log.d(LOG_TAG, "deleted rows count = " + clearCount);




                db.delete("mytable","name=?", new String[]{currentOfferID});

                btnAddID.setVisibility(View.VISIBLE);
                btnClear.setVisibility(View.GONE);
                break;
        }
        // закрываем подключение к БД
        dbHelper.close();

    }



    static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
