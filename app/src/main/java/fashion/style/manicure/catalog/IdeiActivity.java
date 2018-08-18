package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class IdeiActivity extends AppCompatActivity {
Context context = this;
    String[][] array = new String[11][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idei);
        this.setTitle(R.string.Idei);

        final String answer = getIntent().getExtras().getString("answer");
        try {
            JSONObject obj = new JSONObject(answer);
            String ideas = obj.getString("ideas");
            Log.e("offemainmenu", ideas);
            for (int x = 1; x < 11; x++) {
                String ID = "id" + x;
                JSONObject offersObj = new JSONObject(ideas);
                String id = offersObj.getString(ID);
                JSONObject objID = new JSONObject(id);
                array[x][0] = objID.getString("desc");
                array[x][1] = objID.getString("pict");
                array[x][2] = objID.getString("name");
            }
        }catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + answer + "\"");
        }

        for(int i = 1; i<7;i++){

            String ID = "imageView" + i;
            int resID = getResources().getIdentifier(ID, "id", getPackageName());
            ImageView IV = (ImageView) findViewById(resID);
            Picasso.with(context).load(array[i][1]).into(IV);

        }

        /*
        ImageView offer1 = (ImageView) findViewById(R.id.imageView1);
        Picasso.with(context).load(array[1][1]).into(offer1);

        ImageView offer2 = (ImageView) findViewById(R.id.imageView2);
        Picasso.with(context).load(array[2][1]).into(offer2);

        ImageView offer3 = (ImageView) findViewById(R.id.imageView3);
        Picasso.with(context).load(array[3][1]).into(offer3);

        ImageView offer4 = (ImageView) findViewById(R.id.imageView4);
        Picasso.with(context).load(array[4][1]).into(offer4);

        ImageView offer5 = (ImageView) findViewById(R.id.imageView5);
        Picasso.with(context).load(array[4][1]).into(offer5);

        ImageView offer6 = (ImageView) findViewById(R.id.imageView6);
        Picasso.with(context).load(array[4][1]).into(offer6);
        */






    }

    public void nextPage(View v){
//        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        v.startAnimation(animAlpha);
        String ID = v.getContentDescription().toString();
        Log.e("dadada", ID);
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(IdeiActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array[i][0]);
        infoIntent.putExtra("pict", array[i][1]);
        infoIntent.putExtra("name", array[i][2]);

        startActivity(infoIntent);
    }
}
