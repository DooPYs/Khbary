package ma.info.abdel.khbary;

/**
 * Created by Abdel on 18/05/2015.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ma.info.abdel.khbary.PostItemFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;


public class PostItemAdapter extends FragmentPagerAdapter {

    protected Context mContext;
    ArrayList<FeedsInfos> itemfeed;

    public PostItemAdapter(FragmentManager fm, Context context,  ArrayList<FeedsInfos> listitem) {
        super(fm);
        mContext = context;
        this.itemfeed = listitem;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new PostItemFragment();

        Bundle feedsBundle = new Bundle();

        feedsBundle.putSerializable("itemfeed", itemfeed.get(position + 1));

        fragment.setArguments(feedsBundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return  itemfeed.size();
    }
}

/*public class PostItemAdapter extends ArrayAdapter<PostData> {

    private Activity myContext;
    private PostData[] datas;

    public PostItemAdapter(Context context, int textViewResourceId,
                           PostData[] objects) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub
        myContext = (Activity) context;
        datas = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = myContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.postitem, null);
        ImageView thumbImageView = (ImageView) rowView
                .findViewById(R.id.postThumb);
        if (datas[position].postThumbUrl == null) {
            thumbImageView.setImageResource(R.drawable.postthumb_loading);
        }

        TextView postTitleView = (TextView) rowView
                .findViewById(R.id.postTitleLabel);
        postTitleView.setText(datas[position].postTitle);

        TextView postDateView = (TextView) rowView
                .findViewById(R.id.postDateLabel);
        postDateView.setText(datas[position].postDate);

        return rowView;
    }
}*/