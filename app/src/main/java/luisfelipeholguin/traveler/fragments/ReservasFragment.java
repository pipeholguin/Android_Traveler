package luisfelipeholguin.traveler.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import luisfelipeholguin.traveler.MainActivity;
import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.adapters.ViajeAdapter;
import luisfelipeholguin.traveler.databinding.FragmentReservasBinding;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.api.ViajesApi;
import luisfelipeholguin.traveler.util.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservasFragment extends Fragment implements ViajesApi.OnViajes, ViajeAdapter.OnItemClickAdpapter, View.OnClickListener {

    public interface OnHomeItemClick{
        void  onHomeClick(int pos);
    }

    FragmentReservasBinding binding;
    ViajeAdapter adapter;
    LinearLayoutManager manager;
    OnHomeItemClick onHomeItemClick;

    public ReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onHomeItemClick = (OnHomeItemClick) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentReservasBinding.inflate(getLayoutInflater(savedInstanceState));

        manager = new LinearLayoutManager(getActivity());

        L.data = new ArrayList<>();
        adapter = new ViajeAdapter(getContext(), L.data, this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(manager);

        loadReservas();
        binding.actualizar.setOnClickListener(this);
        return binding.getRoot();
    }

    private void loadReservas() {
        L.data.clear();
        List<Viaje> data = Viaje.listAll(Viaje.class);
        if (data != null){
            for (Viaje v: data){
                L.data.add(v);
            }
            adapter.notifyDataSetChanged();
        } else {
            Log.d("RESERVAS","DATA: NULL");
        }
    }

    @Override
    public void onViajes(List<Viaje> data) {
        Viaje.deleteAll(Viaje.class);
        for (Viaje v: data){
            v.save();
        }
        loadReservas();
    }

    @Override
    public void onClick(View v) {
        Log.d("RESERVAS","SWITCH"+v.getId());
        Log.d("RESERVAS","SWITCH"+R.id.recycler);
        switch (v.getId()){
            case -1:
                int pos = binding.recycler.getChildAdapterPosition(v);
                Log.d("RESERVAS","POS: "+pos);
                onHomeItemClick.onHomeClick(pos);
                break;
            case  R.id.actualizar:
                ViajesApi api = new ViajesApi(getActivity());
                api.getReservas(this);
                break;
        }
    }

}