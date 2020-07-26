package vn.ntu.edu.phucboo.kt1234;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Calendar;

import controller.INTController;

public class FirstFragment extends Fragment implements View.OnClickListener {
    NavController navController;
    EditText editNgaydi, editnhapmua, editnhapban;
    ImageView ImgDate;
    RadioGroup rdg;
    Button Them, Inds;
    INTController ntController;
    ArrayList<String> dsTien;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navController = NavHostFragment.findNavController(this);
        ((MainActivity)getActivity()).navController = navController;
        dsTien = ((MainActivity)getActivity()).dsTien;

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editNgaydi = view.findViewById(R.id.editNgaydi);
        editnhapban = view.findViewById(R.id.editnhapban);
        editnhapmua = view.findViewById(R.id.editnhapmua);
        ImgDate = view.findViewById(R.id.ImgDate);
        rdg = view.findViewById(R.id.rdg);
        Inds = view.findViewById(R.id.Inds);
        Them = view.findViewById(R.id.Them);

        Inds.setOnClickListener(this);
        Them.setOnClickListener(this);
        ImgDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ImgDate: ImgDate(); break;
            case R.id.Inds: inDs(); break;
            case R.id.Them: them(); break;

        }
    }
    private void ImgDate() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dayOfMonth)
                        .append("/")
                        .append(month + 1)
                        .append("/")
                        .append(year);
                editNgaydi.setText(stringBuilder.toString());
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
    private void add() {
        String date = editNgaydi.getText().toString();
        String mua = editnhapmua.getText().toString();
        String ban = editnhapban.getText().toString();
        String ngoaiTe = "";
        switch (rdg.getCheckedRadioButtonId()) {
            case R.id.radioEUR: ngoaiTe =  "EUR"; break;
            case R.id.radioUSD: ngoaiTe =  "USD"; break;
            case R.id.radioCNY: ngoaiTe =  "CNY"; break;
        }
        if (date.length() > 0 && mua.length() > 0 && ban.length() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(editNgaydi.getText().toString())
                    .append("\n")
                    .append(ngoaiTe)
                    .append("\n")
                    .append("Mua vào: ")
                    .append(editnhapmua.getText().toString())
                    .append("  Bán ra: ")
                    .append(editnhapban.getText().toString());
            Toast.makeText(getActivity(), "Thêm ngoại tệ thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Chưa nhập đủ thông tin đăng ký", Toast.LENGTH_SHORT).show();
        }
    }
    private void inDs() {
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
    }

    private void them() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(editnhapban.getText().toString());
        stringBuilder.append(editnhapmua.getText().toString());
        stringBuilder.append(editNgaydi.getText().toString());


        dsTien.add(stringBuilder.toString());
    }
}
