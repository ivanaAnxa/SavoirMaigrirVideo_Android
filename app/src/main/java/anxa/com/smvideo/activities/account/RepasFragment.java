package anxa.com.smvideo.activities.account;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import anxa.com.smvideo.R;
import anxa.com.smvideo.connection.ApiCaller;

/**
 * Created by aprilanxa on 22/06/2017.
 */

public class RepasFragment extends Fragment implements View.OnClickListener {

    private Context context;
    protected ApiCaller caller;

    View mView;

    private TextView repasProgram_tv;
    private EditText repasDay_et;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();
        mView = inflater.inflate(R.layout.repas_account, null);

        caller = new ApiCaller();

        //header change
        ((TextView) (mView.findViewById(R.id.header_title_tv))).setText(getString(R.string.menu_account_repas));
        ((TextView) (mView.findViewById(R.id.header_right_tv))).setVisibility(View.INVISIBLE);


        repasProgram_tv = (TextView) (mView.findViewById(R.id.repasHeader_tv));
        repasDay_et = (EditText) (mView.findViewById(R.id.repasDay_et));

        return mView;
    }

    @Override
    public void onClick(final View v) {

    }
}
