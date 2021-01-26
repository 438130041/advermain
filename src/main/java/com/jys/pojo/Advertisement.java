package com.jys.pojo;

import java.io.Serializable;
import java.util.Date;

public class Advertisement implements Serializable {
    private String id;  //主键ID
    private String advername;  //广告名称
    private Integer adverclick; //广告点击量
    private String advercategory;  //广告分类
    private String adverphoto; //  图片或者文字
    private String adverlink;   //支持跳转链接还是不支持
    private String advertanchuang;  //弹窗还是非弹窗
    private String adverimgurl;  //图片
    private Date createtime;
    private String advertext;
    private String islink;
    private String showpage;
    private String wordshow;

    @Override
    public String toString() {
        return "Advertisement{" +
                "id='" + id + '\'' +
                ", advername='" + advername + '\'' +
                ", adverclick=" + adverclick +
                ", advercategory='" + advercategory + '\'' +
                ", adverphoto='" + adverphoto + '\'' +
                ", adverlink='" + adverlink + '\'' +
                ", advertanchuang='" + advertanchuang + '\'' +
                ", adverimgurl='" + adverimgurl + '\'' +
                ", createtime=" + createtime +
                ", advertext='" + advertext + '\'' +
                ", islink='" + islink + '\'' +
                ", showpage='" + showpage + '\'' +
                ", wordshow='" + wordshow + '\'' +
                '}';
    }

    public String getWordshow() {
        return wordshow;
    }

    public void setWordshow(String wordshow) {
        this.wordshow = wordshow;
    }

    public String getShowpage() {
        return showpage;
    }

    public void setShowpage(String showpage) {
        this.showpage = showpage;
    }

    public String getIslink() {
        return islink;
    }

    public void setIslink(String islink) {
        this.islink = islink;
    }

    public String getAdvertext() {
        return advertext;
    }

    public void setAdvertext(String advertext) {
        this.advertext = advertext;
    }

    public String getAdverimgurl() {
        return adverimgurl;
    }

    public void setAdverimgurl(String adverimgurl) {
        this.adverimgurl = adverimgurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvername() {
        return advername;
    }

    public void setAdvername(String advername) {
        this.advername = advername;
    }

    public Integer getAdverclick() {
        return adverclick;
    }

    public void setAdverclick(Integer adverclick) {
        this.adverclick = adverclick;
    }

    public String getAdvercategory() {
        return advercategory;
    }

    public void setAdvercategory(String advercategory) {
        this.advercategory = advercategory;
    }

    public String getAdverphoto() {
        return adverphoto;
    }

    public void setAdverphoto(String adverphoto) {
        this.adverphoto = adverphoto;
    }

    public String getAdverlink() {
        return adverlink;
    }

    public void setAdverlink(String adverlink) {
        this.adverlink = adverlink;
    }

    public String getAdvertanchuang() {
        return advertanchuang;
    }

    public void setAdvertanchuang(String advertanchuang) {
        this.advertanchuang = advertanchuang;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
