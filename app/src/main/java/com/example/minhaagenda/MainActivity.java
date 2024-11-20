package com.example.minhaagenda;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentoEntrada fragmentoEntrada = new FragmentoEntrada();
        fragmentTransaction.add(R.id.fragment_entrada, fragmentoEntrada);
        fragmentTransaction.commit();

        FragmentoVisualizacao fragmentoVisualizacao = new FragmentoVisualizacao();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_visualizacao, fragmentoVisualizacao);
        fragmentTransaction.commit();
    }
}