package com.fkw.knowledge.data

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/3/5 16:40
 * Desc:            WanAndroid 返回的顶层数据类
 *            {
 *             "data": ...,
 *             "errorCode": 0,
 *             "errorMsg": ""
 *             }
 *
 *
 * ModifyTime:
 * ModifyItems:
 * ```
 * @author:         Kevin
 */
data class ApiResponse<T>(
        val errorCode: Int,
        val errorMsg: String,
        val data: T
)