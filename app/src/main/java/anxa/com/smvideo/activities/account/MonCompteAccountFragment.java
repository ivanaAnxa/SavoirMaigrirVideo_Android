package anxa.com.smvideo.activities.account;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import anxa.com.smvideo.R;
import anxa.com.smvideo.connection.ApiCaller;

/**
 * Created by aprilanxa on 14/06/2017.
 */

public class MonCompteAccountFragment extends Fragment implements View.OnClickListener {

    private Context context;
    protected ApiCaller caller;

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();
        mView = inflater.inflate(R.layout.mon_compte_account, null);

        caller = new ApiCaller();

        //header change
        ((TextView) (mView.findViewById(R.id.header_title_tv))).setText(getString(R.string.menu_account_compte));
        ((TextView) (mView.findViewById(R.id.header_right_tv))).setText(getString(R.string.mon_compte_save));

        return mView;
    }

    @Override
    public void onClick(final View v) {

    }
}
