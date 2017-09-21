package anxa.com.smvideo.activities.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import anxa.com.smvideo.R;

/**
 * Created by aprilanxa on 21/09/2017.
 */

public class AproposContentActivity extends Activity implements View.OnClickListener {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apropos_content);

        ((TextView) findViewById(R.id.header_title_tv)).setText(getString(R.string.apropos_menu_title));
        ((TextView) findViewById(R.id.header_right_tv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_back)).setVisibility(View.VISIBLE);

        backButton = (ImageView) ((RelativeLayout) findViewById(R.id.headermenu)).findViewById(R.id.header_menu_back);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == backButton) {
            finish();
        }
    }
}
