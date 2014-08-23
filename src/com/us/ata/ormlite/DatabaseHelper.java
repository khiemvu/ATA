package com.us.ata.ormlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.us.ata.model.Police;
import com.us.ata.model.Vehicle;
import com.us.ata.model.Witness;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{
// ------------------------------ FIELDS ------------------------------

    public static String DATABASE_NAME = "ATA.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;
    private Dao<Vehicle, String> vehicleDAO;
    private Dao<Witness, String> witnessDAO;
    private Dao<Police, String> policeDAO;

// --------------------------- CONSTRUCTORS ---------------------------

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Dao<Vehicle, String> getVehicleDAO() throws SQLException
    {
        if (vehicleDAO == null)
        {
            vehicleDAO = getDao(Vehicle.class);
            ((BaseDaoImpl) vehicleDAO).initialize();
        }
        return vehicleDAO;
    }

    public Dao<Witness, String> getWitnessDAO() throws SQLException
    {
        if (witnessDAO == null)
        {
            witnessDAO = getDao(Witness.class);
            ((BaseDaoImpl) witnessDAO).initialize();
        }
        return witnessDAO;
    }

    public Dao<Police, String> getPoliceDAO() throws SQLException
    {
        if (policeDAO == null)
        {
            policeDAO = getDao(Police.class);
            ((BaseDaoImpl) policeDAO).initialize();
        }
        return policeDAO;
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource)
    {
        try
        {
            TableUtils.createTable(connectionSource, Vehicle.class);
            TableUtils.createTable(connectionSource, Witness.class);
            TableUtils.createTable(connectionSource, Police.class);
        }
        catch (SQLException e)
        {
            Log.e(this.getClass().getName(), e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
                          int newVersion)
    {
        try
        {
            TableUtils.dropTable(connectionSource, Witness.class, true);
            TableUtils.dropTable(connectionSource, Vehicle.class, true);
            TableUtils.dropTable(connectionSource, Police.class, true);
            TableUtils.createTable(connectionSource, Vehicle.class);
            TableUtils.createTable(connectionSource, Witness.class);
            TableUtils.createTable(connectionSource, Police.class);

        }
        catch (SQLException e)
        {
        }
    }

    public void dropAllDatabase() throws SQLException
    {
        TableUtils.dropTable(connectionSource, Vehicle.class, true);
        TableUtils.dropTable(connectionSource, Witness.class, true);
        TableUtils.dropTable(connectionSource, Police.class, true);

    }

    public void createTables() throws SQLException
    {
        TableUtils.createTable(connectionSource, Vehicle.class);
        TableUtils.createTable(connectionSource, Witness.class);
        TableUtils.createTable(connectionSource, Police.class);
    }
}
