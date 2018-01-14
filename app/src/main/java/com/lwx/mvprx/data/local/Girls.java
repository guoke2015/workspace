package com.lwx.mvprx.data.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ljl on 2018/1/14.
 */

@Entity
public class Girls {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String url;
    private Date date;

    @Generated(hash = 1207650175)
    public Girls(Long id, String title, String url, Date date) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.date = date;
    }

    @Generated(hash = 1244608458)
    public Girls() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
