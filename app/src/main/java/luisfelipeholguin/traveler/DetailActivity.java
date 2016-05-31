package luisfelipeholguin.traveler;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import luisfelipeholguin.traveler.databinding.ActivityDetailBinding;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.util.Constants;
import luisfelipeholguin.traveler.util.L;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDetailBinding binding;
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        int pos = getIntent().getExtras().getInt(Constants.POSITION_FROM_FRAGMENT);
        Viaje viaje = L.data.get(pos);
        Log.i("VIAJE", viaje.getDestino().toString() + " " + viaje.getCarro().toString());
        binding.setViaje(viaje);

        binding.llamar.setOnClickListener(this);
        num = viaje.getContacto();

    }

    @Override
    public void onClick(View v) {


        String call;
        call = "tel:"+num;
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(call));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);

    }
}
