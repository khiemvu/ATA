package com.us.ata.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: khiemvx
 * Date: 8/23/14
 */
@DatabaseTable(tableName = "WITNESS")
public class Witness
{
    public transient static final String TABLE_NAME = "WITNESS";

    @DatabaseField(columnName = "_ID", id = true)
    private String id;
    @DatabaseField(columnName = "CRASH_LOCATION")
    protected String crashLocation;
    @DatabaseField(columnName = "DESCRIPTION")
    protected String description;
    @DatabaseField(columnName = "NAME")
    protected String name;
    @DatabaseField(columnName = "PHONE")
    protected String phone;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCrashLocation()
    {
        return crashLocation;
    }

    public void setCrashLocation(String crashLocation)
    {
        this.crashLocation = crashLocation;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public interface Properties
    {
        public static final String Id = "_ID";
        public static final String CrashLocation = "CRASH_LOCATION";
        public static final String Description = "DESCRIPTION";
        public static final String Name = "NAME";
        public static final String Phone = "PHONE";
    }
}
