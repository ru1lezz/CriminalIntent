package serzhan.com.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CrimeListFragment extends Fragment{

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.getInstance(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
            mAdapter.setData(crimes);
        } else {
            mAdapter.onNewData(crimes);
        }
    }

//    public abstract class CrimeHolder extends RecyclerView.ViewHolder {
//
//        TextView mTitleTextView;
//        TextView mDateTextView;
//        ImageView mSolvedImageView;
//
//        public CrimeHolder(View itemView) {
//            super(itemView);
//
//            mTitleTextView = itemView.findViewById(R.id.crime_title);
//            mDateTextView = itemView.findViewById(R.id.crime_date);
//            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
//
//        }
//    }

//    public class RegularCrimeHolder extends CrimeHolder {
//
//        public RegularCrimeHolder(View itemView) {
//            super(itemView);
//        }
//    }
//
//    public class SeriousCrimeHolder extends CrimeHolder {
//
//        Button mContactPoliceButton;
//
//        public SeriousCrimeHolder(View itemView) {
//            super(itemView);
//            mContactPoliceButton = itemView.findViewById(R.id.contact_police_button);
//        }
//    }

//    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
//
//        private List<Crime> mCrimes;
//        private final List<CrimeHolderBinder> mBinders;
//        private SparseArray<CrimeHolderFactory> mFactoryMap;
//
//        public CrimeAdapter(List<Crime> crimes) {
//            mCrimes = crimes;
//            mBinders = new ArrayList<>();
//            initFactory();
//        }
//
//        private void initFactory() {
//            mFactoryMap = new SparseArray<>();
//            mFactoryMap.put(0, new RegularCrimeHolderFactory());
//            mFactoryMap.put(1, new SeriousCrimeHolderFactory());
//        }
//
//        @Override
//        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            CrimeHolderFactory factory = mFactoryMap.get(viewType);
//            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//            return factory.createCrimeHolder(parent, layoutInflater);
//        }
//
//        private CrimeHolderBinder generateBinder(Crime crime) {
//            if (crime.isRequiresPolice()) {
//                return new SeriousCrimeHolderBinder(crime, 1);
//            } else {
//                return new RegularCrimeHolderBinder(crime, 0);
//            }
//        }
//
//        private void setData(List<Crime> crimes) {
//            mBinders.clear();
//            for(Crime crime: crimes) {
//                mBinders.add(generateBinder(crime));
//            }
//            notifyDataSetChanged();
//        }
//
//        public void onNewData(List<Crime> crimes) {
//            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CrimeDiffUtilCallback(crimes, mCrimes));
//            diffResult.dispatchUpdatesTo(this);
//            mCrimes.clear();
//            mCrimes.addAll(crimes);
//        }
//
//        @Override
//        public void onBindViewHolder(CrimeHolder holder, int position) {
//            CrimeHolderBinder binder = mBinders.get(position);
//            if (binder != null) {
//                binder.bindViewHolder(holder);
//            }
//        }
//
//        @Override
//        public void onBindViewHolder(CrimeHolder holder, int position, List<Object> payloads) {
//            if(payloads.isEmpty()) {
//                super.onBindViewHolder(holder, position, payloads);
//            } else {
//                Bundle o = (Bundle) payloads.get(0);
//                for(String key : o.keySet()) {
//                    if(key.equals("solved")) {
//                        holder.mSolvedImageView.setVisibility(mCrimes.get(position).isSolved() ? View.VISIBLE : View.GONE);
//                    }
//                }
//            }
//        }
//
//        @Override
//        public int getItemCount() {
//            return mCrimes.size();
//        }
//
//        @Override
//        public int getItemViewType(int position) {
//            if (mCrimes.get(position).isRequiresPolice()) {
//                return 1;
//            }
//            return 0;
//        }
//    }

//    public abstract class CrimeHolderBinder {
//
//        protected final int viewType;
//        protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault());
//
//
//        public CrimeHolderBinder(int viewType) {
//            this.viewType = viewType;
//        }
//
//        public abstract void bindViewHolder(CrimeHolder holder);
//
//        public abstract Crime getItem();
//
//    }
//
//    public class RegularCrimeHolderBinder extends CrimeHolderBinder implements View.OnClickListener{
//
//        private final Crime mCrime;
//
//        public RegularCrimeHolderBinder(Crime crime, int viewType) {
//            super(viewType);
//            mCrime = crime;
//        }
//
//        @Override
//        public void bindViewHolder(CrimeHolder holder) {
//            RegularCrimeHolder regularCrimeHolder = (RegularCrimeHolder) holder;
//            regularCrimeHolder.mTitleTextView.setText(mCrime.getTitle());
//            regularCrimeHolder.mDateTextView.setText(simpleDateFormat.format(mCrime.getDate()));
//            regularCrimeHolder.mSolvedImageView.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
//            holder.itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public Crime getItem() {
//            return mCrime;
//        }
//
//        @Override
//        public void onClick(View v) {
//            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
//            startActivity(intent);
//        }
//    }
//
//    public class SeriousCrimeHolderBinder extends CrimeHolderBinder implements View.OnClickListener{
//
//        private final Crime mCrime;
//
//        public SeriousCrimeHolderBinder(Crime crime, int viewType) {
//            super(viewType);
//            mCrime = crime;
//        }
//
//        @Override
//        public void bindViewHolder(CrimeHolder holder) {
//            SeriousCrimeHolder seriousCrimeHolder = (SeriousCrimeHolder) holder;
//            seriousCrimeHolder.mTitleTextView.setText(mCrime.getTitle());
//            seriousCrimeHolder.mDateTextView.setText(simpleDateFormat.format(mCrime.getDate()));
//            seriousCrimeHolder.mContactPoliceButton.setOnClickListener((view) -> {
//                Toast.makeText(getActivity(), "Police contacted for " + mCrime.getTitle(), Toast.LENGTH_SHORT).show();
//            });
//            holder.itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public Crime getItem() {
//            return mCrime;
//        }
//
//        @Override
//        public void onClick(View v) {
//            Toast.makeText(getActivity(), "Clicked " + mCrime.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//    }

//    public class RegularCrimeHolderFactory implements CrimeHolderFactory {
//
//        @Override
//        public CrimeHolder createCrimeHolder(ViewGroup parent, LayoutInflater inflater) {
//            View itemView = inflater.inflate(R.layout.list_item_crime, parent, false);
//            CrimeHolder holder = new RegularCrimeHolder(itemView);
//            return holder;
//        }
//    }
//
//    public class SeriousCrimeHolderFactory implements CrimeHolderFactory {
//
//        @Override
//        public CrimeHolder createCrimeHolder(ViewGroup parent, LayoutInflater inflater) {
//            View itemView = inflater.inflate(R.layout.list_item_crime_police, parent, false);
//            CrimeHolder holder = new SeriousCrimeHolder(itemView);
//            return holder;
//        }
//    }

}
