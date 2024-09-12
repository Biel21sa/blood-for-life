package br.com.poo.bloodforlife.manipulacaoarquivo;


import br.com.poo.bloodforlife.doacao.Doador;

import java.io.*;
import java.util.ArrayList;

public class ControladorArquivoDoador {

    private final String ARQUIVO_DOADORES = "arquivoDoadores.ser";

    public void cadastrarDoadorNoArquivo(Doador doador){
        ArrayList<Doador> doadores = this.lerArquivoDoadores();
        doadores.add(doador);
        this.salvarArquivoDoadores(doadores);
    }

    public ArrayList<Doador> lerArquivoDoadores(){
        ArrayList<Doador> doadores = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DOADORES))) {
            doadores = (ArrayList<Doador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo.");
            this.salvarArquivoDoadores(doadores);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doadores;
    }

    private void salvarArquivoDoadores(ArrayList<Doador> doadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DOADORES))) {
            oos.writeObject(doadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
