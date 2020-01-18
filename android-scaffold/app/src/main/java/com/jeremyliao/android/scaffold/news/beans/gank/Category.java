package com.jeremyliao.android.scaffold.news.beans.gank;

import java.io.Serializable;

/**
 * Created by liaohailiang on 2020-01-16.
 */
public class Category implements Serializable {

    private String _id;

    private String en_name;

    private String name;

    private int rank;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
