package fashion.style.manicure.catalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FavoritesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        setTitle(R.string.favorites);


    }
    public void onClick(View view) {
        finish();
    }
}
