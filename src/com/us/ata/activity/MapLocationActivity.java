package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.us.ata.R;
import com.us.ata.utils.LocationService;

/**
 * User: Khiemvx
 * Date: 8/24/2014
 */
public class MapLocationActivity extends Activity implements View.OnClickListener
{
    // Google Map
    private GoogleMap googleMap;
    private ImageView ibtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_location);
        ibtBack = (ImageView) findViewById(R.id.map_location_ivBack);
        ibtBack.setOnClickListener(this);
        try
        {
            // Loading map
            initilizeMap();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * function to load map. If map is not created it will create it for you
     */
    private void initilizeMap()
    {
        // latitude and longitude
        double latitude = 0;
        double longitude = 0;
        LocationService.updateLocation(this);
        if (null != LocationService.getLastKnownLocation())
        {
            longitude = LocationService.getLastKnownLocation().getLongitude();
            latitude = LocationService.getLastKnownLocation().getLatitude();
        }
        // create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Position");
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        if (googleMap == null)
        {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            googleMap.addMarker(marker);
            // check if map is created successfully or not
            if (googleMap == null)
            {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initilizeMap();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.map_location_ivBack:
                finish();
                break;
        }
    }
}
