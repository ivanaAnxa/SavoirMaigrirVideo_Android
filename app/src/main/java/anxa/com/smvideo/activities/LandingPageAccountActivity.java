package anxa.com.smvideo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by aprilanxa on 14/06/2017.
 */

public class LandingPageAccountActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_account);

        ApplicationData.getInstance().showLandingPage = false;

        String welcome_message = getString(R.string.welcome_account_1).replace("%@", ApplicationData.getInstance().userName) +
                getString(R.string.welcome_account_2).replace("%d", Integer.toString(AppUtil.getCurrentWeek()));
        ((TextView) (findViewById(R.id.welcome_message_account_tv))).setText(welcome_message);

        //initialize on click (transfer inside api call if there will be one in the future
        ((Button) findViewById(R.id.LandingCoachingButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.LandingRepasButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.LandingRecettesAccountButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.LandingConseilsButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.LandingExercicesButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.LandingSuiviButton)).setOnClickListener(this);
        ((Button) findViewById(R.id.LandingMonCompteButton)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage1_account)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage2_account)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage3_account)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage4_account)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage5_account)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage6_account)).setOnClickListener(this);
        ((ImageView) findViewById(R.id.LandingImage7_account)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.LandingCoachingButton || v.getId() == R.id.LandingImage1_account) {
            goToCoachingPage();
        } else if (v.getId() == R.id.LandingRepasButton || v.getId() == R.id.LandingImage2_account) {
            goToRepasPage();
        } else if (v.getId() == R.id.LandingRecettesAccountButton || v.getId() == R.id.LandingImage3_account) {
            goToRecettesPage();
        } else if (v.getId() == R.id.LandingConseilsButton || v.getId() == R.id.LandingImage4_account) {
            goToConseilsPage();
        } else if (v.getId() == R.id.LandingExercicesButton || v.getId() == R.id.LandingImage5_account) {
            goToExercicesPage();
        } else if (v.getId() == R.id.LandingSuiviButton || v.getId() == R.id.LandingImage6_account) {
            goToSuiviPage();
        } else if (v.getId() == R.id.LandingMonCompteButton || v.getId() == R.id.LandingImage7_account) {
            goToMonComptePage();
        }
    }

    public void goToCoachingPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Coaching;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToRepasPage() {

    }

    public void goToRecettesPage() {

    }

    public void goToConseilsPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Conseil;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);

    }

    public void goToExercicesPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Exercices;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToSuiviPage() {

    }

    public void goToMonComptePage() {

    }


}
