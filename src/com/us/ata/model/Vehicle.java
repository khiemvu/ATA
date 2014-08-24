package com.us.ata.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * User: khiemvx
 * Date: 8/23/14
 */
@DatabaseTable(tableName = "VEHICLE")
public class Vehicle
{
    public transient static final String TABLE_NAME = "VEHICLE";

    @DatabaseField(columnName = "_ID", id = true)
    private String id;
    @DatabaseField(columnName = "BROKER")
    protected String broker;
    @DatabaseField(columnName = "DRIVER")
    protected String driver;
    @DatabaseField(columnName = "INSURANCE_COMPANY")
    protected String insuranceCompany;
    @DatabaseField(columnName = "INSURANCE_PHONE")
    protected String insurancePhone;
    @DatabaseField(columnName = "INSURANCE_POLICY")
    protected String insurancePolicy;
    @DatabaseField(columnName = "MAKE")
    protected String make;
    @DatabaseField(columnName = "MODEL")
    protected String model;
    @DatabaseField(columnName = "NAME")
    protected String name;
    @DatabaseField(columnName = "OTHER_WORK")
    protected String otherWork;
    @DatabaseField(columnName = "READ")
    protected String read;
    @DatabaseField(columnName = "REGO")
    protected String rego;
    @DatabaseField(columnName = "REGO_REMINDER")
    protected String regoReminder;
    @DatabaseField(columnName = "TYPE")
    protected String type;
    @DatabaseField(columnName = "UNDERNEATH")
    protected String underneath;
    @DatabaseField(columnName = "YOUR_ADDRESS")
    protected String yourAddress;
    @DatabaseField(columnName = "YOUR_PHONE")
    protected String yourPhone;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getBroker()
    {
        return broker;
    }

    public void setBroker(String broker)
    {
        this.broker = broker;
    }

    public String getDriver()
    {
        return driver;
    }

    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public String getInsuranceCompany()
    {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany)
    {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsurancePhone()
    {
        return insurancePhone;
    }

    public void setInsurancePhone(String insurancePhone)
    {
        this.insurancePhone = insurancePhone;
    }

    public String getInsurancePolicy()
    {
        return insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy)
    {
        this.insurancePolicy = insurancePolicy;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOtherWork()
    {
        return otherWork;
    }

    public void setOtherWork(String otherWork)
    {
        this.otherWork = otherWork;
    }

    public String getRead()
    {
        return read;
    }

    public void setRead(String read)
    {
        this.read = read;
    }

    public String getRego()
    {
        return rego;
    }

    public void setRego(String rego)
    {
        this.rego = rego;
    }

    public String getRegoReminder()
    {
        return regoReminder;
    }

    public void setRegoReminder(String regoReminder)
    {
        this.regoReminder = regoReminder;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUnderneath()
    {
        return underneath;
    }

    public void setUnderneath(String underneath)
    {
        this.underneath = underneath;
    }

    public String getYourAddress()
    {
        return yourAddress;
    }

    public void setYourAddress(String yourAddress)
    {
        this.yourAddress = yourAddress;
    }

    public String getYourPhone()
    {
        return yourPhone;
    }

    public void setYourPhone(String yourPhone)
    {
        this.yourPhone = yourPhone;
    }

    public interface Properties
    {
        public static final String Id = "_ID";
        public static final String Broker = "BROKER";
        public static final String Driver = "DRIVER";
        public static final String InsuranceCompany = "INSURANCE_COMPANY";
        public static final String InsurancePhone = "INSURANCE_PHONE";
        public static final String InsurancePolicy = "INSURANCE_POLICY";
        public static final String Make = "MAKE";
        public static final String Model = "MODEL";
        public static final String Name = "NAME";
        public static final String OtherWork = "OTHER_WORK";
        public static final String Read = "READ";
        public static final String Rego = "REGO";
        public static final String RegoReminder = "REGO_REMINDER";
        public static final String Type = "TYPE";
        public static final String Underneath = "UNDERNEATH";
        public static final String YourAddress = "YOUR_ADDRESS";
        public static final String YourPhone = "YOUR_PHONE";
    }
}
