package com.example.minhaagenda;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class FragmentoVisualizacao extends Fragment {

    private static final String TAG = "FragmentoVisualizacao";
    private CompromissoDB compromissoDB;
    private LinearLayout containerCompromissos;
    private Button botaoHoje;
    private Button botaoOutraData;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_visualizacao, container, false);

        compromissoDB = new CompromissoDB(requireContext());
        containerCompromissos = view.findViewById(R.id.containerCompromissos);
        botaoHoje = view.findViewById(R.id.botaoHoje);
        botaoOutraData = view.findViewById(R.id.botaoOutraData);

        botaoHoje.setOnClickListener(v -> filtrarCompromissosPorHoje());
        botaoOutraData.setOnClickListener(v -> abrirDatePicker());

        carregarCompromissos(compromissoDB.getCompromissos());

        return view;
    }

    private void carregarCompromissos(List<Compromisso> compromissos) {
        containerCompromissos.removeAllViews();

        if (compromissos.isEmpty()) {
            Log.d(TAG, "Nenhum compromisso encontrado.");
            TextView textView = new TextView(requireContext());
            textView.setText("Nenhum compromisso encontrado.");
            textView.setTextSize(16);
            textView.setTextColor(requireContext().getResources().getColor(R.color.textColor));
            containerCompromissos.addView(textView);
            return;
        }

        for (Compromisso compromisso : compromissos) {
            adicionarCompromissoAoContainer(compromisso);
        }
    }

    private void adicionarCompromissoAoContainer(Compromisso compromisso) {
        View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_compromisso, containerCompromissos, false);

        TextView textViewData = itemView.findViewById(R.id.textViewData);
        TextView textViewHora = itemView.findViewById(R.id.textViewHora);
        TextView textViewDescricao = itemView.findViewById(R.id.textViewDescricao);

        textViewData.setText("Data: " + compromisso.getData());
        textViewHora.setText("Hora: " + compromisso.getHora());
        textViewDescricao.setText("Descrição: " + compromisso.getDescricao());

        containerCompromissos.addView(itemView);
    }

    private void filtrarCompromissosPorHoje() {
        Calendar hoje = Calendar.getInstance();
        String dataHoje = String.format("%02d/%02d/%04d",
                hoje.get(Calendar.DAY_OF_MONTH),
                hoje.get(Calendar.MONTH) + 1,
                hoje.get(Calendar.YEAR));

        List<Compromisso> compromissosHoje = compromissoDB.getCompromissos().stream()
                .filter(c -> c.getData().equals(dataHoje))
                .collect(Collectors.toList());

        carregarCompromissos(compromissosHoje);
    }

    private void abrirDatePicker() {
        Calendar calendario = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    List<Compromisso> compromissosFiltrados = compromissoDB.getCompromissos().stream()
                            .filter(c -> c.getData().equals(dataSelecionada))
                            .collect(Collectors.toList());
                    carregarCompromissos(compromissosFiltrados);
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}
