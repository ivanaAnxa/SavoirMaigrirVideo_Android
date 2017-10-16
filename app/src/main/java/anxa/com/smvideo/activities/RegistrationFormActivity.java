package anxa.com.smvideo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import anxa.com.smvideo.R;

/**
 * Created by ivanaanxa on 10/10/2017.
 */

public class RegistrationFormActivity extends Activity implements View.OnClickListener {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);

        //header change
        ((TextView) (this.findViewById(R.id.header_title_tv))).setText(R.string.inscription);
        ((TextView) (this.findViewById(R.id.header_right_tv))).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_back)).setVisibility(View.VISIBLE);

        ((ImageView) findViewById(R.id.header_menu_back)).setOnClickListener(this);
        ((TextView) (this.findViewById(R.id.reg_login_account_exist_tv))).setOnClickListener(this);
        ((Button) (this.findViewById(R.id.registration_continuer))).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.header_menu_back) {
            onBackPressed();
        }else if(v.getId() == R.id.reg_login_account_exist_tv) {
            goToLoginPage();
        }else if (v.getId() == R.id.registration_continuer) {
            goToRegistrationPage2();
        }
    }


    private void goToLoginPage() {
        Intent mainIntent = new Intent(this, LoginActivity.class);
        startActivity(mainIntent);
    }

    private void goToRegistrationPage2 () {
        Intent mainIntent = new Intent(this, InscriptionActivity.class);
        startActivity(mainIntent);
    }

}
