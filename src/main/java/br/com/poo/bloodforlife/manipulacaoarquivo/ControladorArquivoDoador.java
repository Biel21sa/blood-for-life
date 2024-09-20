package br.com.poo.bloodforlife.manipulacaoarquivo;


import br.com.poo.bloodforlife.doacao.Doador;

import java.io.*;
import java.util.ArrayList;

public class ControladorArquivoDoador {

    private final String ARQUIVO_DOADORES = "arquivoDoadores.ser";

    public void cadastrarDoadorNoArquivo(Doador doador) {
        ArrayList<Doador> doadores = this.lerArquivoDoadores();
        doadores.add(doador);
        this.salvarArquivoDoadores(doadores);
    }

    public ArrayList<Doador> lerArquivoDoadores() {
        ArrayList<Doador> doadores = new ArrayList<>();
        File arquivo = new File(ARQUIVO_DOADORES);

        if (!arquivo.exists()) {
            System.out.println("Arquivo de doadores não encontrado. Criando novo arquivo.");
            this.salvarArquivoDoadores(doadores);
            return doadores;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DOADORES))) {
            doadores = (ArrayList<Doador>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo.");
            this.salvarArquivoDoadores(doadores);
        } catch (IOException e) {
            System.err.println("Erro de leitura do arquivo de doadores: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Erro de classe ao ler o arquivo de doadores: " + e.getMessage());
            e.printStackTrace();
        }

        return doadores;
    }

    private void salvarArquivoDoadores(ArrayList<Doador> doadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DOADORES))) {
            oos.writeObject(doadores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar doadores no arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
