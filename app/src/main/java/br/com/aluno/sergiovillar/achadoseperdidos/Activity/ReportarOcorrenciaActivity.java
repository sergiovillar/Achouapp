package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class ReportarOcorrenciaActivity extends AppCompatActivity {
    /*idOcorrencia, , uid;documento, descricao, localDoc, emailContato, foneContato, status*/

    private EditText edtDoc, edtDescri, edtLocalDoc, edtEmailContato, edtFoneContato;
    private RadioButton rbStatusAchado, rbStatusPerdido;
    private Button btnSubmeter, btnVoltar;
    private AchadosPerdidos achadosPerdidos;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_ocorrencia);
        getSupportActionBar().setTitle("Registrar Objeto");
        edtDoc= (EditText)findViewById(R.id.edtPerdDoc);
        edtDescri= (EditText)findViewById(R.id.edtPerdDescri);
        edtLocalDoc= (EditText)findViewById(R.id.edtPerdEndereco);
        edtEmailContato= (EditText)findViewById(R.id.edtPerdEmail);
        edtFoneContato= (EditText)findViewById(R.id.edtPerdTelefone);
        rbStatusAchado = (RadioButton)findViewById(R.id.rbStatusAchado);
        rbStatusPerdido = (RadioButton)findViewById(R.id.rbStatusPerdido);
        btnSubmeter = (Button)findViewById(R.id.btnReportPublicar);
        btnVoltar = (Button)findViewById(R.id.btnReportVoltar);

        btnSubmeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                achadosPerdidos = new AchadosPerdidos();
                achadosPerdidos.setDocumento(edtDoc.getText().toString());
                achadosPerdidos.setDescricao(edtDescri.getText().toString());
                achadosPerdidos.setLocalDoc(edtLocalDoc.getText().toString());
                achadosPerdidos.setEmailContato(edtEmailContato.getText().toString());
                achadosPerdidos.setFoneContato(edtFoneContato.getText().toString());
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                achadosPerdidos.setUid(currentUser.getUid());
                achadosPerdidos.setIdOcorrencia(UUID.randomUUID().toString());
                if (rbStatusAchado.isChecked()){
                    achadosPerdidos.setStatus("Achado");
                }
                if (rbStatusPerdido.isChecked()){
                    achadosPerdidos.setStatus("Perdido");
                }
                salvarreport(achadosPerdidos);
            }
        });

    }
    public boolean salvarreport(AchadosPerdidos achadosPerdidos){
        try{
            ref = ConexaoFirebase.getReferencia();
            ref.child("ocorrencias").child(achadosPerdidos.getIdOcorrencia()).setValue(achadosPerdidos);
            Toast.makeText(ReportarOcorrenciaActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
            limparTela();
            voltarInicial();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public void limparTela(){
        edtDoc.setText("");
        edtDescri.setText("");
        edtLocalDoc.setText("");
        edtEmailContato.setText("");
        edtFoneContato.setText("");
        rbStatusAchado.setChecked(false);
        rbStatusPerdido.setChecked(false);
    }
    public void voltarInicial(){
        Intent intent = new Intent(ReportarOcorrenciaActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

