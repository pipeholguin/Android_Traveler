package luisfelipeholguin.traveler.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.adapters.ViajeAdapter;
import luisfelipeholguin.traveler.databinding.FragmentViajesBinding;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.api.UsuarioApi;
import luisfelipeholguin.traveler.net.api.ViajesApi;
import luisfelipeholguin.traveler.util.L;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViajesFragment extends Fragment implements ViajesApi.OnViajes, ViajeAdapter.OnItemClickAdpapter {

    public interface OnHomeItemClick{
        void  onHomeClick(int pos);
    }

    FragmentViajesBinding binding;
    ViajeAdapter adapter;
    LinearLayoutManager manager;
    OnHomeItemClick onHomeItemClick;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onHomeItemClick= (OnHomeItemClick) context;
    }


    public ViajesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViajesBinding.inflate(getLayoutInflater(savedInstanceState));
        manager = new LinearLayoutManager(getActivity());

        L.data = new ArrayList<>();
        adapter = new ViajeAdapter(getContext(), L.data, this);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(manager);

        ViajesApi api = new ViajesApi(getActivity());
        api.getViajes(this);

        return binding.getRoot();
    }

    @Override
    public void onViajes(List<Viaje> data) {
        for (Viaje v: data){
           L.data.add(v);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

        int pos = binding.recycler.getChildAdapterPosition(v);
        onHomeItemClick.onHomeClick(pos);

    }
}
