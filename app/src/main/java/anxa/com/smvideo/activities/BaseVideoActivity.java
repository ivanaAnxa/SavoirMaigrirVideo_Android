package anxa.com.smvideo.activities;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.UserDataContract;

/**
 * Created by angelaanxa on 5/23/2017.
 */

public class BaseVideoActivity extends Activity{
    protected ApiCaller caller;

    public BaseVideoActivity() {
        caller = new ApiCaller();
    }

    private Gson gson;
    {
        gson = new Gson();
    }

    @Override
    protected void onResume() {
        UserDataContract up = ApplicationData.getInstance().userDataContract;
        if (up != null && up.Id>0 ) {
            ApplicationData.getInstance().setAnxamatsSessionStart(this, System.currentTimeMillis());
        }
        if (!this.getClass().getSimpleName().equalsIgnoreCase("LoginActivity") && !this.getClass().getSimpleName().equalsIgnoreCase("RegistrationActivity") && !this.getClass().getSimpleName().equalsIgnoreCase("SplashActivity")
                && !this.getClass().getSimpleName().equalsIgnoreCase("FullBrowserActivity") && !this.getClass().getSimpleName().equalsIgnoreCase("NpnaActivity")) {
            long sessionTimeMilliseconds = System.currentTimeMillis() - ApplicationData.getInstance().getSessionTime();
            if(sessionTimeMilliseconds > ApplicationData.getInstance().maximSessionTime)
            {
//                backgroundLogin();
                ApplicationData.getInstance().setSessionTime(this,0);
            }

        }

        super.onResume();
    }
    @Override
    protected void onPause()
    {
        UserDataContract up = ApplicationData.getInstance().userDataContract;
        long activeTimeMilliseconds = System.currentTimeMillis() - ApplicationData.getInstance().getAnxamatsSessionStart();
        if(activeTimeMilliseconds > ApplicationData.getInstance().maximumAnxamatsSessionTime)
        {
            activeTimeMilliseconds = ApplicationData.getInstance().maximumAnxamatsSessionTime;
        }
        final long activeTimeFinal = activeTimeMilliseconds;
        Log.d("sessionstartvalue", String.valueOf(ApplicationData.getInstance().getAnxamatsSessionStart()));
        if (up != null && up.Id>0 ) {
            if ((ApplicationData.getInstance().getAnxamatsSessionStart() > 0) &&
                    (activeTimeFinal > ApplicationData.minimumAnxamatsSessionTime)) {
                caller.PostAnxamatsActiveTime(new AsyncResponse() {
                    @Override
                    public void processFinish(Object output) {
                        Log.d("USERTIMELOGGED", "Logged user time " + activeTimeFinal);

                    }
                }, activeTimeFinal, up.Id);

            }
        }
        ApplicationData.getInstance().setAnxamatsSessionStart(this, 0);
        super.onPause();
    }

}
