package fashion.style.manicure.catalog;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainMenuActivity extends AppCompatActivity {



    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        new GetTask().execute();

        Context context = this;

//        Log.e("array1", array[1][0]);
//        Log.e("array2", array2[1][1]);
        View view1 = (View) findViewById(R.id.view1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, IdeiActivity.class);
                intent.putExtra("answer", answer);
                startActivity(intent);
            }
        });

        View view2 = (View) findViewById(R.id.view2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, FavoriteActivity.class);
                startActivity(intent);
            }
        });

        View view3 = (View) findViewById(R.id.view3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, OurAppsActivity.class);
                intent.putExtra("answer", answer);
                startActivity(intent);
            }
        });

    }

    private class GetTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            getAnswer();
            return null;
        }
    }
    public void getAnswer() {
        try {
            String urljson = getString(R.string.json);
            URL url = new URL(urljson);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            final int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf8"));
                answer = "";
                String line = null;
                while ((line = reader.readLine()) != null) {
                    answer += line;
                }
                reader.close();
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
    }
    protected void onRestoreInstanceState (Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
}

