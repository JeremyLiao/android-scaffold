package com.jeremyliao.android.scaffold.news.beans.gank;

import java.io.Serializable;

/**
 * Created by liaohailiang on 2020-01-16.
 */
public class Content implements Serializable {

    private String _id;

    private String content;

    private String cover;

    private long crawled;

    private String created_at;

    private boolean deleted;

    private String published_at;

    private String raw;

    private Site site;

    private String title;

    private String uid;

    private String url;

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return this._id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCrawled(long crawled) {
        this.crawled = crawled;
    }

    public long getCrawled() {
        return this.crawled;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getPublished_at() {
        return this.published_at;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return this.raw;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Site getSite() {
        return this.site;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
