package com.example.agendamobile;

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
        fragmentTransaction.add(R.id.fragmento_entrada, fragmentoEntrada);
        fragmentTransaction.commit();

        FragmentoSaida FragmentoSaida = new FragmentoSaida();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmento_saida, FragmentoSaida);
        fragmentTransaction.commit();
    }
}
