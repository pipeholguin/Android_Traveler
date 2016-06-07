package luisfelipeholguin.traveler.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import luisfelipeholguin.traveler.LoginActivity;
import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentPublicarBinding;
import luisfelipeholguin.traveler.net.api.ViajesApi;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicarFragment extends Fragment implements View.OnClickListener, ViajesApi.OnPublish {

    private final int SELECT_PHOTO = 10;
    private static final int CAMERA_REQUEST = 20;
    FragmentPublicarBinding binding;
    ViajesApi api;
    String encodeimage;




    public PublicarFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPublicarBinding.inflate(getLayoutInflater(savedInstanceState));
        binding.setOnClick(this);;
        api = new ViajesApi(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {

        /*Date d = null;
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        try {
            d = format.parse(binding.fecha.getText().toString());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }*/
        //Calendar fecha = Calendar.getInstance();
        //fecha.setTime(d);

        switch (view.getId()){
            case R.id.publicar:
                String fecha = binding.fecha.getText().toString();

                api.publicar(binding.origen.getText().toString(),binding.destino.getText().toString(),
                        Integer.parseInt(binding.precio.getText().toString()),
                        Integer.parseInt(binding.asientos.getText().toString()),
                        fecha,binding.carro.getText().toString(), encodeimage,
                        Integer.parseInt(binding.contacto.getText().toString()),this);
                break;


            case 1:
                Intent intentcamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentcamera, CAMERA_REQUEST);
                break;

            case R.id.seleccion :
                Intent intentpick = new Intent(Intent.ACTION_PICK);
                intentpick.setType("image/*");
                startActivityForResult(intentpick, SELECT_PHOTO);

                break;

        }
        }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch(requestCode) {
            case SELECT_PHOTO:
                Uri uri = data.getData();
                binding.selected.setImageURI(uri);
                try {

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), uri);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    byte[] b = outputStream.toByteArray();
                    encodeimage = Base64.encodeToString(b, Base64.DEFAULT);

                } catch (IOException e) {
                    e.printStackTrace();
                }

               // ByteArrayOutputStream baos = new ByteArrayOutputStream();
               // bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
               // byte[] b = baos.toByteArray();

              break;
            case  CAMERA_REQUEST:
                Uri uri1 = data.getData();
                Bitmap bitmap = null;
                binding.selected.setImageURI(uri1);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), uri1);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    byte[] b = outputStream.toByteArray();
                    encodeimage = Base64.encodeToString(b, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

                }
        }



    @Override
    public void onPublish(String status) {

        Log.d("STATUS: ",""+status);
        if (status.equals("OK")){
            Toast.makeText(getContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
        }

    }
}
