package ma.info.abdel.khbary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class FeedsHome extends AppCompatActivity {
        private final static String urlStr = "http://www.le360.ma/fr/rss";
        SimpleGestureFilter detector;
        int i =1;
        ArrayList<FeedsInfos> myFeeds = null;

        ArrayList<FeedsInfos> myFeedsDetailed = null;
        VerticalPager mViewPager;
        PostItemAdapter mPostItemAdapter;
        FeedsDataController feedsDataController = new FeedsDataController();
        String FeedDetail;
        FeedsInfos feedsInfos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds_home);
       // FontsOverride.setDefaultFont(this, "MONOSPACE", "oswald.otf");
        // detector = new SimpleGestureFilter(this,this);

        try {
            myFeeds = feedsDataController.execute(urlStr).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }







    /*
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedslistview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ListViewAdapter mAdapter = new ListViewAdapter(myFeeds);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


       TextView textView = (TextView)  findViewById(R.id.feedsbrowser);

        try {
            FeedDetail= getFeedContent.execute(myFeeds.get(2).getLink()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        textView.setText(FeedDetail);

           GetFeedContent getFeedContent = new GetFeedContent(myFeeds);

        try {
            myFeedsDetailed = getFeedContent.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */
    //    webView.loadUrl("http://m.le360.ma/page.php?link="+myFeeds.get(2).getLink());

         mPostItemAdapter = new PostItemAdapter(getSupportFragmentManager(), this,myFeeds);

        mViewPager = (VerticalPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPostItemAdapter);
        mViewPager.setPageTransformer(false, new FeedsViewPagerTransformer(FeedsViewPagerTransformer.TransformType.SLIDE_OVER));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feeds_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  class GetFeedContent extends AsyncTask<String,Integer,ArrayList<FeedsInfos>>{

        ArrayList<FeedsInfos> feedsDetailsList;

        public  GetFeedContent (ArrayList<FeedsInfos> feedsInfoses){
           this.feedsDetailsList =feedsInfoses;
        }

        @Override
        protected ArrayList<FeedsInfos> doInBackground(String... params) {
            String restult=null;
           // FeedsInfos myfeeds= null;
          //  ArrayList<FeedsInfos> myFeedsDetailed = new ArrayList<>();

            for (int i =1;i<feedsDetailsList.size();i++) {
            try {
                Document doc = Jsoup.connect(feedsDetailsList.get(i).link).get();
                restult=doc.select("div.ctn").toString();
                Log.v("Content", restult);
                feedsDetailsList.get(i).setContent(restult);

            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            return feedsDetailsList;
        }
    }
/*
    @Override
    public void onSwipe(int direction) {
        String str = "";
        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
            break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

    public void PostItem (FeedsInfos itemFeed){

        ImageLoader relativeLayout = new ImageLoader(this);
        TextView TitleLabel = (TextView)findViewById(R.id.postTitleLabel);
        TextView DateLabel = (TextView) findViewById(R.id.postDateLabel);
        TextView ContentLabel = (TextView)findViewById(R.id.postContent);
        //TextView LinkLabel = (TextView) convertView.findViewById(R.id.postLink);
        RelativeLayout feedsLayout = (RelativeLayout)findViewById(R.id.feedsHome);
        // Populate the data into the template view using the data object
        TitleLabel.setText(itemFeed.getTitle());
        DateLabel.setText(itemFeed.getPubDate());
        ContentLabel.setText(itemFeed.getDescription());
        // LinkLabel.setText(itemFeed.link);
        relativeLayout.DisplayImage(itemFeed.getEnclosure(), feedsLayout);
        // ListView listView = (ListView) this.findViewById(R.id.postListView);
    } */
}

