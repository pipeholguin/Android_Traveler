package luisfelipeholguin.traveler.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.TemplateViajeBinding;
import luisfelipeholguin.traveler.models.Viaje;

/**
 * Created by luisfelipeholguin on 30/05/16.
 */
public class ViajeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public interface OnItemClickAdpapter{
        void onClick(View v);
    }


    Context context;
    List<Viaje> data;
    OnItemClickAdpapter onItemClickAdpapter;

    public ViajeAdapter(Context context, List<Viaje> data, OnItemClickAdpapter onItemClickAdpapter) {
        this.context = context;
        this.data = data;
        this.onItemClickAdpapter = onItemClickAdpapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context, R.layout.template_viaje, null);
        viewHolder = new ViajeViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Viaje v = (Viaje) data.get(position);
        ViajeViewHolder h = (ViajeViewHolder) holder;
        ((ViajeViewHolder) holder).binding.setViaje(v);
        h.binding.getRoot().setOnClickListener(this);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {

        onItemClickAdpapter.onClick(v);
    }

    static class ViajeViewHolder extends RecyclerView.ViewHolder{


        TemplateViajeBinding binding;

        public ViajeViewHolder(View itemView){
            super(itemView);
            binding = TemplateViajeBinding.bind(itemView);
        }
    }
}
