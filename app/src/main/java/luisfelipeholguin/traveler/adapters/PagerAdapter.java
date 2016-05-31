package luisfelipeholguin.traveler.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julian on 28/05/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    List<Fragment> data;

    public PagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        List<String> titles = new ArrayList<>();
        titles.add("");
        titles.add("");
        titles.add("");
        titles.add("");
        return titles.get(position);
    }
}
