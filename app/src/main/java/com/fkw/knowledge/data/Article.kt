package com.fkw.knowledge.data

/**
 * ```
 * Author:          Kevin
 * CreatedTime:     2019/3/5 16:34
 * Desc:            TODO
 *
 * ModifyTime:
 * ModifyItems:
 * ```
 * @author:         Kevin
 */
data class Article(
        val apoLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Long,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Long,
        val link: String,
        val niceDate: String,
        val origin: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>,
        val title: String,
        val type: Int,
        val userId: Long,
        val visible: Int,
        val zan: Long
)