package com.fkw.knowledge.db.bean;

import com.fkw.knowledge.db.convert.TagConvert;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;

import java.util.List;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/8 16:39 </BR>
 * Desc:            文章、项目 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
@Entity
public class Article {


    /**
     * apkLink :
     * author : susion随心
     * chapterId : 427
     * chapterName : susion随心
     * collect : false
     * courseId : 13
     * desc :
     * envelopePic :
     * fresh : false
     * id : 7997
     * link : https://mp.weixin.qq.com/s/IdkTssxRyE3gxY_yc7r2ew
     * niceDate : 2019-02-27
     * origin :
     * projectLink :
     * publishTime : 1551196800000
     * superChapterId : 408
     * superChapterName : 公众号
     * tags : [{"name":"公众号","url":"/wxarticle/list/427/1"}]
     * title : Fresco架构设计赏析
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */

    @Id(autoincrement = true)
    private Long ID;

    @Index(unique = true)
    @Property(nameInDb = "wanId")
    private long id;

    @Index(unique = true)
    private long chapterId;

    private String apkLink;
    private String author;
    private String chapterName;
    private boolean collect;
    private long courseId;
    private String desc;
    private String envelopePic;
    private boolean fresh;
    private String link;
    private String niceDate;
    private String origin;
    private String projectLink;
    private long publishTime;
    private int superChapterId;
    private String superChapterName;
    private String title;
    private int type;
    private long userId;
    private long visible;
    private int zan;

    @Convert(columnType = String.class, converter = TagConvert.class)
    private List<Tag> tags;


    @Generated(hash = 260390150)
    public Article(Long ID, long id, long chapterId, String apkLink, String author,
                   String chapterName, boolean collect, long courseId, String desc,
                   String envelopePic, boolean fresh, String link, String niceDate,
                   String origin, String projectLink, long publishTime, int superChapterId,
                   String superChapterName, String title, int type, long userId,
                   long visible, int zan, List<Tag> tags) {
        this.ID = ID;
        this.id = id;
        this.chapterId = chapterId;
        this.apkLink = apkLink;
        this.author = author;
        this.chapterName = chapterName;
        this.collect = collect;
        this.courseId = courseId;
        this.desc = desc;
        this.envelopePic = envelopePic;
        this.fresh = fresh;
        this.link = link;
        this.niceDate = niceDate;
        this.origin = origin;
        this.projectLink = projectLink;
        this.publishTime = publishTime;
        this.superChapterId = superChapterId;
        this.superChapterName = superChapterName;
        this.title = title;
        this.type = type;
        this.userId = userId;
        this.visible = visible;
        this.zan = zan;
        this.tags = tags;
    }


    @Generated(hash = 742516792)
    public Article() {
    }


    @Override
    public String toString() {
        return "Article{" +
                "ID=" + ID +
                ", id=" + id +
                ", chapterId=" + chapterId +
                ", apkLink='" + apkLink + '\'' +
                ", author='" + author + '\'' +
                ", chapterName='" + chapterName + '\'' +
                ", collect=" + collect +
                ", courseId=" + courseId +
                ", desc='" + desc + '\'' +
                ", envelopePic='" + envelopePic + '\'' +
                ", fresh=" + fresh +
                ", link='" + link + '\'' +
                ", niceDate='" + niceDate + '\'' +
                ", origin='" + origin + '\'' +
                ", projectLink='" + projectLink + '\'' +
                ", publishTime=" + publishTime +
                ", superChapterId=" + superChapterId +
                ", superChapterName='" + superChapterName + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", userId=" + userId +
                ", visible=" + visible +
                ", zan=" + zan +
                ", tags=" + tags +
                '}';
    }


    public Long getID() {
        return this.ID;
    }


    public void setID(Long ID) {
        this.ID = ID;
    }


    public long getId() {
        return this.id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getChapterId() {
        return this.chapterId;
    }


    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }


    public String getApkLink() {
        return this.apkLink;
    }


    public void setApkLink(String apkLink) {
        this.apkLink = apkLink;
    }


    public String getAuthor() {
        return this.author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getChapterName() {
        return this.chapterName;
    }


    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }


    public boolean getCollect() {
        return this.collect;
    }


    public void setCollect(boolean collect) {
        this.collect = collect;
    }


    public long getCourseId() {
        return this.courseId;
    }


    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }


    public String getDesc() {
        return this.desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getEnvelopePic() {
        return this.envelopePic;
    }


    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }


    public boolean getFresh() {
        return this.fresh;
    }


    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }


    public String getLink() {
        return this.link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public String getNiceDate() {
        return this.niceDate;
    }


    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }


    public String getOrigin() {
        return this.origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public String getProjectLink() {
        return this.projectLink;
    }


    public void setProjectLink(String projectLink) {
        this.projectLink = projectLink;
    }


    public long getPublishTime() {
        return this.publishTime;
    }


    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }


    public int getSuperChapterId() {
        return this.superChapterId;
    }


    public void setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
    }


    public String getSuperChapterName() {
        return this.superChapterName;
    }


    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public int getType() {
        return this.type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public long getUserId() {
        return this.userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getVisible() {
        return this.visible;
    }


    public void setVisible(long visible) {
        this.visible = visible;
    }


    public int getZan() {
        return this.zan;
    }


    public void setZan(int zan) {
        this.zan = zan;
    }


    public List<Tag> getTags() {
        return this.tags;
    }


    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
