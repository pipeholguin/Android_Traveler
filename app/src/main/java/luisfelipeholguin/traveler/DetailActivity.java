package luisfelipeholguin.traveler;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import luisfelipeholguin.traveler.databinding.ActivityDetailBinding;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.api.ViajesApi;
import luisfelipeholguin.traveler.util.Constants;
import luisfelipeholguin.traveler.util.L;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener, ViajesApi.OnReserva {

    ActivityDetailBinding binding;
    int num;
    int pos;
    //SharedPreferences preferences = getSharedPreferences("preferencias", MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pos = getIntent().getExtras().getInt(Constants.POSITION_FROM_FRAGMENT);
        Viaje viaje = L.data.get(pos);
        Log.i("VIAJE", viaje.getDestino().toString() + " " + viaje.getCarro().toString());
        binding.setViaje(viaje);

        binding.reservar.setOnClickListener(this);
        binding.llamar.setOnClickListener(this);
        num = viaje.getContacto();

    }

    @Override
    public void onClick(View v) {

        if (v == binding.reservar)
        {
            Log.d("VIEW: ", "RESERVAR");
            int id = pos+1;
            //String user = preferences.getString("usuario","null");
            ViajesApi api = new ViajesApi(this);
            api.reservar("luis",id,this);
        }

        if (v == binding.llamar)
        {
            Log.d("VIEW: ", "LLAMAR");
            String call;
            call = "tel:"+num;
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(call));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(intent);
        }
    }

    @Override
    public void onReserva(String status) {
        Log.d("STATUS: ",""+status);
        if (status.equals("OK")){
            Toast.makeText(this, "Reserva correcto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error en la reserva", Toast.LENGTH_SHORT).show();
        }
    }
}
