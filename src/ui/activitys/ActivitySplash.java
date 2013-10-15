package ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.kozhurov.R;

public class ActivitySplash extends ActivityBase {

    private static final long TIME_TO_SHOW_SPLASH = 2000;

    private Handler handler;

    private Runnable mRunnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopAndGo();
    }

    private void stopAndGo() {
        mRunnable = new StartMainActivity();
        handler = new Handler(getMainLooper());
        handler.postDelayed(mRunnable, TIME_TO_SHOW_SPLASH);
    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, ActivityMain.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(mRunnable);
    }

    private class StartMainActivity implements Runnable {

        @Override
        public void run() {
            launchMainActivity();
            finish();
        }
    }
}
