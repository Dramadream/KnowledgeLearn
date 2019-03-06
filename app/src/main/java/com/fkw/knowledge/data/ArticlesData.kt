package com.fkw.knowledge.data

/**
 * Author:          Kevin </BR>
 * CreatedTime:     2019/3/6 16:16 </BR>
 * Desc:            文章列表界面的数据 </BR>
 * <P>
 * ModifyTime:       </BR>
 * ModifyItems:      </BR>
 *
 * @author:         Kevin
 */
data class ArticlesData(
        val curPage: Int,//当前第几页
        val offset: Int,// 当前第一条数据是总的第几条数据
        val over: Boolean,// 数据是否请求完了
        val pageCount: Int, // 一共多少页
        val size: Int,// 每页的数量
        val total: Int,// 文章总数量
        val datas: List<Article>// 文章列表
)