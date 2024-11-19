package com.example.agendamobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

public class FragmentoEntrada extends Fragment {

    private Button botaoData;
    private Button botaoHora;
    private EditText descricaoCompromisso;
    private Button botaoOk;
    private TextView dataSelecionadaText;
    private TextView horaSelecionadaText;
    private Calendar dataSelecionada = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_entrada, container, false);

        botaoData = view.findViewById(R.id.botaoData);
        botaoHora = view.findViewById(R.id.botaoHora);
        descricaoCompromisso = view.findViewById(R.id.descricaoCompromisso);
        botaoOk = view.findViewById(R.id.botaoOk);
        dataSelecionadaText = view.findViewById(R.id.dataSelecionadaText);
        horaSelecionadaText = view.findViewById(R.id.horaSelecionadaText);

        botaoData.setOnClickListener(v -> datePicker());
        botaoHora.setOnClickListener(v -> timePicker());

        botaoOk.setOnClickListener(v -> {
            String descricao = descricaoCompromisso.getText().toString();

            int dia = dataSelecionada.get(Calendar.DAY_OF_MONTH);
            int mes = dataSelecionada.get(Calendar.MONTH) + 1;
            int ano = dataSelecionada.get(Calendar.YEAR);
            int hora = dataSelecionada.get(Calendar.HOUR_OF_DAY);
            int minuto = dataSelecionada.get(Calendar.MINUTE);

            Log.d("CadastroCompromisso", "Data: " + dia + "/" + mes + "/" + ano +
                    ", Hora: " + hora + ":" + minuto + ", Descrição: " + descricao);
        });

        return view;
    }

    private void datePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    dataSelecionada.set(Calendar.YEAR, year);
                    dataSelecionada.set(Calendar.MONTH, month);
                    dataSelecionada.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    String dataFormatada = dayOfMonth + "/" + (month + 1) + "/" + year;
                    dataSelecionadaText.setText(dataFormatada);
                },
                dataSelecionada.get(Calendar.YEAR),
                dataSelecionada.get(Calendar.MONTH),
                dataSelecionada.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void timePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay, minute) -> {
                    dataSelecionada.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    dataSelecionada.set(Calendar.MINUTE, minute);

                    String horaFormatada = String.format("%02d:%02d", hourOfDay, minute);
                    horaSelecionadaText.setText(horaFormatada);
                },
                dataSelecionada.get(Calendar.HOUR_OF_DAY),
                dataSelecionada.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void atualizarData() {
        int dia = dataSelecionada.get(Calendar.DAY_OF_MONTH);
        int mes = dataSelecionada.get(Calendar.MONTH) + 1;
        int ano = dataSelecionada.get(Calendar.YEAR);
        dataSelecionadaText.setText("Data: " + dia + "/" + mes + "/" + ano);
    }

    private void atualizarHora() {
        int hora = dataSelecionada.get(Calendar.HOUR_OF_DAY);
        int minuto = dataSelecionada.get(Calendar.MINUTE);
        horaSelecionadaText.setText("Hora: " + String.format("%02d:%02d", hora, minuto));
    }
}
