package com.example.minhaagenda;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import java.util.List;

public class FragmentoEntrada extends Fragment {

    private EditText descricaoCompromisso;
    private EditText dataSelecionadaText;
    private EditText horaSelecionadaText;
    private Button botaoSalvar;
    private CompromissoDB compromissoDB;
    private Calendar dataSelecionada = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_entrada, container, false);

        descricaoCompromisso = view.findViewById(R.id.descricaoCompromisso);
        dataSelecionadaText = view.findViewById(R.id.dataSelecionadaText);
        horaSelecionadaText = view.findViewById(R.id.horaSelecionadaText);
        botaoSalvar = view.findViewById(R.id.botaoSalvar);

        compromissoDB = new CompromissoDB(requireContext());

        dataSelecionadaText.setOnClickListener(v -> abrirDatePicker());
        horaSelecionadaText.setOnClickListener(v -> abrirTimePicker());
        botaoSalvar.setOnClickListener(v -> salvarCompromisso());

        return view;
    }

    private void abrirDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    String dataFormatada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    dataSelecionadaText.setText(dataFormatada);
                },
                dataSelecionada.get(Calendar.YEAR),
                dataSelecionada.get(Calendar.MONTH),
                dataSelecionada.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void abrirTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay, minute) -> {
                    String horaFormatada = String.format("%02d:%02d", hourOfDay, minute);
                    horaSelecionadaText.setText(horaFormatada);
                },
                dataSelecionada.get(Calendar.HOUR_OF_DAY),
                dataSelecionada.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void salvarCompromisso() {
        String data = dataSelecionadaText.getText().toString();
        String hora = horaSelecionadaText.getText().toString();
        String descricao = descricaoCompromisso.getText().toString();

        if (data.isEmpty() || hora.isEmpty() || descricao.isEmpty()) {
            Log.e("FragmentoEntrada", "Todos os campos devem ser preenchidos.");
            return;
        }

        Compromisso compromisso = new Compromisso(data, hora, descricao);
        compromissoDB.addCompromisso(compromisso);

        Log.d("FragmentoEntrada", "=== COMPROMISSO INSERIDO ===: Data: " + data + ", Hora: " + hora + ", Descricao: " + descricao);
       
        imprimirBancoDeDados();

        limparCampos();
    }

    private void limparCampos() {
        descricaoCompromisso.setText("");
        dataSelecionadaText.setText("");
        horaSelecionadaText.setText("");
        dataSelecionada = Calendar.getInstance();
    }

    private void imprimirBancoDeDados() {
        List<Compromisso> compromissos = compromissoDB.getCompromissos();
        if (compromissos.isEmpty()) {
            Log.d("FragmentoEntrada", "Banco de dados vazio.");
            return;
        }

        // Imprime cada compromisso no Logcat
        Log.d("FragmentoEntrada", "=== BANCO DE DADOS ===");
        for (Compromisso compromisso : compromissos) {
            Log.d("FragmentoEntrada", "Data: " + compromisso.getData() + ", Hora: " + compromisso.getHora() + ", Descricao: " + compromisso.getDescricao());
        }
        Log.d("FragmentoEntrada", "--------------------------------");
    }
}
