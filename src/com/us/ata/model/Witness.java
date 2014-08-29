package com.us.ata.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * User: khiemvx
 * Date: 8/23/14
 */
@DatabaseTable(tableName = "WITNESS")
public class Witness implements Serializable
{
    public transient static final String TABLE_NAME = "WITNESS";

    @DatabaseField(columnName = "_ID", generatedId = true)
    private Long id;
    @DatabaseField(columnName = "CRASH_LOCATION")
    protected String crashLocation;
    @DatabaseField(columnName = "DESCRIPTION")
    protected String description;
    @DatabaseField(columnName = "NAME")
    protected String name;
    @DatabaseField(columnName = "PHONE")
    protected String phone;
    @DatabaseField(columnName = "EMAIL")
    protected String email;
    @DatabaseField(columnName = "ADDRESS")
    protected String address;

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public interface Properties
    {
        public static final String Id = "_ID";
        public static final String CrashLocation = "CRASH_LOCATION";
        public static final String Description = "DESCRIPTION";
        public static final String Name = "NAME";
        public static final String Phone = "PHONE";
        public static final String Email = "EMAIL";
        public static final String Address = "ADDRESS";
    }
}
