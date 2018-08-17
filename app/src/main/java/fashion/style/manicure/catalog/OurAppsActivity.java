package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class OurAppsActivity extends AppCompatActivity {
    Context context = this;
    String[][] array2 = new String[2][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourapps);

        final String answer = getIntent().getExtras().getString("answer");
        try {
            JSONObject obj = new JSONObject(answer);
            String apps = obj.getString("apps");
            Log.e("offemainmenu", apps);
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




    }
    public void nextPage(View v){
//        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
//        v.startAnimation(animAlpha);
        String ID = v.getContentDescription().toString();
        int i = Integer.parseInt(ID);
        Intent infoIntent = new Intent(OurAppsActivity.this, OfferActivity.class);
        infoIntent.putExtra("desc", array2[i][0]);
        infoIntent.putExtra("link", array2[i][1]);
        infoIntent.putExtra("name", array2[i][2]);
        infoIntent.putExtra("pict", array2[i][3]);

        startActivity(infoIntent);
    }
}
