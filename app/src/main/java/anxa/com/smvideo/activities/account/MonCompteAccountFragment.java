package anxa.com.smvideo.activities.account;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Date;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.LoginActivity;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.DietProfilesDataContract;
import anxa.com.smvideo.contracts.UserDataContract;
import anxa.com.smvideo.contracts.UserDataResponseContract;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by aprilanxa on 14/06/2017.
 */

public class MonCompteAccountFragment extends Fragment implements View.OnClickListener {

    private Context context;
    protected ApiCaller caller;

    private TextView logout_btn;
    private Button saveButton;
    private EditText name_et;
    private TextView sexe_et;
    private EditText weight_init_et;
    private EditText weight_target_et;
    private EditText height_et;
    private TextView plan_et;
    private TextView niveau_calorique_et;
    private EditText email_et;
    private ProgressBar savingProgressBar;

    private UserDataContract userDataContract;
    private DietProfilesDataContract dietProfilesDataContract;

    AlertDialog.Builder builder;
    AlertDialog genericDialog;

    String[] genderArray = new String[]{};
    String[] plansArray = new String[]{};
    String[] caloriesArray = new String[]{};

    View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();
        mView = inflater.inflate(R.layout.mon_compte_account, null);

        caller = new ApiCaller();

        userDataContract = ApplicationData.getInstance().userDataContract;
        dietProfilesDataContract = ApplicationData.getInstance().dietProfilesDataContract;

        genderArray = new String[]{getString(R.string.mon_compte_sexe_fem), getString(R.string.mon_compte_sexe_masc)};


        plansArray = new String[]{getString(R.string.mon_compte_plans_brunch), getString(R.string.mon_compte_plans_classique), getString(R.string.mon_compte_plans_pour),
                getString(R.string.mon_compte_plans_dejeuner), getString(R.string.mon_compte_plans_diner), getString(R.string.mon_compte_plans_gluten), getString(R.string.mon_compte_plans_vache),
                getString(R.string.mon_compte_plans_laitages_avec), getString(R.string.mon_compte_plans_laitages_sans), getString(R.string.mon_compte_plans_petit_dejeuner), getString(R.string.mon_compte_plans_special), getString(R.string.mon_compte_plans_vegetarien)};
        caloriesArray = new String[]{getString(R.string.mon_compte_niveau_calorique_900), getString(R.string.mon_compte_niveau_calorique_1200), getString(R.string.mon_compte_niveau_calorique_1400),
                getString(R.string.mon_compte_niveau_calorique_1600), getString(R.string.mon_compte_niveau_calorique_1800)};

        //header change
        ((TextView) (mView.findViewById(R.id.header_title_tv))).setText(getString(R.string.menu_account_compte));
        ((TextView) (mView.findViewById(R.id.header_right_tv))).setVisibility(View.GONE);
//        logout_btn = ((TextView) (mView.findViewById(R.id.header_right_tv)));
//        logout_btn.setText(getString(R.string.mon_compte_disconnect));
//        logout_btn.setOnClickListener(this);

        name_et = (EditText) (mView.findViewById(R.id.mon_name_et));
        sexe_et = (TextView) (mView.findViewById(R.id.mon_sexe_et));
        sexe_et.setOnClickListener(this);

        saveButton = (Button) (mView.findViewById(R.id.save_btn));
        saveButton.setOnClickListener(this);

        weight_init_et = (EditText) (mView.findViewById(R.id.mon_weight_init_et));
        weight_target_et = (EditText) (mView.findViewById(R.id.mon_weight_target_et));
        height_et = (EditText) (mView.findViewById(R.id.mon_height_et));

        plan_et = (TextView) (mView.findViewById(R.id.mon_plan_et));
        plan_et.setOnClickListener(this);
        niveau_calorique_et = (TextView) (mView.findViewById(R.id.mon_calories_et));
        niveau_calorique_et.setOnClickListener(this);
//        email_et = (EditText) (mView.findViewById(R.id.mon_email_et));

        savingProgressBar = (ProgressBar) (mView.findViewById(R.id.account_progressBar));
        savingProgressBar.setVisibility(View.GONE);

        builder = new AlertDialog.Builder(context);
        builder.setTitle("");
        builder.setCancelable(false);

        updateUserProfile();

        return mView;
    }

    @Override
    public void onClick(final View v) {
        if (v == saveButton) {
            if (validateInput()) {
                savingProgressBar.setVisibility(View.VISIBLE);
                saveProfileToObject();
            }
        } else if (v == sexe_et) {
            builder.setItems(genderArray,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            String[] arr = genderArray;
                            sexe_et.setText(arr[item]);
                            dietProfilesDataContract.Gender = item;
                        }
                    });

            genericDialog = builder.create();
            genericDialog.show();
        } else if (v == plan_et) {
            builder.setItems(plansArray,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            String[] arr = plansArray;
                            plan_et.setText(arr[item]);
                            dietProfilesDataContract.MealPlanType = item;
                        }
                    });

            genericDialog = builder.create();
            genericDialog.show();
        } else if (v == niveau_calorique_et) {
            builder.setItems(caloriesArray,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            String[] arr = caloriesArray;
                            niveau_calorique_et.setText(arr[item]);
                            dietProfilesDataContract.CalorieType = item;
                        }
                    });

            genericDialog = builder.create();
            genericDialog.show();
        }
