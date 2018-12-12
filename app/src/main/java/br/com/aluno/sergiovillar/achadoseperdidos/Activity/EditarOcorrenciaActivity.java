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

import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class EditarOcorrenciaActivity extends AppCompatActivity {

    private EditText edtDoc, edtDescri, edtLocalDoc, edtEmailContato, edtFoneContato;
    private RadioButton rbAltAchado, rbAltPerdido, rbAltRecuperado;
    private Button btnSalvar, btnCancelar;
    private AchadosPerdidos achadosPerdidos;
    private DatabaseReference ref;
    private AchadosPerdidos ocorrencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ocorrencia);
        edtDoc = findViewById(R.id.edtAltDoc);
        edtDescri = findViewById(R.id.edtAltDescri);
        edtLocalDoc = findViewById(R.id.edtAltEndereco);
        edtEmailContato = findViewById(R.id.edtAltEmail);
        edtFoneContato = findViewById(R.id.edtAltTelefone);
        rbAltAchado = findViewById(R.id.rbAltAchado);
        rbAltPerdido = findViewById(R.id.rbAltPerdido);
        rbAltRecuperado = findViewById(R.id.rbAltRecuperado);
        btnCancelar = findViewById(R.id.btnAltVoltar);
        btnSalvar = findViewById(R.id.btnAltSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarDados();
                salvarOcorrencia(ocorrencia);
                Intent intent = new Intent(EditarOcorrenciaActivity.this, PrincipalOcorrencias.class);
                startActivity(intent);
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        ocorrencia = new AchadosPerdidos();
        ocorrencia.setDocumento(bundle.getString("doc"));
        ocorrencia.setDescricao(bundle.getString("descri"));
        ocorrencia.setLocalDoc(bundle.getString("local"));
        ocorrencia.setEmailContato(bundle.getString("email"));
        ocorrencia.setFoneContato(bundle.getString("fone"));
        ocorrencia.setStatus(bundle.getString("status"));
        ocorrencia.setUid(bundle.getString("uid"));
        ocorrencia.setIdOcorrencia(bundle.getString("idocor"));

        preencherDados(ocorrencia);

    }

    public void preencherDados(AchadosPerdidos ocorrencia){
        this.edtDoc.setText(ocorrencia.getDocumento());
        this.edtDescri.setText(ocorrencia.getDescricao());
        this.edtLocalDoc.setText(ocorrencia.getLocalDoc());
        this.edtEmailContato.setText(ocorrencia.getEmailContato());
        this.edtFoneContato.setText(ocorrencia.getFoneContato());
        if (ocorrencia.getStatus().equals("Achado")){
            this.rbAltAchado.setChecked(true);
        }else if (ocorrencia.getStatus().equals("Perdido")){
            this.rbAltPerdido.setChecked(true);
        }else if (ocorrencia.getStatus().equals("Recuperado")){
            this.rbAltRecuperado.setChecked(true);
        }

    }

    public AchadosPerdidos recuperarDados(){
        ocorrencia.setIdOcorrencia(ocorrencia.getIdOcorrencia());
        ocorrencia.setUid(ocorrencia.getUid());
        ocorrencia.setDocumento(edtDoc.getText().toString());
        ocorrencia.setDescricao(edtDescri.getText().toString());
        ocorrencia.setLocalDoc(edtLocalDoc.getText().toString());
        ocorrencia.setEmailContato(edtEmailContato.getText().toString());
        ocorrencia.setFoneContato(edtFoneContato.getText().toString());
        if (rbAltAchado.isChecked()){
            ocorrencia.setStatus("Achado");
        }else if (rbAltPerdido.isChecked()){
            ocorrencia.setStatus("Perdido");
        }else if (rbAltRecuperado.isChecked()){
            ocorrencia.setStatus("Recuperado");
        }

        return ocorrencia;
    }

    public boolean salvarOcorrencia(AchadosPerdidos ocorrencia){
        try{
            ref = ConexaoFirebase.getReferencia();
            ref.child("ocorrencias").child(ocorrencia.getIdOcorrencia()).setValue(ocorrencia);
            Toast.makeText(EditarOcorrenciaActivity.this, "Sucesso", Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
