package ma.info.abdel.khbary;

import java.io.Serializable;

/**
 * Created by Abdel on 16/05/2015.
 */
public class FeedsInfos implements Serializable {

    private static final long serialVersionUID = -2163051469151804394L;

/*
    public String title;
    private String link;
    private String author;
    private String contentSnippet;
    private String content;
    private Date publishedDate;
*/
    public String guid;
    public String title;
    public String link;
    public String description;
    public String enclosure;
    public String pubDate;
    public String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }



}
