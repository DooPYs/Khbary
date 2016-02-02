package ma.info.abdel.khbary;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Abdel on 12/06/2015.
 */
public class ListViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {



    TextView guid;
    TextView title;
    TextView link;
    TextView description;
    TextView enclosure;
    TextView pubDate;
    LinearLayout feedsLayout;

    public ListViewHolder(View itemView) {
        super(itemView);

       feedsLayout= (LinearLayout) itemView.findViewById(R.id.feedsLayout);
        //    guid = (TextView) itemView.findViewById(R.id.guid);
        title = (TextView) itemView.findViewById(R.id.title);
   //     link = (TextView) itemView.findViewById(R.id.link);
        description = (TextView) itemView.findViewById(R.id.description);
     //   enclosure = (TextView) itemView.findViewById(R.id.enclosure);
        pubDate = (TextView) itemView.findViewById(R.id.pubDate);
    }

    @Override
    public void onClick(View v) {

    }
}
