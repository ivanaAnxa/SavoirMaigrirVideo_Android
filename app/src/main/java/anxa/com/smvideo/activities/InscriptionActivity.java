package anxa.com.smvideo.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import anxa.com.smvideo.R;

/**
 * Created by ivanaanxa on 10/12/2017.
 */

public class InscriptionActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form2);

        //header change
        ((TextView) (this.findViewById(R.id.header_title_tv))).setText(R.string.inscription);
        ((TextView) (this.findViewById(R.id.header_right_tv))).setVisibility(View.INVISIBLE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_back)).setVisibility(View.GONE);

        //((TextView) (this.findViewById(R.id.registrationform2_headerTitle))).setText(R.string.inscription_headerTitle);

    }
}
