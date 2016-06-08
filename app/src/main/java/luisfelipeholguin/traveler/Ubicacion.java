package luisfelipeholguin.traveler;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by luisfelipeholguin on 30/05/16.
 */
public class Ubicacion implements LocationListener {

    private Context context;
    String proveedor;
    LocationManager locationManager;

    Location location;
    String cityName = null;
    String citycorrect = null;
    TextView txt;

    public Ubicacion(Context context, TextView view) {
        this.context = context;


        txt = view;
        locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        proveedor = LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(proveedor, 1000, 1,this);
        //location = locationManager.getLastKnownLocation(proveedor);
        // getCity();

    }





    public void  getCity(){
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            if (addresses.size() > 0) {
                System.out.println(addresses.get(0).getLocality());
                cityName = addresses.get(0).getLocality();
                remove1(cityName);

                txt.setText(cityName);

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String remove1(String input) {
        // Cadena de caracteres original a sustituir.
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i=0; i<original.length(); i++) {
            // Reemplazamos los caracteres especiales.
            output = output.replace(original.charAt(i), ascii.charAt(i));

        }//for i

        return output;
    }


    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        getCity();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}