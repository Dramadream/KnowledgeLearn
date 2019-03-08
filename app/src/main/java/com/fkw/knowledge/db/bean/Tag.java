package com.fkw.knowledge.db.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/8 16:41 </BR>
 * Desc:            标签 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
@Entity
public class Tag {


    @Id(autoincrement = true)
    private Long id;
    /**
     * name : 公众号
     * url : /wxarticle/list/427/1
     */

    private String name;
    private String url;


    @Generated(hash = 1492932651)
    public Tag(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    @Generated(hash = 1605720318)
    public Tag() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
