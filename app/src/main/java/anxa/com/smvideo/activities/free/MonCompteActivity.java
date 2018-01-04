package anxa.com.smvideo.activities.free;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import anxa.com.smvideo.R;
import anxa.com.smvideo.activities.registration.RegistrationActivity;

/**
 * Created by josephollero on 6/2/2017.
 */

public class MonCompteActivity extends Fragment {

    private Context context;
    private static final int BROWSERTAB_ACTIVITY = 1111;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();

        View mView = inflater.inflate(R.layout.mon_compte, null);

        //header change
        ((TextView) ((RelativeLayout) mView.findViewById(R.id.headermenu)).findViewById(R.id.header_title_tv)).setText(getString(R.string.menu_mon_compte));

        ((Button) mView.findViewById(R.id.moncompte_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
//                Uri uriUrl = Uri.parse("https://savoir-maigrir.aujourdhui.com/orange/billing/subscribe/");
//                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
//                startActivity(launchBrowser);
                goToRegistrationPage();

            }
        });

        return mView;
    }

    private void goToRegistrationPage() {
        Intent mainIntent = new Intent(context, RegistrationActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(mainIntent, BROWSERTAB_ACTIVITY);
    }
}
