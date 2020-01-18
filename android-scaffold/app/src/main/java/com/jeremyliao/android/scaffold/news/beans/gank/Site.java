package com.jeremyliao.android.scaffold.news.beans.gank;

/**
 * Created by liaohailiang on 2020-01-16.
 */
public class Site {

    private String cat_cn;

    private String cat_en;

    private String desc;

    private String feed_id;

    private String icon;

    private String id;

    private String name;

    private int subscribers;

    private String type;

    private String url;

    public void setCat_cn(String cat_cn) {
        this.cat_cn = cat_cn;
    }

    public String getCat_cn() {
        return this.cat_cn;
    }

    public void setCat_en(String cat_en) {
        this.cat_en = cat_en;
    }

    public String getCat_en() {
        return this.cat_en;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setFeed_id(String feed_id) {
        this.feed_id = feed_id;
    }

    public String getFeed_id() {
        return this.feed_id;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public int getSubscribers() {
        return this.subscribers;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
