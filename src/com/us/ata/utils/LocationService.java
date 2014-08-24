package com.us.ata.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

/**
 * User: Khiemvx
 * Date: 8/24/2014
 */
public class LocationService
{
// ------------------------------ FIELDS ------------------------------

    private static final String TAG = "LocationService";
    private static LocationManager locationManager;
    private static Location lastKnownLocation;

    private static LocationListener locationListener = new LocationListener()
    {
        public void onLocationChanged(Location location)
        {
            if (isBetterLocation(location, lastKnownLocation) || lastKnownLocation == null)
            {
                lastKnownLocation = location;
            }

            Log.i(TAG, "Longitude = " + lastKnownLocation.getLongitude());
            Log.i(TAG, "Latitude = " + lastKnownLocation.getLatitude());

            // Remove the listener you previously added
            locationManager.removeUpdates(this);
        }

        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }

        public void onProviderEnabled(String provider)
        {
            Log.w(TAG, "Provider is enabled");
        }

        public void onProviderDisabled(String provider)
        {
            Log.w(TAG, "Provider is disabled");
        }
    };

    private static final int TWO_MINUTES = 1000 * 60 * 2;

// -------------------------- STATIC METHODS --------------------------

    public static Location getLastKnownLocation(final Activity context)
    {
        return lastKnownLocation;
    }

    public static Location getLastKnownLocation()
    {
        return lastKnownLocation;
    }

    private static void requestUpdateLocation(Activity activity)
    {
        for (String provider : locationManager.getAllProviders())
        {
            requestLocationUpdates(provider, activity);
        }
    }

    public static void requestLocationUpdates(final String provider, Activity activity)
    {
        if (locationManager.isProviderEnabled(provider))
        {
            locationManager.requestLocationUpdates(provider, 0, 0, locationListener, Looper.getMainLooper());
        }
    }

    public static void updateLocation(Activity activity)
    {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        requestUpdateLocation(activity);
    }

    public static void updateLocation(Service service)
    {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) service.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Determines whether one Location reading is better than the current Location fix
     *
     * @param location            The new Location that you want to evaluate
     * @param currentBestLocation The current Location fix, to which you want to compare the new one
     */
    protected static boolean isBetterLocation(Location location, Location currentBestLocation)
    {
        if (currentBestLocation == null)
        {
            // A new location is always better than no location
            return true;
        }

        if (location.getAccuracy() > 50)
        {
            return false;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer)
        {
            return true;
            // If the new location is more than two minutes older, it must be worse
        }
        else if (isSignificantlyOlder)
        {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate)
        {
            return true;
        }
        else if (isNewer && !isLessAccurate)
        {
            return true;
        }
        else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks whether two providers are the same
     */
    private static boolean isSameProvider(String provider1, String provider2)
    {
        if (provider1 == null)
        {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
}
