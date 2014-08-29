package com.us.ata.model;

/**
 * User: Khiemvx
 * Date: 8/29/2014
 */
public class ServiceType
{
    private String name;
    private boolean selected;

    public ServiceType(String name, boolean selected)
    {
        this.name = name;
        this.selected = selected;
    }

    public ServiceType()
    {

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}

