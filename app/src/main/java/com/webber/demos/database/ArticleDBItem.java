package com.webber.demos.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Makolacko on 12/3/2016.
 */
@Table(name = "ArticleDBItem")
public class ArticleDBItem extends Model {
    public static final String ARTICLE_ID = "mlArticleId";
    public static final String JSON_STRING = "jsonString";
    public static final String IS_READ = "isRead";

    @Column(name = "mlArticleId", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public String mlArticleId;

    @Column(name = "jsonString")
    public byte[] jsonString;

    @Column(name = "isRead")
    public int isRead;

    @Column(name = "isBookmark")
    public int isBookmark;

    @Column(name = "readDate")
    public String readDate;


}
