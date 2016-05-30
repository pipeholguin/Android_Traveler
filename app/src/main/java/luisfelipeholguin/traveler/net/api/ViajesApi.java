package luisfelipeholguin.traveler.net.api;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.HttpApi;
import luisfelipeholguin.traveler.net.HttpAsyncTask;
import luisfelipeholguin.traveler.net.Response;

/**
 * Created by luisfelipeholguin on 30/05/16.
 */
public class ViajesApi extends HttpApi {

    static final int REQUEST_VIAJES= 1;

    public interface OnViajes{
        void  onViajes(List<Viaje> data);
    }

    OnViajes onViajes;

    public ViajesApi(Context context) {
        super(context);
    }

    public void getViajes(OnViajes onViajes){
        this.onViajes = onViajes;
        String url = urlBase + context.getString(R.string.url_viajes);
        HttpAsyncTask task = makeTask(REQUEST_VIAJES, HttpAsyncTask.METHOD_GET);
        task.execute(url);
    }

    private void processViajes(Response response) {

        if (validateError(response)) {
            Type type = new TypeToken<List<Viaje>>(){}.getType();
            List<Viaje> data = gson.fromJson(response.getMsg(), type);
            onViajes.onViajes(data);
        }
    }

    @Override
    public void onResponse(int request, Response response) {

        switch (request){
            case REQUEST_VIAJES:
                processViajes(response);
                break;

        }

    }
}
