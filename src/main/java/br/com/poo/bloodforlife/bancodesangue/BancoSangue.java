package br.com.poo.bloodforlife.bancodesangue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BancoSangue implements Serializable {

    private Map<String, Integer> estoqueSanguineo;

    public BancoSangue() {
        estoqueSanguineo = new HashMap<>();
        inicializarTiposSanguineos();
    }

    private void inicializarTiposSanguineos() {
        estoqueSanguineo.put("A+", 0);
        estoqueSanguineo.put("A-", 0);
        estoqueSanguineo.put("B+", 0);
        estoqueSanguineo.put("B-", 0);
        estoqueSanguineo.put("AB+", 0);
        estoqueSanguineo.put("AB-", 0);
        estoqueSanguineo.put("O+", 0);
        estoqueSanguineo.put("O-", 0);
    }

    public void adicionarQuantidade(String tipo, int quantidade) {
        if (estoqueSanguineo.containsKey(tipo)) {
            estoqueSanguineo.put(tipo, estoqueSanguineo.get(tipo) + quantidade);
        } else {
            System.out.println("Tipo sanguíneo não encontrado.");
        }
    }

    public void removerQuantidade(String tipo, int quantidade) {
        if (estoqueSanguineo.containsKey(tipo)) {
            int novaQuantidade = estoqueSanguineo.get(tipo) - quantidade;
            if (novaQuantidade >= 0) {
                estoqueSanguineo.put(tipo, novaQuantidade);
            } else {
                System.out.println("Quantidade insuficiente para remover.");
            }
        } else {
            System.out.println("Tipo sanguíneo não encontrado.");
        }
    }

    public int consultarQuantidade(String tipo) {
        return estoqueSanguineo.getOrDefault(tipo, -1);
    }

    public void exibirEstoque() {
        for (Map.Entry<String, Integer> entry : estoqueSanguineo.entrySet()) {
            System.out.println("Tipo: " + entry.getKey() + ", Quantidade: " + entry.getValue());
        }
    }

    public Map<String, Integer> getEstoqueSanguineo() {
        return estoqueSanguineo;
    }

    public void setEstoqueSanguineo(Map<String, Integer> estoqueSanguineo) {
        this.estoqueSanguineo = estoqueSanguineo;
    }
}