package ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.kozhurov.R;

import java.util.List;

/**
 * User: Андрей
 * Date: 14.10.13
 */
public class ActivityBase extends Activity {

    protected void hideSoftKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void showSoftKeyboard(View view) {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.showSoftInput(view, 0);
    }

    protected boolean isInternetConnectionEnabled() {
        return checkInternetConnection();
    }

    protected boolean isInternetConnectionEnabledWithAlertIfDisabled() {
        if (!isInternetConnectionEnabled()) {
            Toast.makeText(this, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

    }

    protected boolean isHasActivityForAction(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}
