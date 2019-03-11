# 1.设计框架或技术

## 1.1 已使用的框架

Rxjava+Retrofit+OkHttp+Gson

[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)

[BRVAH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

[SmartRefreshLayout ](https://github.com/scwang90/SmartRefreshLayout)

Glide

GreenDao

[Stetho](https://github.com/facebook/stetho)

## 1.2 准备使用的框架或者技术

1. 视频播放
2. 图片拖动
3. 腾讯 X5WebView
4. 骨架屏 Skeleton
5. ~~Dagger2~~
6. 



# 2. 遇到的问题记录

## 2.1 GreenDao更新或者插入数据不能全部成功

文章的实体类：

```java
@Entity
public class Article {
    @Id(autoincrement = false)
    private Long id;
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
}
```

现象：使用save、insertOrReplaceInTx保存数据时会有部分数据存不到数据库中

暂时不清楚具体原因

