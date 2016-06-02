package luisfelipeholguin.traveler.net.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
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

    static final int REQUEST_VIAJES=0;
    static final int REQUEST_PUBLICAR=1;
    static final int REQUEST_RESERVAR=2;
    static final int REQUEST_GETRESERVAS=3;

    public interface OnViajes{
        void  onViajes(List<Viaje> data);
    }

    public interface OnPublish{
        void onPublish(String status);
    }

    public interface OnReserva{
        void onReserva(String status);
    }

    OnViajes onViajes;
    OnPublish onPublish;
    OnReserva onReserva;

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

    public void publicar(String origen, String destino, int precio,
                              int asientos, String fecha, String carro, String imagen,
                              int contacto, OnPublish onPublish){
        this.onPublish= onPublish;
        String url = urlBase+context.getString(R.string.url_viajes);
        JsonObject json = new JsonObject();
        json.addProperty("origen", origen);
        json.addProperty("destino", destino);
        json.addProperty("precio", precio);
        json.addProperty("asientos", asientos);
        //json.addProperty("hora", fecha.get(Calendar.HOUR_OF_DAY)+":"+fecha.get(Calendar.MINUTE));
        //json.addProperty("fecha", fecha.get(Calendar.YEAR)+"-"+fecha.get(Calendar.MONTH)+"-"+fecha.get(Calendar.DAY_OF_MONTH));
        json.addProperty("hora", "5:00");
        json.addProperty("fecha", "31-12-2016");
        json.addProperty("carro", carro);
        json.addProperty("imagen", imagen);
        json.addProperty("contacto", contacto);

        HttpAsyncTask task = makeTask(REQUEST_PUBLICAR, HttpAsyncTask.METHOD_POST);
        task.execute(url, json.toString());
        Log.d("TASK EXEC","URL:"+url+" JSON:"+json.toString());
    }

    private void processPublicar(Response response){
        Log.d("RPTAmsg",""+response.getMsg());
        Log.d("VALIDATERPTA: ",""+validateError(response));
        if (validateError(response)){
            Viaje viaje = gson.fromJson(response.getMsg(), Viaje.class);
            onPublish.onPublish(viaje.getStatus());
        }
    }

    public void reservar(String user, int idViaje, OnReserva onReserva)
    {
        this.onReserva = onReserva;
        String url = urlBase+context.getString(R.string.url_reserva)+user+"/"+idViaje;
        HttpAsyncTask task = makeTask(REQUEST_RESERVAR, HttpAsyncTask.METHOD_POST);
        task.execute(url,"");
        Log.d("TASK EXEC", "URL:"+url);
    }

    private void processReservar(Response response){
        Log.d("RPTAmsg",""+response.getMsg());
        Log.d("VALIDATERPTA: ",""+validateError(response));
        if (validateError(response)){
            Viaje viaje = gson.fromJson(response.getMsg(), Viaje.class);
            onReserva.onReserva(viaje.getStatus());
        }
    }

    public void getReservas(OnViajes onViajes){
        this.onViajes = onViajes;
        String url = urlBase + context.getString(R.string.url_reserva)+"luis";
        HttpAsyncTask task = makeTask(REQUEST_GETRESERVAS, HttpAsyncTask.METHOD_GET);
        task.execute(url);
        Log.d("TASK EXEC", "URL:"+url);
    }

    private void processGetReserva(Response response) {

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
            case REQUEST_PUBLICAR:
                processPublicar(response);
                break;
            case REQUEST_RESERVAR:
                processReservar(response);
                break;
            case REQUEST_GETRESERVAS:
                processGetReserva(response);
                break;
        }

    }
}
