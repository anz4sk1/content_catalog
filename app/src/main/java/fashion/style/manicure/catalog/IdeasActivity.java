package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.startapp.android.publish.adsCommon.StartAppAd;

import org.json.JSONObject;

public class IdeasActivity extends AppCompatActivity {
Context context = this;
    String[][] array = new String[50][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);
        this.setTitle(R.string.Ideas);

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
            Log.e("My App", "Could not parse malformed JSON: \"" + answer + "\"");
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
        StartAppAd.showAd(this);
    }
    public void onClick(View view) {
        finish();
    }


}
