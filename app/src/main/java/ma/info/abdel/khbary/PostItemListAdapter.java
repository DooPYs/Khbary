package ma.info.abdel.khbary;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by Abdel on 12/06/2015.
 */
public class PostItemListAdapter extends ArrayAdapter{
    public PostItemListAdapter(Context context, int resource) {
        super(context, resource);
    }
/**
    protected  Context context;
    ArrayList<FeedsInfos> itemfeed;
    LayoutInflater inflater;

    public PostItemListAdapter(Context context, int resource, ArrayList<FeedsInfos> itemfeed) {
        super(context, resource, itemfeed);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView title;
        TextView link;
        TextView author;
        TextView contentSnippet;
        TextView content;
        TextView publishedDate;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.FeedsListView, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml


        title = (TextView) itemView.findViewById(R.id.title);
        link = (TextView) itemView.findViewById(R.id.link);
        author = (TextView) itemView.findViewById(R.id.author);
        contentSnippet = (TextView) itemView.findViewById(R.id.contentSnippet);
        content = (TextView) itemView.findViewById(R.id.content);
        publishedDate = (TextView) itemView.findViewById(R.id.publishedDate);


        // Locate the ImageView in item_listview.xml
        //  enclousure = (ImageView) itemView.findViewById(R.id.enclousure);

        // Capture position and set results to the TextViews

        title.setText(resultp.get(MainActivity.TITLE));
        link.setText(resultp.get(MainActivity.LINK));
        author.setText(resultp.get(MainActivity.AUTHOR));
        contentSnippet.setText(resultp.get(MainActivity.CONTENTSNIPPET));
        content.setText(resultp.get(MainActivity.CONTENT));
        publishedDate.setText(resultp.get(MainActivity.PUBLISHEDDATE));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        //    imageLoader.DisplayImage(resultp.get(MainActivity.ENCLOUSURE), enclousure);
        // Capture ListView item click
    } */
}
