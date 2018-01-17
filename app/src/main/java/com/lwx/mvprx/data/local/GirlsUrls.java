package com.lwx.mvprx.data.local;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Ljl on 2018/1/14.
 */

@Entity
public class GirlsUrls {
    @Id(autoincrement = true)
    private Long id;
    private Long parentId;
    private String url;
    @Generated(hash = 165362390)
    public GirlsUrls(Long id, Long parentId, String url) {
        this.id = id;
        this.parentId = parentId;
        this.url = url;
    }
    @Generated(hash = 181950883)
    public GirlsUrls() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return this.parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
