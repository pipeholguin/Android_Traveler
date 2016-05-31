package luisfelipeholguin.traveler.attrs;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by luisfelipeholguin on 31/05/16.
 */
public class Attrs {

    @BindingAdapter("app:imgUrl")
    public static void loadImg(ImageView img, String url) {
        Picasso.with(img.getContext()).load(Uri.parse(url)).into(img);


    }
}
