package luisfelipeholguin.traveler;



import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.List;

import luisfelipeholguin.traveler.adapters.PagerAdapter;
import luisfelipeholguin.traveler.fragments.PerfilFragment;
import luisfelipeholguin.traveler.fragments.PublicarFragment;
import luisfelipeholguin.traveler.fragments.ReservasFragment;
import luisfelipeholguin.traveler.fragments.ViajesFragment;


public class MainActivity extends AppCompatActivity  {

    ViewPager pager;
    PagerAdapter adapter;

    Toolbar toolbar;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabs = (TabLayout) findViewById(R.id.tabs);

        pager = (ViewPager) findViewById(R.id.pager);
        List<Fragment> data = new ArrayList<>();

        ViajesFragment viajes = new ViajesFragment();
        PublicarFragment publicar = new PublicarFragment();
        ReservasFragment reservas = new ReservasFragment();
        PerfilFragment perfil = new PerfilFragment();

        data.add(viajes);
        data.add(publicar);
        data.add(reservas);
        data.add(perfil);

        adapter = new PagerAdapter(getSupportFragmentManager(),data);
        pager.setAdapter(adapter);

        tabs.setupWithViewPager(pager);

    }
}
