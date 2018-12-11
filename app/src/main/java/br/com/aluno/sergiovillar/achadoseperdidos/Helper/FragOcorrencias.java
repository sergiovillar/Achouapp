package br.com.aluno.sergiovillar.achadoseperdidos.Helper;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class FragOcorrencias extends Fragment {

    private View view;
    private ArrayAdapter<AchadosPerdidos> arrayAdapterAchadosPerdidos;
    private DatabaseReference ref;
    private ValueEventListener valueEventListenerAchados;


    public FragOcorrencias() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_minhasocorrencias, container, false);
        final List<AchadosPerdidos> lista = new ArrayList<>();
        arrayAdapterAchadosPerdidos = new AchadosPerdidosAdapter(getContext(), (ArrayList<AchadosPerdidos>) lista);
        ref = ConexaoFirebase.getReferencia();
        ref.child("ocorrencias").orderByChild("uid").equalTo("hAxUAYHPbnSblbRQQc8EL9NKxvK2");
        valueEventListenerAchados = new ValueEventListener() {
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
        //view = inflater.inflate(R.layout.frag_minhasocorrencias, container, false);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        ref.addValueEventListener(valueEventListenerAchados);
    }

    @Override
    public void onStop() {
        super.onStop();
        ref.removeEventListener(valueEventListenerAchados);
    }
}
