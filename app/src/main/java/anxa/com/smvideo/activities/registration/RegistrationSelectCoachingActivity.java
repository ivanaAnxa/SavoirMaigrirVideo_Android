package anxa.com.smvideo.activities.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.free.LandingPageActivity;

/**
 * Created by angelaanxa on 10/23/2017.
 */

public class RegistrationSelectCoachingActivity extends Activity {
    Button btnSave;
    CheckBox cbClassic;
    CheckBox cbDifficult;
    CheckBox cbDebordee;
    CheckBox cbMobilite;
    CheckBox cbMenopause;
    CheckBox cbMedication;

    TextView tv_Classic_title;
    TextView tv_Difficult_title;
    TextView tv_Debordee_title;
    TextView tv_Mobilite_title;
    TextView tv_Menopause_title;
    TextView tv_Medication_title;

    LinearLayout progressLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_selectcoaching);
        if(ApplicationData.getInstance().regUserProfile == null)
        {
            goToLanding();
        }

        //header change
        ((TextView) (this.findViewById(R.id.header_title_tv))).setText(R.string.registration_myProfileHeader);
        ((TextView) (this.findViewById(R.id.header_right_tv))).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.header_menu_back)).setVisibility(View.GONE);

        btnSave = (Button)findViewById(R.id.save_btn);
        cbClassic = (CheckBox) findViewById(R.id.cbClassic);
        cbDebordee = (CheckBox) findViewById(R.id.cbDebordee);
        cbDifficult = (CheckBox) findViewById(R.id.cbDifficult);
        cbMedication = (CheckBox) findViewById(R.id.cbMedication);
        cbMenopause = (CheckBox) findViewById(R.id.cbMenopause);
        cbMobilite = (CheckBox) findViewById(R.id.cbMobilite);
        tv_Classic_title = (TextView) findViewById(R.id.tv_Classic_title);
        tv_Difficult_title = (TextView) findViewById(R.id.tv_Difficult_title);
        tv_Debordee_title = (TextView) findViewById(R.id.tv_Debordee_title);
        tv_Mobilite_title = (TextView) findViewById(R.id.tv_Mobilite_title);
        tv_Menopause_title = (TextView) findViewById(R.id.tv_Menopause_title);
        tv_Medication_title = (TextView) findViewById(R.id.tv_Medication_title);


        if(ApplicationData.getInstance().regUserProfile.getGender().equalsIgnoreCase(getString(R.string.mon_compte_sexe_masc))) {
            ((LinearLayout) findViewById(R.id.ll_difficult)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.ll_debordee)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.ll_mobilite)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.ll_menopause)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.ll_medication)).setVisibility(View.GONE);
        }
        progressLayout = (LinearLayout) findViewById(R.id.progress);
        progressLayout.setVisibility(View.GONE);

    }
    public void validateForm(View view) {


        //disable submit button
        btnSave.setEnabled(false);


        if (validateForm()) {
            startProgress();

            //uncomment this after
            goToSelectMealProfile();
//            callOptinPage();
        } else {
            //form not validated
            btnSave.setEnabled(true);
        }
    }
    private boolean validateForm()
    {
        if(cbClassic.isChecked())
        {
            //classic
            if(ApplicationData.getInstance().regUserProfile.getGender().equalsIgnoreCase(getString(R.string.mon_compte_sexe_fem)))
            {
                ApplicationData.getInstance().regUserProfile.setCoaching(1);
            }else
            {
                ApplicationData.getInstance().regUserProfile.setCoaching(7);
            }
            return true;
        }
        if(cbDifficult.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setCoaching(4);
            return true;
        }
        if(cbDebordee.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setCoaching(5);
            return true;
        }
        if(cbMobilite.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setCoaching(6);
            return true;
        }
        if(cbMenopause.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setCoaching(2);
            return true;
        }
        if(cbMedication.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setCoaching(3);
            return true;
        }
        return false;
    }
    public void startProgress() {
        // TODO Auto-generated method stub
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                progressLayout.setVisibility(View.VISIBLE);
            }
        });
    }
    public void setCheckBoxValue(View view) {
        switch (view.getId()) {
            case R.id.cbClassic:

                if (cbClassic.isChecked()) {
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }

                break;
            case R.id.cbDifficult:
                if (cbDifficult.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.cbDebordee:

                if (cbDebordee.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.cbMobilite:

                if (cbMobilite.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.cbMenopause:
                if (cbMenopause.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.cbMedication:
                if (cbMedication.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                }
                break;
            case R.id.tv_Classic_title:
                if (cbClassic.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                else
                {
                        cbClassic.setChecked(true);
                        cbDifficult.setChecked(false);
                        cbDebordee.setChecked(false);
                        cbMobilite.setChecked(false);
                        cbMenopause.setChecked(false);
                        cbMedication.setChecked(false);
                }
                break;
            case R.id.tv_Difficult_title:
                if (cbDifficult.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                else
                {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(true);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.tv_Debordee_title:
                if (cbDebordee.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                else
                {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(true);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.tv_Mobilite_title:
                if (cbMobilite.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                else
                {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(true);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.tv_Menopause_title:
                if (cbMenopause.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                else
                {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(true);
                    cbMedication.setChecked(false);
                }
                break;
            case R.id.tv_Medication_title:
                if (cbMedication.isChecked()) {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(false);
                }
                else
                {
                    cbClassic.setChecked(false);
                    cbDifficult.setChecked(false);
                    cbDebordee.setChecked(false);
                    cbMobilite.setChecked(false);
                    cbMenopause.setChecked(false);
                    cbMedication.setChecked(true);
                }
                break;
            default:
        }
    }
    private void goToLanding()
    {
        Intent mainIntent = new Intent(this, LandingPageActivity.class);
        startActivity(mainIntent);
        finish();
    }
    private void goToSelectMealProfile()
    {
        progressLayout.setVisibility(View.GONE);
        btnSave.setEnabled(true);
        Intent mainIntent = new Intent(this, SelectMealProfileActivity.class);
        startActivity(mainIntent);
    }
    public void onBackPressed(View view) {
        super.onBackPressed();
    }
}
