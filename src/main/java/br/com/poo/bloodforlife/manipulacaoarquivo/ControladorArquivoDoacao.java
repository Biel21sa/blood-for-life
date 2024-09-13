package br.com.poo.bloodforlife.manipulacaoarquivo;

import br.com.poo.bloodforlife.doacao.RegistroDoacao;

import java.io.*;
import java.util.ArrayList;

public class ControladorArquivoDoacao {

    private final String ARQUIVO_DOACOES = "arquivoDoacoes.ser";

    public void cadastrarDoacaoNoArquivo(RegistroDoacao doacao){
        ArrayList<RegistroDoacao> doacoes = this.lerArquivoDoacoes();
        doacoes.add(doacao);
        this.salvarArquivoDoacoes(doacoes);
    }

    public ArrayList<RegistroDoacao> lerArquivoDoacoes(){
        ArrayList<RegistroDoacao> doacoes = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_DOACOES))) {
            doacoes = (ArrayList<RegistroDoacao>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo.");
            this.salvarArquivoDoacoes(doacoes);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return doacoes;
    }

    private void salvarArquivoDoacoes(ArrayList<RegistroDoacao> doacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DOACOES))) {
            oos.writeObject(doacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
