package br.com.aluno.sergiovillar.achadoseperdidos.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.aluno.sergiovillar.achadoseperdidos.Entidades.AchadosPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.Helper.FragPerdidos;
import br.com.aluno.sergiovillar.achadoseperdidos.R;

public class AdapterListaOcorrencias extends BaseAdapter {
    private Context context;
    private List<AchadosPerdidos> listaAchadosPerdidos;

    public AdapterListaOcorrencias(FragPerdidos fragPerdidos, List<AchadosPerdidos> listaPerdidos) {
    }

    public AdapterListaOcorrencias(Context context, List<AchadosPerdidos> listaAchadosPerdidos) {
        this.context = context;
        this.listaAchadosPerdidos = listaAchadosPerdidos;
    }

    @Override
    public int getCount() {
        return this.listaAchadosPerdidos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listaAchadosPerdidos.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(this.context, R.layout.lista_achados_perdidos, null);
        TextView tvListaNome = view.findViewById(R.id.tvListaNome);
        TextView tvListaDescri = view.findViewById(R.id.tvListaDescri);
        TextView tvListaEndereco = view.findViewById(R.id.tvListaEndereco);
        TextView tvListaEmail = view.findViewById(R.id.tvListaEmail);
        TextView tvListaTelefone = view.findViewById(R.id.tvListaEmail);
        TextView tvListaTipo = view.findViewById(R.id.tvListaTipo);

        tvListaNome.setText(this.listaAchadosPerdidos.get(position).getDocumento());
        tvListaDescri.setText(this.listaAchadosPerdidos.get(position).getDescricao());
        tvListaEndereco.setText(this.listaAchadosPerdidos.get(position).getLocalDoc());
        tvListaEmail.setText(this.listaAchadosPerdidos.get(position).getEmailContato());
        tvListaTelefone.setText(this.listaAchadosPerdidos.get(position).getFoneContato());
        tvListaTipo.setText(this.listaAchadosPerdidos.get(position).getStatus());


        return view;
    }
}
