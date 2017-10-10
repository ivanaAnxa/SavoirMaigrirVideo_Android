package anxa.com.smvideo.activities.account;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Date;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.MainActivity;
import anxa.com.smvideo.common.WebkitURL;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.DietProfilesDataContract;
import anxa.com.smvideo.contracts.UserDataContract;
import anxa.com.smvideo.contracts.UserDataResponseContract;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by aprilanxa on 14/06/2017.
 */

public class LandingPageAccountActivity extends Activity implements View.OnClickListener {

    protected ApiCaller caller;
    String userName = "";
    TextView initial_weight_tv, target_weight_tv, lost_weight_tv;
    ProgressBar weightProgressBar, landingProgressBar;
    private ImageView header_info_iv;
    private Button contact_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_page_account);

        initial_weight_tv = (TextView) findViewById(R.id.initial_weight_tv);
        target_weight_tv = (TextView) findViewById(R.id.target_weight_tv);
        lost_weight_tv = (TextView) findViewById(R.id.lost_weight_tv);

        weightProgressBar = (ProgressBar) findViewById(R.id.weight_landing_progressBar);
        landingProgressBar = (ProgressBar) findViewById(R.id.landing_account_progressBar);
        landingProgressBar.setVisibility(View.VISIBLE);

        header_info_iv = (ImageView) findViewById(R.id.header_info_iv);
        header_info_iv.setOnClickListener(this);

        caller = new ApiCaller();

        caller.GetAccountUserData(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                if (output != null) {
                    UserDataResponseContract c = (UserDataResponseContract) output;
                    //INITIALIZE ALL ONCLICK AND API RELATED PROCESS HERE TO AVOID CRASHES

                    if (c != null && c.Data != null) {

                        System.out.println("GetAccountUserData: " + c.Data);
                        ApplicationData.getInstance().userDataContract = c.Data;

                        userName = c.Data.FirstName;

                        String welcome_message = getString(R.string.welcome_account_1).replace("%@", userName) + getString(R.string.welcome_account_2);
                        ((TextView) (findViewById(R.id.welcome_message_account_tv))).setText(welcome_message);

                        if (c.Data.DietProfiles != null) {
                            for (DietProfilesDataContract dietProfilesDataContract : c.Data.DietProfiles) {
                                if (dietProfilesDataContract.CoachingStartDate != null && !dietProfilesDataContract.CoachingStartDate.equalsIgnoreCase("null")) {
                                    ApplicationData.getInstance().dietProfilesDataContract = dietProfilesDataContract;
                                    break;
                                }
                            }
                            ApplicationData.getInstance().currentWeekNumber = AppUtil.getCurrentWeekNumber(Long.parseLong(ApplicationData.getInstance().dietProfilesDataContract.CoachingStartDate), new Date());
                            welcome_message = welcome_message.replace("%d", Integer.toString(ApplicationData.getInstance().currentWeekNumber));
                            ((TextView) (findViewById(R.id.welcome_message_account_tv))).setText(welcome_message);
                        }
                        updateProgressBar();
                    }

                    landingProgressBar.setVisibility(View.GONE);

                }

            }
        });

        ApplicationData.getInstance().showLandingPage = false;

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

        contact_btn = (Button) findViewById(R.id.contact_account);
        contact_btn.setPaintFlags(contact_btn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        contact_btn.setOnClickListener(this);

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
        } else if (v == header_info_iv) {
            goToAproposPage();
        } else if (v == contact_btn) {
            goToContactPage();
        }
    }

    public void goToCoachingPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Coaching;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToRepasPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Repas;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToRecettesPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Recettes;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
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
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Suivi;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void goToMonComptePage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_MonCompte;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    private void updateProgressBar() {
        initial_weight_tv.setText(Float.toString(ApplicationData.getInstance().dietProfilesDataContract.StartWeightInKg) + " kg");
        target_weight_tv.setText(Float.toString(ApplicationData.getInstance().dietProfilesDataContract.TargetWeightInKg) + " kg");

        float lost_weight = ApplicationData.getInstance().dietProfilesDataContract.StartWeightInKg - ApplicationData.getInstance().dietProfilesDataContract.CurrentWeightInKg;
        //(StartWeightInKg - CurrentWeightInKg) / (StartWeightInKg - TargetWeightInKg)) * 100
        float lost_percentage = (lost_weight / (ApplicationData.getInstance().dietProfilesDataContract.StartWeightInKg - ApplicationData.getInstance().dietProfilesDataContract.TargetWeightInKg)) * 100;
        String lost_weight_message = getString(R.string.lost_weight_text) + " " + String.format("%.2f", lost_weight) + " kg (" + String.format("%.0f", lost_percentage) + "%)";
        lost_weight_tv.setText(lost_weight_message);

        weightProgressBar.setProgress((int) lost_percentage);
    }

    private void goToAproposPage() {
        ApplicationData.getInstance().selectedFragment = ApplicationData.SelectedFragment.Account_Apropos;
        Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    private void goToConditionsPage() {
        Intent mainContentBrowser = new Intent(this, BrowserActivity.class);
        mainContentBrowser.putExtra("HEADER_TITLE", getResources().getString(R.string.apropos_menu2));
        mainContentBrowser.putExtra("URL_PATH", WebkitURL.conditionsURL);
        startActivity(mainContentBrowser);
    }
    private void goToContactPage() {
        Intent mainContentBrowser = new Intent(this, BrowserActivity.class);
        mainContentBrowser.putExtra("HEADER_TITLE", getResources().getString(R.string.contact));

        mainContentBrowser.putExtra("URL_PATH", WebkitURL.contactURL);

        startActivity(mainContentBrowser);
    }
    @Override
    public void onBackPressed() {
    }

}
