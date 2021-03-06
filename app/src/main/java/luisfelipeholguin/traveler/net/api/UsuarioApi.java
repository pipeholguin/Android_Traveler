package luisfelipeholguin.traveler.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.models.Login;
import luisfelipeholguin.traveler.models.Register;
import luisfelipeholguin.traveler.models.Update;
import luisfelipeholguin.traveler.models.Usuario;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.HttpApi;
import luisfelipeholguin.traveler.net.HttpAsyncTask;
import luisfelipeholguin.traveler.net.Response;

/**
 * Created by luisfelipeholguin on 26/05/16.
 */
public class UsuarioApi extends HttpApi {

    static final int REQUEST_LOGIN = 0;
    static final int REQUEST_REGISTER = 1;
    static final int REQUEST_USUARIO = 2;
    static final int REQUEST_UPDATE =3;

    public interface OnLogin{
        void onLogin(String status, String usuario);
    }

    public interface OnRegister{
        void  onRegister(String status);
    }

    public interface  OnUser{
        void onUser(String nombre, String correo, int celular );
    }

    public interface OnUpdateUser{
        void onUpdateUser(String status);
    }
    OnLogin onLogin;
    OnRegister onRegister;
    OnUser onUser;
    OnUpdateUser onUpdateUser;

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

    public void getUsuario( OnUser onUser){
        this.onUser = onUser;
        Usuario userLogged = Usuario.findById(Usuario.class,1);
        String url = urlBase + context.getString(R.string.url_user)+userLogged.getNombre();
        HttpAsyncTask task = makeTask(REQUEST_USUARIO, HttpAsyncTask.METHOD_GET);
        task.execute(url);
    }

    private void processGetUsuario (Response response){
        if (validateError(response)) {
            Type type = new TypeToken<List<Viaje>>(){}.getType();
            Usuario usuario = gson.fromJson(response.getMsg(), type);
            onUser.onUser(usuario.getNombre(), usuario.getCorreo(), usuario.getCelular());
        }
    }

    public void updateUser(String name, String email, int cel, String usr, String pass, OnUpdateUser onUpdateUser){
        this.onUpdateUser = onUpdateUser;
        Usuario userLogged = Usuario.findById(Usuario.class,1);
        String url = urlBase+context.getString(R.string.url_updateUser) + userLogged.getNombre();
        JsonObject json = new JsonObject();
        json.addProperty("nombre", name);
        json.addProperty("email", email);
        json.addProperty("celular", cel);
        json.addProperty("usuario", usr);
        json.addProperty("contrasena", pass);

        HttpAsyncTask task = makeTask(REQUEST_UPDATE, HttpAsyncTask.METHOD_PUT);
        task.execute(url, json.toString());
    }

    private void processUpdateUser(Response response){

        if (validateError(response)){
            Update update = gson.fromJson(response.getMsg(), Update.class);
            onUpdateUser.onUpdateUser(update.getStatus());
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
            case REQUEST_USUARIO:
                processGetUsuario(response);
                break;
            case REQUEST_UPDATE:
                processUpdateUser(response);
                break;
        }

    }
}
