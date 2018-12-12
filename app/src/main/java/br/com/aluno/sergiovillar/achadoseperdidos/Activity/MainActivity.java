package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAcessarLogin, btnAchados, btnPerdidos, btnTeste;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Tela Principal");
        btnAcessarLogin = (Button)findViewById(R.id.btnAcessarLogin);
        btnAchados = (Button)findViewById(R.id.btnMainAchados);
        btnTeste = (Button)findViewById(R.id.btnTeste);
        btnPerdidos = (Button)findViewById(R.id.btnMainPerdidos);
        btnAcessarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentLogin);
            }
        });
        btnAchados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Toast.makeText(MainActivity.this, "Teste"+currentUser.getUid(), Toast.LENGTH_LONG).show();
            }
        });
        btnPerdidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerdidos = new Intent(MainActivity.this, ReportarOcorrenciaActivity.class);
                startActivity(intentPerdidos);
            }
        });
        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPerdidos = new Intent(MainActivity.this, PrincipalOcorrencias.class);
                startActivity(intentPerdidos);
            }
        });

    }

}
