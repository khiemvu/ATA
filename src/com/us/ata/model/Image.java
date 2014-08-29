package com.us.ata.model;

import android.provider.BaseColumns;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Jodie Pham on 8/24/14.
 */
@DatabaseTable(tableName = "IMAGE")
public class Image
{
    @DatabaseField(columnName = "_ID", generatedId = true)
    private Long id;
    @DatabaseField(columnName = "URL")
    protected String url;
    @DatabaseField(columnName = "SECTION")
    private Long section;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public Long getSection()
    {
        return section;
    }

    public void setSection(Long section)
    {
        this.section = section;
    }
}
