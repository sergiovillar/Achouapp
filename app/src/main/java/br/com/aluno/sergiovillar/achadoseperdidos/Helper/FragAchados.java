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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.com.aluno.sergiovillar.achadoseperdidos.Adapter.AchadosPerdidosAdapter;
import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class FragAchados extends Fragment {
    private View view;
    private ArrayAdapter<AchadosPerdidos> arrayAdapterAchadosPerdidos;
    private ValueEventListener valueEventListenerPerdidos;
    private Query query;

    public FragAchados() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_achados, container, false);
        final List<AchadosPerdidos> lista = new ArrayList<>();
        arrayAdapterAchadosPerdidos = new AchadosPerdidosAdapter(getContext(), (ArrayList<AchadosPerdidos>) lista);
        query = ConexaoFirebase.getReferencia().child("ocorrencias").orderByChild("status").equalTo("Achado");
        valueEventListenerPerdidos = new ValueEventListener() {
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

        ListView listView = view.findViewById(R.id.lvFragAchados);
        listView.setAdapter(arrayAdapterAchadosPerdidos);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        query.addValueEventListener(valueEventListenerPerdidos);
    }

    @Override
    public void onStop() {
        super.onStop();
        query.removeEventListener(valueEventListenerPerdidos);
    }
}
