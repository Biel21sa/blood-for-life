package br.com.poo.bloodforlife.manipulacaoarquivo;

import br.com.poo.bloodforlife.bancodesangue.BancoSangue;

import java.io.*;
import java.util.Map;

public class ControladorArquivoBancoSangue implements Serializable {

    private final String ARQUIVO_BANCO_SANGUE = "bancoSangue.ser";

    // Lê o estoque de sangue do arquivo
    public BancoSangue lerEstoqueBancoSangue() {
        BancoSangue bancoSangue = new BancoSangue();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_BANCO_SANGUE))) {
            Map<String, Integer> estoque = (Map<String, Integer>) ois.readObject();
            bancoSangue.setEstoqueSanguineo(estoque);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo.");
            salvarEstoqueBancoSangue(bancoSangue); // Cria um arquivo vazio se não existir
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bancoSangue;
    }

    // Salva o estoque de sangue no arquivo
    public void salvarEstoqueBancoSangue(BancoSangue bancoSangue) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_BANCO_SANGUE))) {
            oos.writeObject(bancoSangue.getEstoqueSanguineo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Consulta a quantidade disponível de um tipo sanguíneo
    public int consultarQuantidade(String tipo) {
        BancoSangue bancoSangue = lerEstoqueBancoSangue();
        return bancoSangue.consultarQuantidade(tipo);
    }

    // Adiciona quantidade ao tipo sanguíneo e atualiza o arquivo
    public void adicionarQuantidade(String tipo, int quantidade) {
        BancoSangue bancoSangue = lerEstoqueBancoSangue();
        bancoSangue.adicionarQuantidade(tipo, quantidade);
        salvarEstoqueBancoSangue(bancoSangue);
    }

    // Remove quantidade do tipo sanguíneo e atualiza o arquivo
    public void removerQuantidade(String tipo, int quantidade) {
        BancoSangue bancoSangue = lerEstoqueBancoSangue();
        bancoSangue.removerQuantidade(tipo, quantidade);
        salvarEstoqueBancoSangue(bancoSangue);
    }

    // Exibe todo o estoque de sangue
    public void exibirEstoque() {
        BancoSangue bancoSangue = lerEstoqueBancoSangue();
        bancoSangue.exibirEstoque();
    }
}
