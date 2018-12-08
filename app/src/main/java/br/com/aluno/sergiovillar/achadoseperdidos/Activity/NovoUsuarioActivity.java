package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.Usuarios;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;
import br.com.aluno.sergiovillar.achadoseperdidos.R;


public class NovoUsuarioActivity extends AppCompatActivity {

    private EditText edtNome, edtEmail, edtSenha, edtConfSenha;
    private Button btnSalvar, btnVoltar;
    private Usuarios usuario;
    private FirebaseAuth autentica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        getSupportActionBar().setTitle("Cadastro de usuário");
        edtNome =  findViewById(R.id.edtNovoUsuNome);
        edtEmail =  findViewById(R.id.edtNovoUusEmail);
        edtSenha = findViewById(R.id.edtNovoUsuSenha);
        edtConfSenha =  findViewById(R.id.edtNovoUsuConfSenha);
        btnVoltar =  findViewById(R.id.btnNovoUusVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NovoUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSalvar =  findViewById(R.id.btnNovoUusSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtSenha.getText().toString().equals(edtConfSenha.getText().toString())) {
                    usuario = new Usuarios();
                    usuario.setNome(edtNome.getText().toString());
                    usuario.setEmail(edtEmail.getText().toString());
                    usuario.setSenha(edtSenha.getText().toString());
                    salvarUsuario();
                } else {
                    Toast.makeText(NovoUsuarioActivity.this, "As senhas não conferem", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void salvarUsuario() {
        autentica = ConexaoFirebase.getAutentica();
        autentica.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(NovoUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(NovoUsuarioActivity.this, "Cadastro realizado", Toast.LENGTH_LONG).show();
                    usuario.salvar();
                    acessarLogin();
                } else {
                    String excep ;
                    try {
                        throw task.getException();
                    } catch (Exception e) {
                        excep = e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(NovoUsuarioActivity.this, "Erro, contate suporte " + excep, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void acessarLogin() {
        Intent intent = new Intent(NovoUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
