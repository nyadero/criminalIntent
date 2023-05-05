package com.bronyst.criminalintent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CrimeListAdapter crimeListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mRecyclerView = view.findViewById(R.id.crime_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimeList = crimeLab.getCrimeList();
        crimeListAdapter = new CrimeListAdapter(crimeList);
        mRecyclerView.setAdapter(crimeListAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewTitle, mTextViewDate;
        private ImageView imageView;
        private Crime crime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.crime_item, container, false));
            itemView.setOnClickListener(this);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mTextViewDate = (TextView) itemView.findViewById(R.id.txt_date);
            imageView = (ImageView) itemView.findViewById(R.id.imv_solved);
        }

        public void bind(Crime mCrime){
            crime = mCrime;
            mTextViewTitle.setText(crime.getTitle());
            mTextViewDate.setText(crime.getDate().toString());
            imageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Clicked on " + crime.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeListAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> crimeList;

        public CrimeListAdapter(List<Crime> crimes){
            crimeList = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
             Crime crime = crimeList.get(position);
             holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return crimeList.size();
        }
    }
}
