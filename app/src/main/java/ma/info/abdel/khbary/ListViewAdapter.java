package ma.info.abdel.khbary;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Abdel on 19/06/2015.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewHolder> {

    private  ArrayList<FeedsInfos> feedsInfos;
    ImageLoader relativeLayout;

    public  ListViewAdapter (ArrayList<FeedsInfos> feedsInfos){
        this.feedsInfos = feedsInfos;
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View feedsListView = LayoutInflater.from(parent.getContext()).inflate(R.layout.feeds_list_view, parent, false);

        // create ViewHolder
        ListViewHolder feedsListviewHolder = new ListViewHolder(feedsListView);

        relativeLayout = new ImageLoader(parent.getContext());

        return feedsListviewHolder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int position) {

      //  listViewHolder.guid.setText(feedsInfos.get(position).getGuid());
        listViewHolder.title.setText(feedsInfos.get(position).getTitle());
      //  listViewHolder.link.setText(feedsInfos.get(position).getLink());
        listViewHolder.description.setText(feedsInfos.get(position).getDescription());
       // listViewHolder.enclosure.setText(feedsInfos.get(position).getEnclosure());
        listViewHolder.pubDate.setText(feedsInfos.get(position).getPubDate());
        relativeLayout.DisplayImage(feedsInfos.get(position).getEnclosure(),listViewHolder.feedsLayout);
    }

    @Override
    public int getItemCount() {
        return  feedsInfos.size();
    }


}
