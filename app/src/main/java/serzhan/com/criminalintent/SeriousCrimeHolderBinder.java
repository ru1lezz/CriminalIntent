package serzhan.com.criminalintent;

import android.view.View;
import android.widget.Toast;

public class SeriousCrimeHolderBinder extends CrimeHolderBinder implements View.OnClickListener{

    private final Crime mCrime;

    public SeriousCrimeHolderBinder(Crime crime, int viewType) {
        super(viewType);
        mCrime = crime;
    }

    @Override
    public void bindViewHolder(CrimeAdapter.CrimeHolder holder) {
        CrimeAdapter.SeriousCrimeHolder seriousCrimeHolder = (CrimeAdapter.SeriousCrimeHolder) holder;
        seriousCrimeHolder.mTitleTextView.setText(mCrime.getTitle());
        seriousCrimeHolder.mDateTextView.setText(simpleDateFormat.format(mCrime.getDate()));
        seriousCrimeHolder.mContactPoliceButton.setOnClickListener((view) -> {
            Toast.makeText(view.getContext(), "Police contacted for " + mCrime.getTitle(), Toast.LENGTH_SHORT).show();
        });
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public Crime getItem() {
        return mCrime;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked " + mCrime.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
