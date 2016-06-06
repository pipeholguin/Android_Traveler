package luisfelipeholguin.traveler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import luisfelipeholguin.traveler.databinding.ActivityLoginBinding;
import luisfelipeholguin.traveler.models.Usuario;
import luisfelipeholguin.traveler.net.api.UsuarioApi;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UsuarioApi.OnLogin {

    ActivityLoginBinding binding;
    UsuarioApi api;
    SharedPreferences preferences;
    Usuario logueado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("preferencias", MODE_PRIVATE);
        boolean logged = preferences.getBoolean("logged", false);
        try {
            logueado = Usuario.findById(Usuario.class, 1);
        }catch (Exception e){
            logueado= null;
            Log.d("LOGIN","CATCH SET NOMBRE: null");
        }

        if(logueado != null){
            Log.d("LOGIN",""+logueado.getNombre());
            if (!logueado.getNombre().equals("nil")){
                Log.d("LOGIN","IF TRUE");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setOnClick(this);

        api = new UsuarioApi(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.in:
                api.login(binding.usr.getText().toString(), binding.pass.getText().toString(), this);
                break;
            case R.id.reg:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onLogin(String status, String usuario) {
        Log.d("STATUS: ",""+status);
        if (status.equals("OK")){
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor= preferences.edit();
            editor.putBoolean("logged", true);
            //editor.putString("usuario", usuario);
            editor.commit();
            Usuario usuarioLogeado = Usuario.findById(Usuario.class,1);
            if (usuarioLogeado == null){
                usuarioLogeado = new Usuario();
            }
            usuarioLogeado.setNombre(usuario);
            usuarioLogeado.save();
            Log.d("LOGIN","USUARIO LOGUEADO: "+usuario);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
