package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class ReportarAchadoPerdidoActivity extends AppCompatActivity {

    private EditText edtNome, edtDescri, edtEnd, edtEmail, edtFone;
    private RadioButton rbAchado, rbPerdido;
    private Button btnSubmeter, btnVoltar;
    private AchadosPerdidos achadosPerdidos;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar_perdido);
        getSupportActionBar().setTitle("Registrar Objeto");
        edtNome= (EditText)findViewById(R.id.edtPerdNome);
        edtDescri= (EditText)findViewById(R.id.edtPerdDescri);
        edtEnd= (EditText)findViewById(R.id.edtPerdEndereco);
        edtEmail= (EditText)findViewById(R.id.edtPerdEmail);
        edtFone= (EditText)findViewById(R.id.edtPerdTelefone);
        rbAchado = (RadioButton)findViewById(R.id.rbAchado);
        rbPerdido = (RadioButton)findViewById(R.id.rbPerdido);
        btnSubmeter = (Button)findViewById(R.id.btnPublicar);
        btnVoltar = (Button)findViewById(R.id.btnReportVoltar);

        btnSubmeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                achadosPerdidos = new AchadosPerdidos();
                achadosPerdidos.setNome(edtNome.getText().toString());
                achadosPerdidos.setDescricao(edtDescri.getText().toString());
                achadosPerdidos.setEndereco(edtEnd.getText().toString());
                achadosPerdidos.setEmailcontato(edtEmail.getText().toString());
                achadosPerdidos.setTelefonecontato(edtFone.getText().toString());
                if (rbAchado.isChecked()){
                    achadosPerdidos.setTipo("Achado");
                }
                if (rbPerdido.isChecked()){
                    achadosPerdidos.setTipo("Perdido");
                }
                salvarreport(achadosPerdidos);

            }
        });

    }
    public boolean salvarreport(AchadosPerdidos achadosPerdidos){
        try{
            ref = ConexaoFirebase.getFirebase();
            ref.child("achados_perdidos").child(UUID.randomUUID().toString()).setValue(achadosPerdidos);
            Toast.makeText(ReportarAchadoPerdidoActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
            limparTela();
            voltarInicial();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public void limparTela(){
        edtNome.setText("");
        edtDescri.setText("");
        edtEnd.setText("");
        edtEmail.setText("");
        edtFone.setText("");
        rbAchado.setChecked(false);
        rbPerdido.setChecked(false);
    }
    public void voltarInicial(){
        Intent intent = new Intent(ReportarAchadoPerdidoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

