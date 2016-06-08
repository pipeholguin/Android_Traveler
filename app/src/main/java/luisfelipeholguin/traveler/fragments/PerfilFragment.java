package luisfelipeholguin.traveler.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentPerfilBinding;
import luisfelipeholguin.traveler.models.Usuario;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.api.UsuarioApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements View.OnClickListener, UsuarioApi.OnUpdateUser{

UsuarioApi api;
    String user;
    @Override
    public void onUpdateUser(String status) {
        if (status.equals("OK")) {
            Toast.makeText(getActivity(), "Cambios guardados", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "Error al guardar cambios", Toast.LENGTH_SHORT).show();
        }

    }

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

        Usuario userLogged = Usuario.findById(Usuario.class,1);
        user = userLogged.getNombre();
        binding.nombre.setText(user);
        binding.close.setVisibility(View.VISIBLE);

        api = new UsuarioApi(getActivity());

       // api.getUsuario(this);


        binding.setOnClick(this);
        return binding.getRoot();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close:
                Usuario userLogged = Usuario.findById(Usuario.class,1);
                userLogged.setNombre("nil");
                userLogged.save();
        /*userLogged.delete();
        Usuario.executeQuery("VACUUM");*/
                Viaje.deleteAll(Viaje.class);
                onCloseSession.onCloseSession(true);
                Log.d("PERFIL","SESSION CLOSED"+userLogged.getNombre());
                break;

            case R.id.updateuser:
                binding.name.setVisibility(View.VISIBLE);
                binding.nameImg.setVisibility(View.VISIBLE);
                binding.correo.setVisibility(View.VISIBLE);
                binding.correoImg.setVisibility(View.VISIBLE);
                binding.cel.setVisibility(View.VISIBLE);
                binding.celImg.setVisibility(View.VISIBLE);
                binding.password.setVisibility(View.VISIBLE);
                binding.passwordImg.setVisibility(View.VISIBLE);
                binding.close.setVisibility(View.GONE);
                binding.updateuser.setVisibility(View.GONE);
                binding.save.setVisibility(View.VISIBLE);
                binding.cancelar.setVisibility(View.VISIBLE);
                break;

            case R.id.cancelar:
                binding.name.setVisibility(View.INVISIBLE);
                binding.nameImg.setVisibility(View.INVISIBLE);
                binding.correo.setVisibility(View.INVISIBLE);
                binding.correoImg.setVisibility(View.INVISIBLE);
                binding.cel.setVisibility(View.INVISIBLE);
                binding.celImg.setVisibility(View.INVISIBLE);
                binding.password.setVisibility(View.INVISIBLE);
                binding.passwordImg.setVisibility(View.INVISIBLE);
                binding.close.setVisibility(View.VISIBLE);
                binding.updateuser.setVisibility(View.VISIBLE);
                binding.save.setVisibility(View.GONE);
                binding.cancelar.setVisibility(View.GONE);
                break;

            case R.id.save:
                api.updateUser(binding.name.getText().toString(), binding.correo.getText().toString(),
                        Integer.parseInt(binding.cel.getText().toString()), user, binding.password.getText().toString(), this);
                break;




        }

    }


}
