package serzhan.com.criminalintent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    private List<Crime> mCrimes;
    private final List<CrimeHolderBinder> mBinders;
    private SparseArray<CrimeHolderFactory> mFactoryMap;

    public CrimeAdapter(List<Crime> crimes) {
        mCrimes = crimes;
        mBinders = new ArrayList<>();
        initFactory();
    }

    private void initFactory() {
        mFactoryMap = new SparseArray<>();
        mFactoryMap.put(0, new RegularCrimeHolderFactory());
        mFactoryMap.put(1, new SeriousCrimeHolderFactory());
    }

    @NonNull
    @Override
    public CrimeAdapter.CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CrimeHolderFactory factory = mFactoryMap.get(i);
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        return factory.createCrimeHolder(viewGroup, layoutInflater);
    }

    private CrimeHolderBinder generateBinder(Crime crime) {
        if (crime.isRequiresPolice()) {
            return new SeriousCrimeHolderBinder(crime, 1);
        } else {
            return new RegularCrimeHolderBinder(crime, 0);
        }
    }

    public void setData(List<Crime> crimes) {
        mBinders.clear();
        for(Crime crime: crimes) {
            mBinders.add(generateBinder(crime));
        }
        notifyDataSetChanged();
    }

    public void onNewData(List<Crime> crimes) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CrimeDiffUtilCallback(crimes, mCrimes));
        diffResult.dispatchUpdatesTo(this);
        mCrimes.clear();
        mCrimes.addAll(crimes);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeAdapter.CrimeHolder holder, int i) {
        CrimeHolderBinder binder = mBinders.get(i);
        if(binder != null) {
            binder.bindViewHolder(holder);
        }
    }

    @Override
    public int getItemCount() {
        return mCrimes.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mCrimes.get(position).isRequiresPolice()) {
            return 1;
        }

        return 0;
    }

    public static abstract class CrimeHolder extends RecyclerView.ViewHolder{

        TextView mTitleTextView;
        TextView mDateTextView;
        ImageView mSolvedImageView;

        public CrimeHolder(@NonNull View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }
    }

    public static class RegularCrimeHolder extends CrimeAdapter.CrimeHolder {

        public RegularCrimeHolder(View itemView) {
            super(itemView);
        }

    }

    public static class SeriousCrimeHolder extends CrimeAdapter.CrimeHolder {

        Button mContactPoliceButton;

        public SeriousCrimeHolder(View itemView) {
            super(itemView);
            mContactPoliceButton = itemView.findViewById(R.id.contact_police_button);
        }
    }
}
