package luisfelipeholguin.traveler.attrs;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by luisfelipeholguin on 31/05/16.
 */
public class Attrs {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView img, String url) {
        Picasso.with(img.getContext()).load(Uri.parse(url)).into(img);

    }
    @BindingAdapter("app:textDate")
    public static void loadFecha(TextView txt, Date date){
        if (date !=null)
            txt.setText(format.format(date));
}


}



