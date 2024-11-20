package com.example.minhaagenda;

public class Compromisso {

    private String data;
    private String hora;
    private String descricao;

    public Compromisso(String data, String hora, String descricao) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Compromisso{" +
                "data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}