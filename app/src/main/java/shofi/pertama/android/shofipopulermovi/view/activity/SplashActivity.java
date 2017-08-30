package shofi.pertama.android.shofipopulermovi.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import shofi.pertama.android.shofipopulermovi.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView gifView = (ImageView) findViewById(R.id.gifView);

        Glide.with(SplashActivity.this)
                .load(R.drawable.run)
                .asGif()
                .placeholder(R.drawable.run)
                .crossFade()
                .into(gifView);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                nextToMainActivity();
            }
        };

        long SPLASH_TIME = 5000;
        handler.postDelayed(runnable, SPLASH_TIME);

    }

    private void nextToMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
