package luisfelipeholguin.traveler.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import luisfelipeholguin.traveler.LoginActivity;
import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentPerfilBinding;
import luisfelipeholguin.traveler.net.api.UsuarioApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener{

    FragmentPerfilBinding binding;


    public PerfilFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(getLayoutInflater(savedInstanceState));

        final SharedPreferences preferences =  this.getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");

         binding.nombre.setText(usuario);


        binding.setOnClick(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {

    }


}
