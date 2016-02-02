package ma.info.abdel.khbary;

// Created by Abdel on 18/05/2015.

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FeedsDataController extends AsyncTask<String, Integer,ArrayList<FeedsInfos>> {

    private String urlStr;
    private RSSXMLTag currentTag;

    private enum RSSXMLTag {
        TITLE, DATE, LINK, CONTENT, GUID,ENCLOSURE,IGNORETAG;
    }

    public FeedsDataController() {
    }

    @Override
    protected ArrayList<FeedsInfos> doInBackground(String... params) {
    // TODO Auto-generated method stub
        InputStream is = null;
        FeedsInfos pdData = null;
        ArrayList<FeedsInfos> postDataList = new ArrayList<>();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            int response = connection.getResponseCode();
            Log.d("debug", "The response is: " + response);
            is = connection.getInputStream();

            // parse xml after getting the data
            XmlPullParserFactory factory = XmlPullParserFactory
                    .newInstance();
            factory.setFeature(Xml.FEATURE_RELAXED, true);
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, "UTF-8");

            int eventType = xpp.getEventType();

            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    //"yyyy-MM-dd'T'HH:mm:ssZ");
                   "EEE, DD MMM yyyy HH:mm:ss");
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        pdData = new FeedsInfos();
                        currentTag = RSSXMLTag.IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = RSSXMLTag.TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = RSSXMLTag.LINK;
                    } else if (xpp.getName().equals("pubDate")) {
                        currentTag = RSSXMLTag.DATE;
                    }else if (xpp.getName().equals("description")) {
                        currentTag = RSSXMLTag.CONTENT;
                    }else if (xpp.getName().equals("guid")) {
                        currentTag = RSSXMLTag.GUID;
                    }else if (xpp. getName().equals("enclosure")) {
                        currentTag = RSSXMLTag.ENCLOSURE;
                        pdData.enclosure = xpp.getAttributeValue(null,"url");
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in
                        // Adapter
                        Date postDate = dateFormat.parse(pdData.pubDate);
                        pdData.pubDate = dateFormat.format(postDate);
                        postDataList.add(pdData);
                    } else {
                        currentTag = RSSXMLTag.IGNORETAG;
                    }
                }
                else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (pdData != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (pdData.title != null) {
                                        pdData.title += content;
                                    } else {
                                        pdData.title = content;
                                    }
                                }
                                break;
                            case LINK:
                                if (content.length() != 0) {
                                    if (pdData.link != null) {
                                        pdData.link += content;
                                    } else {
                                        pdData.link = content;
                                    }
                                    Document doc = Jsoup.connect(pdData.link).get();
                                    pdData.content = doc.select("div.cnt").toString();
                                 /*   pdData.content="\n" +
                                            "<!doctype html>\n" +
                                            "<html lang=\"fr\">\n" +
                                            "<head><link rel=\"stylesheet\" type=\"text/css\" href=\"css/style360.css\">\n"+
                                            "</head> <body>"+
                                                doc.select("div.ctn").toString()    + "</body>";
                                 */}
                                break;
                            case ENCLOSURE:
                                if (xpp.getAttributeValue(null, "url").length() != 0) {
                                    if (pdData.enclosure != null) {
                                        pdData.enclosure += xpp.getAttributeValue(null, "url");
                                    } else {
                                        pdData.enclosure = xpp.getAttributeValue(null,"url");
                                    }
                                }
                                break;
                            case CONTENT:
                                if (content.length() != 0) {
                                    if (pdData.description != null) {
                                        pdData.description += content;
                                    } else {
                                        pdData.description = content;
                                    }
                                }
                                break;
                            case DATE:
                                if (content.length() != 0) {
                                    if (pdData.pubDate != null) {
                                        pdData.pubDate += content;
                                    } else {
                                        pdData.pubDate = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
            Log.v("tst", String.valueOf((postDataList.get(1).content)));
            connection.disconnect();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  postDataList;

    }
}