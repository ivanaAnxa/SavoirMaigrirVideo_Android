package anxa.com.smvideo.activities;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.VideoContract;
import anxa.com.smvideo.contracts.VideoDataContract;
import anxa.com.smvideo.contracts.VideoResponseContract;
import anxa.com.smvideo.ui.CustomListView;
import anxa.com.smvideo.ui.VideoListAdapter;
import anxa.com.smvideo.util.VideoHelper;


/**
 * Created by angelaanxa on 5/23/2017.
 */

public class DiscoverActivity extends Fragment implements View.OnClickListener {

    Context context;
    ApiCaller caller;
    private CustomListView discoverListView;
    private VideoListAdapter adapter;
    private List<VideoContract> videosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();
        View mView = inflater.inflate(R.layout.discover, null);
        caller = new ApiCaller();

        //ui
        discoverListView = (CustomListView) mView.findViewById(R.id.discoverListView);
        videosList = new ArrayList<VideoContract>();
        if (adapter == null) {
            adapter = new VideoListAdapter(context, videosList, this);
        }

        discoverListView.setAdapter(adapter);

        caller.GetFreeDiscover(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {

                //INITIALIZE ALL ONCLICK AND API RELATED PROCESS HERE TO AVOID CRASHES
                if (output != null) {
                    VideoResponseContract c = (VideoResponseContract) output;

                    if (c != null && c.Data != null && c.Data.Videos != null) {
                        videosList = (List<VideoContract>) c.Data.Videos;
                        ApplicationData.getInstance().discoverVideoList = videosList;
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
