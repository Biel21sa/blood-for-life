package br.com.poo.bloodforlife.doacao;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistroDoacao implements Serializable {
    private LocalDate data;
    private String tipoSangue;
    private double quantidade;
    private String doador;

    public RegistroDoacao(LocalDate data, String tipoSangue, double quantidade, String doador) {
        this.data = data;
        this.tipoSangue = tipoSangue;
        this.quantidade = quantidade;
        this.doador = doador;
    }

    public LocalDate getData() {
        return data;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public double getQuantidade() {
        return quantidade;
    }
    public String getDoador() {
        return doador;
    }
}
