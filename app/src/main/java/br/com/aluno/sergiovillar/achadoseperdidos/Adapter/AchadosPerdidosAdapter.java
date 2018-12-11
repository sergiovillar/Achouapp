package br.com.aluno.sergiovillar.achadoseperdidos.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.R;


public class AchadosPerdidosAdapter extends ArrayAdapter<AchadosPerdidos> {

    private ArrayList<AchadosPerdidos> perdidos;
    private Context context;
    public AchadosPerdidosAdapter(@NonNull Context context, ArrayList<AchadosPerdidos> objPerdidos) {
        super(context, 0, objPerdidos);
        this.context = context;
        this.perdidos = objPerdidos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (perdidos!=null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_achados_perdidos, parent, false);
            TextView txtNome = (TextView)view.findViewById(R.id.tvListaNome);
            TextView txtDescri = (TextView)view.findViewById(R.id.tvListaDescri);
            TextView txtEmail = (TextView)view.findViewById(R.id.tvListaEmail);
            TextView txtFone = (TextView)view.findViewById(R.id.tvListaTelefone);
            TextView txtEndereco = (TextView)view.findViewById(R.id.tvListaEndereco);
            TextView txtTipo = (TextView)view.findViewById(R.id.tvListaTipo);

            AchadosPerdidos perdido = perdidos.get(position);
            txtNome.setText("Objeto: "+(perdido.getDocumento()));
            txtDescri.setText("Descrição: "+(perdido.getDescricao()));
            txtEmail.setText("Email: "+(perdido.getEmailContato()));
            txtFone.setText("Telefone: "+(perdido.getFoneContato()));
            txtEndereco.setText("Local: "+(perdido.getLocalDoc()));
            txtTipo.setText("Situação: "+(perdido.getStatus()));

        }
        return view;
    }
}
