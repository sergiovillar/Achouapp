package br.com.aluno.sergiovillar.achadoseperdidos.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.aluno.sergiovillar.achadoseperdidos.Adapter.AchadosPerdidosAdapter;
import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class ListaOcorrenciasActivity extends AppCompatActivity {


    private DatabaseReference ref;
    private ListView listaV;
    private List<AchadosPerdidos> listAchadosPerdidos;
    private ArrayAdapter<AchadosPerdidos> arrayAdapterAchadosPerdidos;
    private ValueEventListener valueEventListenerAchados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_achados);
        listarDados();

    }

    public void listarDados() {
        listAchadosPerdidos = new ArrayList<AchadosPerdidos>();
        listaV = (ListView)findViewById(R.id.lvAchadosPerdidos);
        arrayAdapterAchadosPerdidos = new AchadosPerdidosAdapter(this, (ArrayList<AchadosPerdidos>) listAchadosPerdidos);
        listaV.setAdapter(arrayAdapterAchadosPerdidos);
        ref = ConexaoFirebase.getReferencia().child("ocorrencias");
        valueEventListenerAchados = new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listAchadosPerdidos.clear();
                for (DataSnapshot achadosSnapshot:dataSnapshot.getChildren()){
                    AchadosPerdidos ap = achadosSnapshot.getValue(AchadosPerdidos.class);
                    listAchadosPerdidos.add(ap);
                }
                arrayAdapterAchadosPerdidos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(valueEventListenerAchados);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ref.removeEventListener(valueEventListenerAchados);
    }
}
