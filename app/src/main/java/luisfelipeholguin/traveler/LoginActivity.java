package luisfelipeholguin.traveler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import luisfelipeholguin.traveler.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setOnClick(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.in:  break;
            case R.id.reg:

                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                break;

        }
    }
}
