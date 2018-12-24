package serzhan.com.criminalintent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RegularCrimeHolderFactory implements CrimeHolderFactory {
    @Override
    public CrimeAdapter.CrimeHolder createCrimeHolder(ViewGroup parent, LayoutInflater inflater) {
        View itemView = inflater.inflate(R.layout.list_item_crime, parent, false);
        CrimeAdapter.CrimeHolder holder = new CrimeAdapter.RegularCrimeHolder(itemView);
        return holder;
    }
}
