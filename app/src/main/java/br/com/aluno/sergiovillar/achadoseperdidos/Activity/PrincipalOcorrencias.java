package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import br.com.aluno.sergiovillar.achadoseperdidos.Adapter.ViewPagerAdapter;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.FragAchados;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.FragOcorrencias;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.FragPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class PrincipalOcorrencias extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_ocorrencias);
        tabLayout = findViewById(R.id.tabLayoutId);
        appBarLayout = findViewById(R.id.appBarId);
        viewPager = findViewById(R.id.viewPagerId);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AdicionarFragmento(new FragOcorrencias(), "Minhas Ocorrencias");
        adapter.AdicionarFragmento(new FragAchados(), "Achados");
        adapter.AdicionarFragmento(new FragPerdidos(), "Perdidos");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

}
