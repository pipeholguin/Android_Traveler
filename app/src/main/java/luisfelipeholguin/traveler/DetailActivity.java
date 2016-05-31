package luisfelipeholguin.traveler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import luisfelipeholguin.traveler.databinding.ActivityDetailBinding;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.util.Constants;
import luisfelipeholguin.traveler.util.L;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int pos = getIntent().getExtras().getInt(Constants.POSITION_FROM_FRAGMENT);
        Viaje viaje = L.data.get(pos);
        Log.i("VIAJE", viaje.getDestino().toString() + " " + viaje.getCarro().toString());
        binding.setViaje(viaje);
    }
}
