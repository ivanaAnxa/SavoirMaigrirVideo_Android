package anxa.com.smvideo.activities.account;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.ui.CoachingArchiveListAdapter;
import anxa.com.smvideo.ui.ConseilsCategoriesListAdapter;
import anxa.com.smvideo.ui.CustomListView;

/**
 * Created by aprilanxa on 28/07/2017.
 */

public class ConseilsArchiveActivity extends Activity implements View.OnClickListener {

    CustomListView categoriesListView;
    ConseilsCategoriesListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.coaching_archive_account);
        ((TextView) findViewById(R.id.header_title_tv)).setText(getString(R.string.coaching_header_right));
        ((TextView) findViewById(R.id.header_right_tv)).setVisibility(View.GONE);

        categoriesListView = (CustomListView) findViewById(R.id.archiveListView);

        if (adapter == null) {
            adapter = new ConseilsCategoriesListAdapter(this, ApplicationData.getInstance().conseilsVideoList, this);
        }

        categoriesListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        int categoryId = (Integer) v.getTag(R.id.category_id);
        ApplicationData.getInstance().currentSelectedCategory = ApplicationData.getInstance().categoryList.get(categoryId);
        ApplicationData.getInstance().fromArchiveConseils = true;

        Intent broadint = new Intent();
        broadint.setAction(this.getResources().getString(R.string.conseils_broadcast_string));
        this.sendBroadcast(broadint);

        finish();
    }
}
