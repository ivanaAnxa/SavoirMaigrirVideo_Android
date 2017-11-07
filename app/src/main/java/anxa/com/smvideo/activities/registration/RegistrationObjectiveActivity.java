package anxa.com.smvideo.activities.registration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.free.LandingPageActivity;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by angelaanxa on 10/23/2017.
 */

public class RegistrationObjectiveActivity extends Activity implements View.OnClickListener {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etInitialWeight;
    private EditText etTargetWeight;
    private EditText etHeight;
    private EditText etSexe;
    private EditText etBday;
    private Button btnContinue;
    private ProgressBar savingProgressBar;
    LinearLayout progressLayout;
    AlertDialog.Builder builder;
    AlertDialog genericDialog;

    String[] genderArray = new String[]{};

    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_objective);
        if(ApplicationData.getInstance().regUserProfile == null)
        {
            goToLanding();
        }

        //header change
        ((TextView) (this.findViewById(R.id.header_title_tv))).setText(R.string.registration_myProfileHeader);
        ((TextView) (this.findViewById(R.id.header_right_tv))).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_back)).setVisibility(View.GONE);

        etFirstName = ((EditText) (this.findViewById(R.id.et_firstName)));
        etLastName = ((EditText) (this.findViewById(R.id.et_lastName)));
        etInitialWeight = ((EditText) (this.findViewById(R.id.et_initialWeight)));
        etTargetWeight = ((EditText) (this.findViewById(R.id.et_targetWeight)));
        etHeight = ((EditText) (this.findViewById(R.id.et_height)));
        etSexe = ((EditText) (this.findViewById(R.id.et_sexe)));
        etBday = ((EditText) (this.findViewById(R.id.et_bday)));
        btnContinue = ((Button) (this.findViewById(R.id.save_btn)));

        etFirstName.setText(ApplicationData.getInstance().regUserProfile.getFirstname());
        etLastName.setText(ApplicationData.getInstance().regUserProfile.getLastname());
        progressLayout = (LinearLayout) findViewById(R.id.progress);
        progressLayout.setVisibility(View.GONE);




        etSexe.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setCancelable(false);

        genderArray = new String[]{getString(R.string.mon_compte_sexe_fem), getString(R.string.mon_compte_sexe_masc)};
        btnContinue.setOnClickListener(this);
        etBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });


    }
    private void showDatePicker() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        new DatePickerDialog(this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }
    private void updateLabel() {

        etBday.setText( new SimpleDateFormat("MM/dd/yyyy").format(myCalendar.getTime()));
    }
    @Override
    public void onClick(View v) {
        if (v == etSexe) {
            builder.setItems(genderArray,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            String[] arr = genderArray;
                            etSexe.setText(arr[item]);
                            ApplicationData.getInstance().regUserProfile.setGender(arr[item]);
                        }
                    });

            genericDialog = builder.create();
            genericDialog.show();
        }
        if (v == btnContinue) {
            if (validateInput()) {
                progressLayout.setVisibility(View.VISIBLE);
                saveObjective();
                goToCoachingSelect();
            }
        }
    }
    public void displayToastMessage(final String message) {
        final Context context = this;
        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast m = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                m.show();

            }
        });
    }
    private boolean validateInput() {
        if (etFirstName.getText() == null || etFirstName.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_FIRSTNAME_EMPTY));
            return false;
        }
        if (etLastName.getText() == null || etLastName.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_LASTNAME_EMPTY));
            return false;
        }
        if (etHeight.getText() == null || etHeight.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_HEIGHT));
            return false;
        }
        if (etHeight.getText() != null && (Float.parseFloat(String.valueOf(etHeight.getText())) < 140 || Float.parseFloat(String.valueOf(etHeight.getText())) > 220)) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_HEIGHT));
            return false;
        }
        if (etTargetWeight.getText() == null || etTargetWeight.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_TARGETWEIGHT));
            return false;
        }
        if (etTargetWeight.getText() != null && (Float.parseFloat(String.valueOf(etTargetWeight.getText())) < 40 || Float.parseFloat(String.valueOf(etTargetWeight.getText())) > 200)) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_ERRORWEIGHT_RANGE));
            return false;
        }
        if (etInitialWeight.getText() == null || etInitialWeight.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_CURRENTWEIGHT));
            return false;
        }
        if (etInitialWeight.getText() != null && (Float.parseFloat(String.valueOf(etInitialWeight.getText())) < 40 || Float.parseFloat(String.valueOf(etInitialWeight.getText())) > 200)) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_ERRORWEIGHT_RANGE));
            return false;
        }
        if (etSexe.getText() == null || etSexe.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_GENDER_EMPTY));
            return false;
        }
        if (etBday.getText() == null || etBday.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_BDAY_EMPTY));
            return false;
        }
//        if (email_et.getText() == null || email_et.getText().length() <= 0) {
//            displayToastMessage(getString(R.string.ALERTMESSAGE_EMAIL));
//            return false;
//        }
        return true;
    }
    private void saveObjective()
    {
        ApplicationData.getInstance().regUserProfile.setFirstname(String.valueOf(etFirstName.getText()));
        ApplicationData.getInstance().regUserProfile.setLastname(String.valueOf(etLastName.getText()));
        ApplicationData.getInstance().regUserProfile.setInitialWeight(Float.parseFloat(String.valueOf(etInitialWeight.getText())));
        ApplicationData.getInstance().regUserProfile.setTargetWeight(Float.parseFloat(String.valueOf(etTargetWeight.getText())));
        ApplicationData.getInstance().regUserProfile.setGender(String.valueOf(etSexe.getText()));
        ApplicationData.getInstance().regUserProfile.setHeight(Float.parseFloat(String.valueOf(etHeight.getText())));
        ApplicationData.getInstance().regUserProfile.setBday(String.valueOf(etBday.getText()));
    }

    private void goToLanding()
    {
        Intent mainIntent = new Intent(this, LandingPageActivity.class);
        startActivity(mainIntent);
        finish();
    }
    private void goToCoachingSelect()
    { progressLayout.setVisibility(View.GONE);
        Intent mainIntent = new Intent(this, RegistrationSelectCoachingActivity.class);
        startActivity(mainIntent);
    }

}
