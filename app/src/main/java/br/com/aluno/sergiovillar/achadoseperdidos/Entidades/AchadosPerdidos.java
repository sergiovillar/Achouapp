package br.com.aluno.sergiovillar.achadoseperdidos.Entidades;

public class AchadosPerdidos {
    String idOcorrencia, documento, descricao, localDoc, emailContato, foneContato, status, uid;

    public AchadosPerdidos() {
    }

    public AchadosPerdidos(String idOcorrencia, String documento, String descricao, String localDoc, String emailContato, String foneContato, String status, String uid) {
        this.idOcorrencia = idOcorrencia;
        this.documento = documento;
        this.descricao = descricao;
        this.localDoc = localDoc;
        this.emailContato = emailContato;
        this.foneContato = foneContato;
        this.status = status;
        this.uid = uid;
    }

    public String getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(String idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalDoc() {
        return localDoc;
    }

    public void setLocalDoc(String localDoc) {
        this.localDoc = localDoc;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getFoneContato() {
        return foneContato;
    }

    public void setFoneContato(String foneContato) {
        this.foneContato = foneContato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
