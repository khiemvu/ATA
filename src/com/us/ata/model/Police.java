package com.us.ata.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: khiemvx
 * Date: 8/23/14
 */
@DatabaseTable(tableName = "POLICE")
public class Police
{
    public transient static final String TABLE_NAME = "POLICE";

    @DatabaseField(columnName = "_ID", id = true)
    private String id;
    @DatabaseField(columnName = "EMAIL")
    protected String email;
    @DatabaseField(columnName = "EVENT")
    protected String eventno;
    @DatabaseField(columnName = "LOCATION")
    protected String location;
    @DatabaseField(columnName = "NAME")
    protected String name;
    @DatabaseField(columnName = "PHONE")
    protected String phone;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEventno()
    {
        return eventno;
    }

    public void setEventno(String eventno)
    {
        this.eventno = eventno;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public interface Properties
    {
        public static final String Id = "_ID";
        public static final String Email = "EMAIL";
        public static final String Event = "EVENT";
        public static final String Location = "LOCATION";
        public static final String Name = "NAME";
        public static final String Phone = "PHONE";
    }
}
