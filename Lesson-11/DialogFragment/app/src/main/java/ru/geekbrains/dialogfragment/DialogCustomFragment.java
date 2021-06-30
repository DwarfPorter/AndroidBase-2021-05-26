package ru.geekbrains.dialogfragment;

import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class DialogCustomFragment extends DialogFragment {

    private EditText editText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dialogfragment_custom, null);

        setCancelable(false);

        editText = view.findViewById(R.id.editText);
        view.findViewById(R.id.button).setOnClickListener(v -> {
            dismiss();
            String answer = editText.getText().toString();
            ((MainActivity) requireActivity()).onDialogResult(answer);
        });

        Button date = view.findViewById(R.id.buttonDate);
        date.setOnClickListener(v -> {
            DatePickerDialog.OnDateSetListener d = (view1, year, monthOfYear, dayOfMonth) -> {
                Toast.makeText(getContext(), String.format("%d-%d-%d", year, monthOfYear, dayOfMonth), Toast.LENGTH_SHORT).show();
            };

            new DatePickerDialog(getContext(), d, 2021, 6, 30)
                    .show();
        });

        return view;
    }
}
