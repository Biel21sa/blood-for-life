package br.com.poo.bloodforlife.doacao;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistroDoacao implements Serializable {
    private LocalDate data;
    private String tipoSangue;
    private double quantidade;
    private Doador doador;

    public RegistroDoacao(LocalDate data, String tipoSangue, double quantidade, Doador doador) {
        this.data = data;
        this.tipoSangue = tipoSangue;
        this.quantidade = quantidade;
        this.doador = doador;
    }
}