//        else if(v == logout_btn){
//            logout();
//        }
    }

    private void updateUserProfile() {
        if (userDataContract != null) {
            name_et.setText(userDataContract.FirstName);
//            email_et.setText(userDataContract.Email);

            if (dietProfilesDataContract != null) {
                //diet profile
                weight_init_et.setText(AppUtil.convertToFrenchDecimal(dietProfilesDataContract.StartWeightInKg));
                weight_target_et.setText(AppUtil.convertToFrenchDecimal(dietProfilesDataContract.TargetWeightInKg));
                height_et.setText(AppUtil.convertToWholeNumber(dietProfilesDataContract.HeightInMeter));

                plan_et.setText(AppUtil.getMealTypeString(dietProfilesDataContract.MealPlanType, context));
                niveau_calorique_et.setText(AppUtil.getCalorieType(dietProfilesDataContract.CalorieType, context));

                sexe_et.setText(genderArray[dietProfilesDataContract.Gender]);
            }
        }
    }

    private boolean validateInput() {
        if (name_et.getText() == null || name_et.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_FIRSTNAME_EMPTY));
            return false;
        }
        if (height_et.getText() == null || height_et.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_HEIGHT));
            return false;
        }
        if (weight_target_et.getText() == null || weight_target_et.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_TARGETWEIGHT));
            return false;
        }
        if (weight_init_et.getText() == null || weight_init_et.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_CURRENTWEIGHT));
            return false;
        }
//        if (email_et.getText() == null || email_et.getText().length() <= 0) {
//            displayToastMessage(getString(R.string.ALERTMESSAGE_EMAIL));
//            return false;
//        }
        return true;
    }

    private void saveProfileToObject() {
        Gson gson = new Gson();

        userDataContract.FirstName = name_et.getText().toString();
//        userDataContract.Email = email_et.getText().toString();

//        dietProfilesDataContract.HeightInMeter = AppUtil.convertToEnglishDecimal(height_et.getText().toString()) / 100;
        dietProfilesDataContract.HeightInMeter = AppUtil.convertToEnglishDecimal(height_et.getText().toString());
        dietProfilesDataContract.CurrentWeightInKg = AppUtil.convertToEnglishDecimal(weight_init_et.getText().toString());
        dietProfilesDataContract.TargetWeightInKg = AppUtil.convertToEnglishDecimal(weight_target_et.getText().toString());

        System.out.println("save dietProfilesDataContract: " + niveau_calorique_et.getText().toString());
        System.out.println("save dietProfilesDataContract: " + plan_et.getText().toString());

        dietProfilesDataContract.CalorieType = AppUtil.getCalorieTypeIndex(niveau_calorique_et.getText().toString(), context);
        dietProfilesDataContract.MealPlanType = AppUtil.getMealTypeIndex(plan_et.getText().toString(), context);

        if (userDataContract.DietProfiles != null) {
            for (DietProfilesDataContract dietProfile : userDataContract.DietProfiles) {
                if (dietProfile.Id == dietProfilesDataContract.Id) {
                    userDataContract.DietProfiles.remove(dietProfile);
                    userDataContract.DietProfiles.add(dietProfilesDataContract);
                    break;
                }
            }
        }

        System.out.println("dietProfilesDataContract: " + gson.toJson(userDataContract));

        saveProfileToAPI();
    }

    private void saveProfileToAPI() {
        caller.PostUpdateAccountUserData(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                savingProgressBar.setVisibility(View.GONE);
                UserDataResponseContract c = (UserDataResponseContract) output;

                if (c != null && c.Data != null) {
                    ApplicationData.getInstance().userDataContract = c.Data;
                    System.out.println("PostUpdateAccountUserData: " + output);


                    if (c.Data.DietProfiles != null) {
                        for (DietProfilesDataContract dietProfilesDataContract : c.Data.DietProfiles) {
                            if (dietProfilesDataContract.CoachingStartDate != null && !dietProfilesDataContract.CoachingStartDate.equalsIgnoreCase("null")) {
                                ApplicationData.getInstance().dietProfilesDataContract = dietProfilesDataContract;
                                break;
                            }
                        }
                    }

                    userDataContract = ApplicationData.getInstance().userDataContract;
                    dietProfilesDataContract = ApplicationData.getInstance().dietProfilesDataContract;

                    updateUserProfile();
                }


            }
        }, userDataContract);
    }

    public void displayToastMessage(final String message) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast m = Toast.makeText(context, message, Toast.LENGTH_SHORT);
                m.show();

            }
        });
    }

    public void logout(){

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
        ApplicationData.getInstance().setAnxamatsSessionStart(context, 0);
        //clear login details
        ApplicationData.getInstance().userDataContract = new UserDataContract();
        ApplicationData.getInstance().regId = 1;

        ApplicationData.getInstance().setIsLogin(context, false);
        //clear the saved login credentials
        ApplicationData.getInstance().clearLoginCredentials();

        goToLoginPage();
    }

    private void goToLoginPage(){
        Intent mainIntent = new Intent(context, LoginActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
    }



}
