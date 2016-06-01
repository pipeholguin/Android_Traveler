package luisfelipeholguin.traveler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import luisfelipeholguin.traveler.databinding.ActivityLoginBinding;
import luisfelipeholguin.traveler.models.Usuario;
import luisfelipeholguin.traveler.net.api.UsuarioApi;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, UsuarioApi.OnLogin {

    ActivityLoginBinding binding;
    UsuarioApi api;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("preferencias", MODE_PRIVATE);
        boolean logged = preferences.getBoolean("logged", false);
        if (logged){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setOnClick(this);

        api = new UsuarioApi(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
