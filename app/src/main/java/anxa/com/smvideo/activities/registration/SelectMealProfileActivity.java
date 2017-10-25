package anxa.com.smvideo.activities.registration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.free.LandingPageActivity;

/**
 * Created by angelaanxa on 10/24/2017.
 */

public class SelectMealProfileActivity extends Activity {
    Button btnSave;
    CheckBox cbClassic;
    CheckBox cbMincir;
    CheckBox cbVegetarien;
    CheckBox cbColopathie;
    CheckBox cbBrunch;
    CheckBox cbNoBreakfast;
    CheckBox cbNoLunch;
    CheckBox cbNoDinner;
    CheckBox cbNoMilk;
    CheckBox cbNoDairy;
    CheckBox cbNoDairyNoCheese;
    CheckBox cbNoPorc;
    CheckBox cbNoGluten;

    LinearLayout progressLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_selectmealprofile);
        if(ApplicationData.getInstance().regUserProfile == null)
        {
            goToLanding();
        }

        //header change
        ((TextView) (this.findViewById(R.id.header_title_tv))).setText(R.string.registration_myProfileHeader);
        ((TextView) (this.findViewById(R.id.header_right_tv))).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_back)).setVisibility(View.GONE);

        btnSave = (Button)findViewById(R.id.save_btn);
        cbClassic = (CheckBox) findViewById(R.id.cbClassic);
         cbMincir = (CheckBox) findViewById(R.id.cbMincir);
         cbVegetarien = (CheckBox) findViewById(R.id.cbVegetarien);
         cbColopathie = (CheckBox) findViewById(R.id.cbColopathie);
         cbBrunch = (CheckBox) findViewById(R.id.cbBrunch);
         cbNoBreakfast = (CheckBox) findViewById(R.id.cbNoBreakfast);
         cbNoLunch = (CheckBox) findViewById(R.id.cbNoLunch);
         cbNoDinner = (CheckBox) findViewById(R.id.cbNoDinner);
         cbNoMilk = (CheckBox) findViewById(R.id.cbNoMilk);
         cbNoDairy= (CheckBox) findViewById(R.id.cbNoDairy);
        cbNoDairyNoCheese = (CheckBox) findViewById(R.id.cbNoDairyNoCheese);
         cbNoPorc = (CheckBox) findViewById(R.id.cbNoPorc);
        cbNoGluten = (CheckBox) findViewById(R.id.cbNoGluten);

        progressLayout = (LinearLayout) findViewById(R.id.progress);
        progressLayout.setVisibility(View.GONE);

    }
    public void validateForm(View view) {


        //disable submit button
        btnSave.setEnabled(false);


        if (validateForm()) {
            startProgress();

            //uncomment this after
            goToRegistrationLastPage();
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
            ApplicationData.getInstance().regUserProfile.setMealProfile(1);
            return true;
        }
        if(cbMincir.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(10);
            return true;
        }
        if(cbVegetarien.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(7);
            return true;
        }
        if(cbColopathie.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(5);
            return true;
        }
        if(cbBrunch.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(12);
            return true;
        }
        if(cbNoBreakfast.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(9);
            return true;
        }
        if(cbNoLunch.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(15);
            return true;
        }
        if(cbNoDinner.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(13);
            return true;
        }
        if(cbNoMilk.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(2);
            return true;
        }
        if(cbNoDairy.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(4);
            return true;
        }
        if(cbNoDairyNoCheese.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(4);
            return true;
        }
        if(cbNoPorc.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(6);
            return true;
        }
        if(cbNoGluten.isChecked())
        {
            ApplicationData.getInstance().regUserProfile.setMealProfile(8);
            return true;
        }
        return false;
    }
    public void setCheckBoxValue(View view) {
        switch (view.getId()) {
            case R.id.cbClassic:
                if (cbClassic.isChecked()) {
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbMincir:
                if (cbMincir.isChecked()) {
                    cbClassic.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbVegetarien:
                if (cbVegetarien.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbColopathie:
                if (cbColopathie.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbBrunch:
                if (cbBrunch.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoBreakfast:
                if (cbNoBreakfast.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoLunch:
                if (cbNoLunch.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoDinner:
                if (cbNoDinner.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoMilk:
                if (cbNoMilk.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoDairy:
                if (cbNoDairy.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoDairyNoCheese:
                if (cbNoDairyNoCheese.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoPorc.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoPorc:
                if (cbNoPorc.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoGluten.setChecked(false);
                }
                break;
            case R.id.cbNoGluten:
                if (cbNoGluten.isChecked()) {
                    cbClassic.setChecked(false);
                    cbMincir.setChecked(false);
                    cbVegetarien.setChecked(false);
                    cbColopathie.setChecked(false);
                    cbBrunch.setChecked(false);
                    cbNoBreakfast.setChecked(false);
                    cbNoLunch.setChecked(false);
                    cbNoDinner.setChecked(false);
                    cbNoMilk.setChecked(false);
                    cbNoDairy.setChecked(false);
                    cbNoDairyNoCheese.setChecked(false);
                    cbNoPorc.setChecked(false);
                }
                break;

            default:
        }
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
    private void goToLanding()
    {
        Intent mainIntent = new Intent(this, LandingPageActivity.class);
        startActivity(mainIntent);
        finish();
    }
    private void goToRegistrationLastPage()
    {
        Intent mainIntent = new Intent(this, SelectRegistrationCuisinerActivity.class);
        startActivity(mainIntent);
    }
}
