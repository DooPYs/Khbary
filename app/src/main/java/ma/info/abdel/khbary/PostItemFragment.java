package ma.info.abdel.khbary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Abdel on 30/05/2015.
 */
public class PostItemFragment extends Fragment {

    Context context;

    //

    @Override   
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String mime = "text/html";
        String encoding = "utf-8";
        Bundle bundle = getArguments();
        FeedsInfos itemFeed = (FeedsInfos) bundle.getSerializable("itemfeed");

        // The last two arguments ensure LayoutParams are inflated
        // properly.

        final View rootView = inflater.inflate(
                R.layout.postitem, container, false);

       // Bundle args = getArguments();
        ImageLoader relativeLayout = new ImageLoader(context);
        TextView TitleLabel = (TextView) rootView.findViewById(R.id.postTitleLabel);
        TextView DateLabel = (TextView) rootView.findViewById(R.id.postDateLabel);
        WebView ContentLabel = (WebView) rootView.findViewById(R.id.postContent);
        LinearLayout feedsLayout = (LinearLayout) rootView.findViewById(R.id.feedsLayout);


        // Populate the data into the template view using the data object
        TitleLabel.setText(itemFeed.title);
        DateLabel.setText(itemFeed.pubDate);

        ContentLabel.loadDataWithBaseURL("file:///android_asset/", itemFeed.content, mime, encoding, null);

        relativeLayout.DisplayImage(itemFeed.enclosure, feedsLayout);

        return rootView;
    }

}
