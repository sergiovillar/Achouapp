package br.com.aluno.sergiovillar.achadoseperdidos.Helper;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class FragAchados extends Fragment {
    private View view;

    public FragAchados() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_achados, container, false);
        return view;
    }
}