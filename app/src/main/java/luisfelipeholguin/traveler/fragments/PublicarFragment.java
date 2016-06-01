package luisfelipeholguin.traveler.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import luisfelipeholguin.traveler.LoginActivity;
import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentPublicarBinding;
import luisfelipeholguin.traveler.net.api.ViajesApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicarFragment extends Fragment implements View.OnClickListener, ViajesApi.OnPublish {

    FragmentPublicarBinding binding;
    ViajesApi api;

    public PublicarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPublicarBinding.inflate(getLayoutInflater(savedInstanceState));
        binding.setOnClick(this);
        api = new ViajesApi(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {

        /*Date d = null;
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        try {
            d = format.parse(binding.fecha.getText().toString());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }*/
        //Calendar fecha = Calendar.getInstance();
        //fecha.setTime(d);

        String fecha = binding.fecha.getText().toString();

        api.publicar(binding.origen.getText().toString(),binding.destino.getText().toString(),
                binding.precio.getText().toString(),
                Integer.parseInt(binding.asientos.getText().toString()),
                fecha,binding.carro.getText().toString(), binding.image.getText().toString(),
                Integer.parseInt(binding.contacto.getText().toString()),this);
    }

    @Override
    public void onPublish(String status) {

        Log.d("STATUS: ",""+status);
        if (status.equals("OK")){
            Toast.makeText(getContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
        }

    }
}
