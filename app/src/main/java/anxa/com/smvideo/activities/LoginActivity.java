package anxa.com.smvideo.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.account.BrowserActivity;
import anxa.com.smvideo.activities.account.LandingPageAccountActivity;
import anxa.com.smvideo.common.SavoirMaigrirVideoConstants;
import anxa.com.smvideo.common.WebkitURL;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.LoginContract;
import anxa.com.smvideo.contracts.PaymentOrderGoogleContract;
import anxa.com.smvideo.contracts.UserDataResponseContract;
import anxa.com.smvideo.util.AppUtil;
import anxa.com.smvideo.util.IabBroadcastReceiver;
import anxa.com.smvideo.util.IabHelper;
import anxa.com.smvideo.util.IabResult;
import anxa.com.smvideo.util.Inventory;
import anxa.com.smvideo.util.Purchase;

/**
 * Created by aprilanxa on 27/07/2017.
 */

public class LoginActivity extends Activity{

    private LoginContract loginContract;
    private EditText email_et, password_et;
    private Button loginButton;

    private ApiCaller apiCaller;
    private ProgressBar loginProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        loginContract = new LoginContract();

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        email_et = (EditText)findViewById(R.id.login_email_et);
        password_et = (EditText)findViewById(R.id.login_password_et);
        loginProgressBar = (ProgressBar)findViewById(R.id.login_progressBar);
        loginProgressBar.setVisibility(View.GONE);

        loginButton = (Button)findViewById(R.id.login_login_button);
    }

    public void goBackToLandingPage(View view){
//        onBackPressed();

        Intent mainIntent = new Intent(this, MainLandingPageActivity.class);
        startActivity(mainIntent);
    }

    public void validateLogin(View view){
        if (validateLogin())
            loginToAPI();
    }

    private void loginToAPI(){
        loginProgressBar.setVisibility(View.VISIBLE);
        loginButton.setEnabled(false);

        loginContract.Email = email_et.getText().toString();
        loginContract.Password = password_et.getText().toString();
        loginContract.Check_npna = false;

        apiCaller = new ApiCaller();

        apiCaller.PostLogin(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                loginProgressBar.setVisibility(View.GONE);
                loginButton.setEnabled(true);

                if (output != null) {
                    UserDataResponseContract c = (UserDataResponseContract) output;

                    if (c != null) {
                        if(c.Message.equalsIgnoreCase("Failed")){
                            displayToastMessage(getString(R.string.ALERTMESSAGE_LOGIN_FAILED));
                        }else {
                            ApplicationData.getInstance().userDataContract = c.Data;
                            ApplicationData.getInstance().regId = c.Data.Id;

                            ApplicationData.getInstance().setIsLogin(getBaseContext(), true);
                            ApplicationData.getInstance().setAnxamatsSessionStart(getBaseContext(), System.currentTimeMillis());
                            ApplicationData.getInstance().saveLoginCredentials(loginContract.Email, loginContract.Password);

                            goToAccountLandingPage();
                        }
                    }else{
                        displayToastMessage(getString(R.string.ALERTMESSAGE_LOGIN_FAILED));
                    }
                }

            }
        }, loginContract);

    }

    private void goToAccountLandingPage(){
        ApplicationData.getInstance().accountType = "account";
        Intent mainIntent = new Intent(this, LandingPageAccountActivity.class);
        startActivity(mainIntent);
    }

    private Boolean validateLogin() {
        if (email_et.getText() == null || email_et.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_LOGIN_EMPTY));
            return false;
        } else if (!AppUtil.isEmail(email_et.getText().toString())) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_LOGIN_EMAIL_ERROR));
            return false;
        } else if (password_et.getText() == null || password_et.getText().length() <= 0) {
            displayToastMessage(getString(R.string.ALERTMESSAGE_LOGIN_FAILED));
            return false;
        } else {
            return true;
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

    public void goToForgetPw(View view){
        ApplicationData.getInstance().accountType = "free";
        Intent mainContentBrowser = new Intent(this, BrowserActivity.class);
        mainContentBrowser.putExtra("HEADER_TITLE", getResources().getString(R.string.login_forgot_pw));
        mainContentBrowser.putExtra("URL_PATH", WebkitURL.forgetPw);
        startActivity(mainContentBrowser);
    }
}
