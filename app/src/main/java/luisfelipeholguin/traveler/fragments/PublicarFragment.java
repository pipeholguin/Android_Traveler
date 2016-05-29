package luisfelipeholguin.traveler.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import luisfelipeholguin.traveler.R;
import luisfelipeholguin.traveler.databinding.FragmentPublicarBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublicarFragment extends Fragment {

    FragmentPublicarBinding binding;

    public PublicarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPublicarBinding.inflate(getLayoutInflater(savedInstanceState));
        setList();
        return binding.getRoot();
    }

    public void setList(){
        String list = "hola";
        binding.setList(list);
    }

}
