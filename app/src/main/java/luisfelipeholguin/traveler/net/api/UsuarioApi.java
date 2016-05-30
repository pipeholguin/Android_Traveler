package luisfelipeholguin.traveler.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.models.Login;
import luisfelipeholguin.traveler.models.Register;
import luisfelipeholguin.traveler.models.Usuario;
import luisfelipeholguin.traveler.net.HttpApi;
import luisfelipeholguin.traveler.net.HttpAsyncTask;
import luisfelipeholguin.traveler.net.Response;

/**
 * Created by luisfelipeholguin on 26/05/16.
 */
public class UsuarioApi extends HttpApi {

    static final int REQUEST_LOGIN = 0;
    static final int REQUEST_REGISTER = 1;

    public interface OnLogin{
        void onLogin(String status, Usuario usuario);
    }

    public interface OnRegister{
        void  onRegister(String status);
    }

    OnLogin onLogin;
    OnRegister onRegister;

    public UsuarioApi(Context context) {
        super(context);
    }

    public void login(String usr, String pass, OnLogin onLogin){
        this.onLogin = onLogin;
        String url = urlBase+context.getString(R.string.url_login);
        JsonObject json = new JsonObject();
        json.addProperty("usuario", usr);
        json.addProperty("contrasena", pass);

        HttpAsyncTask task = makeTask(REQUEST_LOGIN, HttpAsyncTask.METHOD_POST);
        task.execute(url, json.toString());
        Log.d("TASK EXEC","URL:"+url+" JSON:"+json.toString());
    }

    private void processLogin(Response response){
        Log.d("RPTAmsg",""+response.getMsg());
        Log.d("VALIDATERPTA: ",""+validateError(response));
        if (validateError(response)){
            Login login = gson.fromJson(response.getMsg(), Login.class);
            onLogin.onLogin(login.getStatus(), login.getUsuario());

        }
    }

    public void register (String name, String email, int cel, String usr, String pass, OnRegister onRegister){
        this.onRegister = onRegister;
        String url = urlBase+context.getString(R.string.url_registro);
        JsonObject json = new JsonObject();
        json.addProperty("nombre", name);
        json.addProperty("email", email);
        json.addProperty("celular", cel);
        json.addProperty("usuario", usr);
        json.addProperty("contrasena", pass);

        HttpAsyncTask task = makeTask(REQUEST_REGISTER, HttpAsyncTask.METHOD_POST);
        task.execute(url, json.toString());
        Log.d("TASK EXEC","URL:"+url+" JSON:"+json.toString());
    }

    private void processRegister(Response response){
        Log.d("RPTAmsg",""+response.getMsg());
        Log.d("VALIDATERPTA: ",""+validateError(response));
        if (validateError(response)){
            Register register = gson.fromJson(response.getMsg(), Register.class);
            onRegister.onRegister(register.getStatus());
        }
    }

    @Override
    public void onResponse(int request, Response response) {

        switch (request){
            case REQUEST_LOGIN:
                processLogin(response);
                break;
            case REQUEST_REGISTER:
                processRegister(response);
                break;
        }

    }
}
