package fashion.style.manicure.catalog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class OfferActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        ImageButton btnBack = (ImageButton) findViewById(R.id.imageButtonBack);

        String offer1avastring = getIntent().getExtras().getString("pict");
        ImageView offer1ava = (ImageView) findViewById(R.id.imageOfferAva);
        Picasso.with(context).load(offer1avastring).into(offer1ava);

    }

    public void onClick(View view) {
        finish();
    }
}
