package com.bronyst.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Date;

public class DatePickerFragment extends DialogFragment {
    public static final String ARG_DATE = "date";
    private DatePicker datePicker;

    public static DatePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);
        return  datePickerFragment;
    }

    View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.crime_datepicker_label)
                .setPositiveButton(android.R.string.ok, null).create();
    }
}
