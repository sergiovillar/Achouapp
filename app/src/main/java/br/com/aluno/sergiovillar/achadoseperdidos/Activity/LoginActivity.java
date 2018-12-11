package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;
import br.com.aluno.sergiovillar.achadoseperdidos.R;
import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.Usuarios;

public class LoginActivity extends AppCompatActivity {

    private Usuarios usuario;
    private EditText edtEmailLogin, edtSenhaLogin;
    private Button btnLogin;
    private FirebaseAuth autentica;
    private TextView tvCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Tela Login");
        edtEmailLogin = (EditText) findViewById(R.id.edtEmailLogin);
        edtSenhaLogin = (EditText) findViewById(R.id.edtSenhaLogin);
        tvCadastro = (TextView)findViewById(R.id.tvCadastrarUsuario);
        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity.this, NovoUsuarioActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSenhaLogin.getText().toString().equals("") || edtEmailLogin.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Informe o e-mail e senha", Toast.LENGTH_SHORT).show();
                } else {
                    usuario = new Usuarios();
                    usuario.setEmail(edtEmailLogin.getText().toString());
                    usuario.setSenha(edtSenhaLogin.getText().toString());
                    logar();

                }
            }
        });

    }

    private void logar() {
        autentica = ConexaoFirebase.getInstancia();
        autentica.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    carregarAplicacao();
                    Toast.makeText(LoginActivity.this, "Login realizado.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Falha de login, verifique o e-mail e senha.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void carregarAplicacao() {
        Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
