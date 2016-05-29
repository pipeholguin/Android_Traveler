package luisfelipeholguin.traveler.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentViajesBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViajesFragment extends Fragment {

    FragmentViajesBinding binding;

    public ViajesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentViajesBinding.inflate(getLayoutInflater(savedInstanceState));
        setList();
        return binding.getRoot();
    }

    public void setList(){
        String list = "chao";
        binding.setList(list);
    }

}
