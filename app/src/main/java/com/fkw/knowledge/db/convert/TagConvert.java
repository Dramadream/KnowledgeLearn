package com.fkw.knowledge.db.convert;

import com.fkw.knowledge.db.bean.Tag;
import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/8 16:49 </BR>
 * Desc:            GreenDao数据中有List时使用的转换帮助类 </BR>
 * <p>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author: Kevin
 */
public class TagConvert implements PropertyConverter<List<Tag>, String> {

    @Override
    public List<Tag> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        } else {
            Tag[] tags = new Gson().fromJson(databaseValue, Tag[].class);
            return Arrays.asList(tags);
        }
    }

    @Override
    public String convertToDatabaseValue(List<Tag> entityProperty) {
        if (entityProperty == null) {
            return null;
        } else {
            return new Gson().toJson(entityProperty);
        }
    }
}
