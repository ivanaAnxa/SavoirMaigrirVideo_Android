package anxa.com.smvideo.activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.VideoContract;
import anxa.com.smvideo.contracts.VideoResponseContract;
import anxa.com.smvideo.ui.CustomListView;
import anxa.com.smvideo.ui.VideoListAdapter;
import anxa.com.smvideo.util.VideoHelper;

/**
 * Created by angelaanxa on 5/24/2017.
 */

public class TemoignagesActivity extends Fragment implements View.OnClickListener {

    private Context context;
    protected ApiCaller caller;

    private CustomListView customListView;
    private VideoListAdapter adapter;
    private List<VideoContract> videosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();

        View mView = inflater.inflate(R.layout.temoignages, null);
        caller = new ApiCaller();

        //ui
        customListView = (CustomListView) mView.findViewById(R.id.testimonialListView);
        videosList = new ArrayList<VideoContract>();

        if (adapter == null) {
            adapter = new VideoListAdapter(context, videosList, this);
        }
        customListView.setAdapter(adapter);


        //header change
        ((TextView) ((RelativeLayout) mView.findViewById(R.id.headermenu)).findViewById(R.id.header_title_tv)).setText(getString(R.string.menu_temoignages));

        caller.GetFreeTestimonials(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                //INITIALIZE ALL ONCLICK AND API RELATED PROCESS HERE TO AVOID CRASHES
                if (output != null) {
                    VideoResponseContract c = (VideoResponseContract) output;

                    if (c != null && c.Data != null && c.Data.Videos != null) {
                        videosList = (List<VideoContract>) c.Data.Videos;
                        ApplicationData.getInstance().testimonialVideoList = videosList;
                        VideoHelper.sort("index", videosList);
                        adapter.updateItems(videosList);

                    }
                }
            }
        });

        return mView;
    }

    @Override
    public void onClick(View v) {

    }
}
