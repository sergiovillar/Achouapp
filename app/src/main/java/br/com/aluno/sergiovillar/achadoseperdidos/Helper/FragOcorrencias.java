package br.com.aluno.sergiovillar.achadoseperdidos.Helper;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.aluno.sergiovillar.achadoseperdidos.Activity.EditarOcorrenciaActivity;
import br.com.aluno.sergiovillar.achadoseperdidos.Adapter.AchadosPerdidosAdapter;
import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class FragOcorrencias extends Fragment {

    private View view;
    private ArrayAdapter<AchadosPerdidos> arrayAdapterAchadosPerdidos;
    private Query query;
    private ValueEventListener valueEventListenerMinhasOcor;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private List<AchadosPerdidos> lista;

    public FragOcorrencias() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_minhasocorrencias, container, false);
        lista = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        arrayAdapterAchadosPerdidos = new AchadosPerdidosAdapter(getContext(), (ArrayList<AchadosPerdidos>) lista);
        query = ConexaoFirebase.getReferencia().child("ocorrencias").orderByChild("uid").equalTo(currentUser.getUid());
        valueEventListenerMinhasOcor = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lista.clear();
                for (DataSnapshot achadosSnapshot:dataSnapshot.getChildren()){
                    AchadosPerdidos ap = achadosSnapshot.getValue(AchadosPerdidos.class);
                    lista.add(ap);
                }
                arrayAdapterAchadosPerdidos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ListView listView = view.findViewById(R.id.lvFragOcor);
        listView.setAdapter(arrayAdapterAchadosPerdidos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final AchadosPerdidos arrayAdapter = arrayAdapterAchadosPerdidos.getItem(position);
                AlertDialog.Builder opcao = new AlertDialog.Builder(getActivity());

                opcao.setMessage("Deseja alterar essa ocorrência ?");
                opcao.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });
                opcao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle dados = new Bundle();
                        dados.putString("doc", arrayAdapter.getDocumento());
                        dados.putString("descri", arrayAdapter.getDescricao());
                        dados.putString("local", arrayAdapter.getLocalDoc());
                        dados.putString("email", arrayAdapter.getEmailContato());
                        dados.putString("fone", arrayAdapter.getFoneContato());
                        dados.putString("status", arrayAdapter.getStatus());
                        dados.putString("uid", arrayAdapter.getUid());
                        dados.putString("idocor", arrayAdapter.getIdOcorrencia());
                        Intent intent = new Intent(getActivity(), EditarOcorrenciaActivity.class);
                        intent.putExtras(dados);
                        startActivity(intent);


                        dialog.cancel();

                    }
                });
                opcao.create().show();

                //Toast.makeText(getActivity(),"Texto " + ocorrencia.getDocumento().toString(),Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        query.addValueEventListener(valueEventListenerMinhasOcor);
    }

    @Override
    public void onStop() {
        super.onStop();
        query.removeEventListener(valueEventListenerMinhasOcor);
    }

}
