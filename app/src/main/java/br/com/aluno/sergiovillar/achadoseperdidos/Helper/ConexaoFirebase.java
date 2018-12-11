package br.com.aluno.sergiovillar.achadoseperdidos.Helper;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class ConexaoFirebase {

    private static DatabaseReference referencia;
    private static FirebaseAuth autentica;

    public static DatabaseReference getReferencia() {
        if (referencia == null) {
            referencia = FirebaseDatabase.getInstance().getReference();
        }
        return referencia;
    }

    public static FirebaseAuth getInstancia() {
        if (autentica == null) {
            autentica = FirebaseAuth.getInstance();
        }
        return autentica;
    }
}
