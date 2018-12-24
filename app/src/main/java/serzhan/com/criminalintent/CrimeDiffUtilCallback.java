package serzhan.com.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

public class CrimeDiffUtilCallback extends DiffUtil.Callback {

    private List<Crime> mOldList;
    private List<Crime> mNewList;

    public CrimeDiffUtilCallback(List<Crime> newList, List<Crime> oldList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return mOldList.get(i).getId().equals(mNewList.get(i1).getId());
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return mOldList.get(i).equals(mNewList.get(i1));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Crime newCrime = mNewList.get(newItemPosition);
        Crime oldCrime = mOldList.get(oldItemPosition);
        Bundle diff = new Bundle();
        if (newCrime.isSolved() != oldCrime.isSolved()) {
            diff.putBoolean("solved", newCrime.isSolved());
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
