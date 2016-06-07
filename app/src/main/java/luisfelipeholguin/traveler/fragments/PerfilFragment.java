package luisfelipeholguin.traveler.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import luisfelipeholguin.traveler.LoginActivity;
import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentPerfilBinding;
import luisfelipeholguin.traveler.models.Usuario;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.api.UsuarioApi;
import luisfelipeholguin.traveler.net.api.ViajesApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener, UsuarioApi.OnUser{




    public interface OnCloseSession{
        void onCloseSession(boolean state);
    }

    FragmentPerfilBinding binding;
    OnCloseSession onCloseSession;


    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onCloseSession = (OnCloseSession) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(getLayoutInflater(savedInstanceState));

        final SharedPreferences preferences =  this.getActivity().getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String usuario = preferences.getString("usuario", "");

        UsuarioApi api = new UsuarioApi(getActivity());
        api.getUsuario(this);


        binding.setOnClick(this);
        return binding.getRoot();
    }

    @Override
    public void onUser(String nombre, String correo, int celular) {

        //binding.nombre.setText(nombre);
        //binding.contacto.setText(celular);
        //binding.usuario.setText(correo);
        Log.d("NOMBRE", nombre);

    }

    @Override
    public void onClick(View view) {
        Usuario userLogged = Usuario.findById(Usuario.class,1);
        userLogged.setNombre("nil");
        userLogged.save();
        /*userLogged.delete();
        Usuario.executeQuery("VACUUM");*/
        Viaje.deleteAll(Viaje.class);
        onCloseSession.onCloseSession(true);
        Log.d("PERFIL","SESSION CLOSED"+userLogged.getNombre());
    }


}
