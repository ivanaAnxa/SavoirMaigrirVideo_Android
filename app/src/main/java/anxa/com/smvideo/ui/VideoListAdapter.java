package anxa.com.smvideo.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anxa.com.smvideo.R;
import anxa.com.smvideo.connection.http.VideoDownloadImageAsync;
import anxa.com.smvideo.contracts.VideoContract;
import anxa.com.smvideo.util.VideoHelper;

/**
 * Created by angelaanxa on 5/31/2017.
 */

public class VideoListAdapter extends ArrayAdapter<VideoContract> implements View.OnClickListener {
    private final Context context;
    private List<VideoContract> items = new ArrayList<VideoContract>();

    LayoutInflater layoutInflater;
    String inflater = Context.LAYOUT_INFLATER_SERVICE;
    View.OnClickListener listener;

    public VideoListAdapter(Context context, List<VideoContract> items, View.OnClickListener listener) {
        super(context, R.layout.listitem_video, items);

        layoutInflater = (LayoutInflater) context.getSystemService(inflater);
        this.context = context;
        this.items = items;
        this.listener = listener;

    }
    public void updateItems(List<VideoContract> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        this.items = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VideoListAdapter.ViewHolder viewHolder = null;

        View row = convertView;
        if (row == null) {
            LayoutInflater layoutInflator = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflator.inflate(R.layout.listitem_video, parent, false);
            viewHolder = new VideoListAdapter.ViewHolder();
            viewHolder.videoImage = (ImageView) row.findViewById(R.id.videoImage);
            viewHolder.videoTitle = ((TextView) row.findViewById(R.id.videoTitle));
            viewHolder.videoImageProgress= ((ProgressBar) row.findViewById(R.id.videoImageProgress));
            row.setTag(viewHolder);
        }else {
            viewHolder = (VideoListAdapter.ViewHolder) row.getTag();
        }

        int itemCount = items.size() - position;

        if (getCount() > position && itemCount == 5) {
            System.out.println("getPosition");
        }

        VideoContract contract = (VideoContract) items.get(position);
        row.setTag(R.id.video_id, contract.VideoUrl);
        row.setOnClickListener(this);
        Bitmap avatar = null;
        avatar = VideoHelper.GetVideoImage(contract.VideoId);
        viewHolder.videoImage.setTag(contract.VideoUrl);
        if(contract.IsSelected)
        {
            if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.videoImage.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.orange_border));
            } else {
                viewHolder.videoImage.setBackground(context.getResources().getDrawable(R.drawable.orange_border));
            }
        }else{
            if (Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.videoImage.setBackgroundDrawable(null);
            } else {
                viewHolder.videoImage.setBackground(null);
            }
        }
        //display message
        viewHolder.videoTitle.setText(contract.Title);
        if (avatar == null) {
            new VideoDownloadImageAsync(viewHolder.videoImage, viewHolder.videoImageProgress,  contract.VideoUrl).execute(contract.ThumbnailUrl.replace("hqdefault", "default"));
        } else {

            viewHolder.videoImage.setImageBitmap(avatar);
            viewHolder.videoImageProgress.setVisibility(View.GONE);
        }

        return row;
    }


    private void refreshUI() {
        notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        if (v != null) {

            if (items != null && items.size() > 0) {


                if (listener != null) {
                    listener.onClick(v);

                }
            }
        }

    }


    private static class ViewHolder {
        ImageView videoImage;
        TextView videoTitle;
        ProgressBar videoImageProgress;
    }
}
