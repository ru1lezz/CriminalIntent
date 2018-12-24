package serzhan.com.criminalintent;

import android.content.Intent;
import android.view.View;

public class RegularCrimeHolderBinder extends CrimeHolderBinder implements View.OnClickListener{
    private final Crime mCrime;

    public RegularCrimeHolderBinder(Crime crime, int viewType) {
        super(viewType);
        mCrime = crime;
    }

    @Override
    public void bindViewHolder(CrimeAdapter.CrimeHolder holder) {
        CrimeAdapter.RegularCrimeHolder regularCrimeHolder = (CrimeAdapter.RegularCrimeHolder) holder;
        regularCrimeHolder.mTitleTextView.setText(mCrime.getTitle());
        regularCrimeHolder.mDateTextView.setText(simpleDateFormat.format(mCrime.getDate()));
        regularCrimeHolder.mSolvedImageView.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public Crime getItem() {
        return mCrime;
    }

    @Override
    public void onClick(View v) {
        Intent intent = CrimeActivity.newIntent(v.getContext(), mCrime.getId());
        v.getContext().startActivity(intent);
    }
}
