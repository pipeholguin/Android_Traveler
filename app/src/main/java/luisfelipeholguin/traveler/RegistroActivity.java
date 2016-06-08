package luisfelipeholguin.traveler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import luisfelipeholguin.traveler.databinding.ActivityRegistroBinding;
import luisfelipeholguin.traveler.net.api.UsuarioApi;


public class RegistroActivity extends AppCompatActivity implements View.OnClickListener, UsuarioApi.OnRegister {

    ActivityRegistroBinding binding;
    UsuarioApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setOnClick(this);
        api = new UsuarioApi(this);
    }

    @Override
    public void onClick(View v) {

        api.register(binding.nombre.getText().toString(),binding.correo.getText().toString(),
                Integer.parseInt(binding.cel.getText().toString()),binding.usuario.getText().toString(),
                binding.password.getText().toString(),this);
    }

    @Override
    public void onRegister(String status) {
        Log.d("STATUS: ",""+status);
        if (status.equals("OK")){
            Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();
        }
    }
}
