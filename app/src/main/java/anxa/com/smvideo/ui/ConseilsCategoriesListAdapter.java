package anxa.com.smvideo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.contracts.VideoContract;

/**
 * Created by aprilanxa on 28/07/2017.
 */

public class ConseilsCategoriesListAdapter extends ArrayAdapter<VideoContract> implements View.OnClickListener {

    private final Context context;
    private List<VideoContract> items = new ArrayList<VideoContract>();
    private String currentCategory;

    LayoutInflater layoutInflater;
    String inflater = Context.LAYOUT_INFLATER_SERVICE;
    View.OnClickListener listener;

    public ConseilsCategoriesListAdapter(Context context, List<VideoContract> items, View.OnClickListener listener) {
        super(context, R.layout.listitem_archive_content, items);

        layoutInflater = (LayoutInflater) context.getSystemService(inflater);
        this.context = context;
        this.items = items;
        this.listener = listener;

        currentCategory = ApplicationData.getInstance().currentSelectedCategory;
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
        return ApplicationData.getInstance().categoryList.size();
//        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        View row = convertView;
        if (row == null) {
            LayoutInflater layoutInflator = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row = layoutInflator.inflate(R.layout.listitem_archive_content, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.videoCategory = ((TextView) row.findViewById(R.id.weekNumber));
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        row.setTag(R.id.category_id, position);
        row.setOnClickListener(this);

        String categoryString = ApplicationData.getInstance().categoryList.get(position);
        viewHolder.videoCategory.setText(categoryString);
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
        TextView videoCategory;
    }
}
