package br.com.aluno.sergiovillar.achadoseperdidos.Entidades;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import br.com.aluno.sergiovillar.achadoseperdidos.Helper.ConexaoFirebase;

public class Usuarios {
    private String email, senha, nome, id;
    private FirebaseAuth mAuth;

    public Usuarios() {
    }

    public Usuarios(String email, String senha, String nome, String id) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.id = id;
    }
    public void salvar(){
        DatabaseReference ref = ConexaoFirebase.getReferencia();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        ref.child("usuario").child(currentUser.getUid()).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap <String, Object> hashMapUsuario = new HashMap<>();
        hashMapUsuario.put("id", getId());
        hashMapUsuario.put("nome", getNome());
        hashMapUsuario.put("senha", getSenha());
        hashMapUsuario.put("email", getEmail());

        return hashMapUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
