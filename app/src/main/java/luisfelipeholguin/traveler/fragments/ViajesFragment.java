package luisfelipeholguin.traveler.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.adapters.ViajeAdapter;
import luisfelipeholguin.traveler.databinding.FragmentViajesBinding;
import luisfelipeholguin.traveler.models.Viaje;
import luisfelipeholguin.traveler.net.api.UsuarioApi;
import luisfelipeholguin.traveler.util.L;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViajesFragment extends Fragment {

    FragmentViajesBinding binding;
    ViajeAdapter adapter;
    LinearLayoutManager manager;

    public ViajesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViajesBinding.inflate(getLayoutInflater(savedInstanceState));
        manager = new LinearLayoutManager(getActivity());



        L.data = new ArrayList<>();
        adapter = new ViajeAdapter(getContext(), L.data);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(manager);



        setList();
        return binding.getRoot();


    }

    public void setList(){

        Viaje v1 = new Viaje();
        v1.setDestino("Cali");

        Viaje v2 = new Viaje();
        v2.setDestino("Bogota");

        L.data.add(v1);
        L.data.add(v2);

        adapter.notifyDataSetChanged();
    }

}
