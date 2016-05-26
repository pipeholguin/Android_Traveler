package luisfelipeholguin.traveler.net.api;

import android.content.Context;

import com.google.gson.JsonObject;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.net.HttpApi;
import luisfelipeholguin.traveler.net.HttpAsyncTask;
import luisfelipeholguin.traveler.net.Response;

/**
 * Created by luisfelipeholguin on 26/05/16.
 */
public class UsuarioApi extends HttpApi {

    static final int REQUEST_LOGIN = 0;

    public UsuarioApi(Context context) {
        super(context);
    }

    public void login(String usr, String pass){
        String url = urlBase+context.getString(R.string.url_login);
        JsonObject json = new JsonObject();
        json.addProperty("usuario", usr);
        json.addProperty("contrasena", pass);

        HttpAsyncTask task = makeTask(0, HttpAsyncTask.METHOD_POST);
        task.execute(url, json.toString());
    }

    private void processLogin(Response response){

        if (validateError(response)){
//
        }
    }

    @Override
    public void onResponse(int request, Response response) {

        switch (request){
            case REQUEST_LOGIN:
                processLogin(response);
                break;
        }

    }
}
