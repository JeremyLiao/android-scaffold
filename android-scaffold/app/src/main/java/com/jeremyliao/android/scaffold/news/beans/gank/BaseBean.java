package com.jeremyliao.android.scaffold.news.beans.gank;

/**
 * Created by liaohailiang on 2020-01-16.
 */
public class BaseBean<T> {
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
