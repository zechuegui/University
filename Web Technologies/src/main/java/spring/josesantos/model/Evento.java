package spring.josesantos.model;

import java.sql.Date;

public class Evento {

    // nomeEvento varchar(50) not null primary key, 
    // descricao varchar(500) not null,
    // preco TINYINT,
    // data date not null,

    private int id;
    private String nomeEvento;
    private String descricao;
    private int preco;
    private Date data;

    // new Date().toISOString().substring(0, 10);
    public Evento(int id, String nomeEvento, Date data, int preco, String descricao){
        this.setId(id);
        this.setNomeEvento(nomeEvento);
        this.setDescricao(descricao);
        this.setPreco(preco);
        this.setData(data);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
