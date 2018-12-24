package serzhan.com.criminalintent;

import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class CrimeHolderBinder {

    protected final int viewType;
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault());


    public CrimeHolderBinder(int viewType) {
        this.viewType = viewType;
    }

    public abstract void bindViewHolder(CrimeAdapter.CrimeHolder holder);

    public abstract Crime getItem();

}
