package fashion.style.manicure.catalog;

import android.app.Application;
import android.util.Log;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class AppMetrika extends Application {
String metrika;
    @Override
    public void onCreate() {
        super.onCreate();
        metrika = getString(R.string.yandexmetrika);
        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder(metrika);
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());

        YandexMetrica.enableActivityAutoTracking(this);

    }
}
