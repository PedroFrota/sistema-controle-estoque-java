package test;

import model.Produto;

public class EstoqueTest {

    public static void main(String[] args) {
        testarEntradaEstoque();
        testarSaidaEstoqueValida();
        testarSaidaMaiorQueSaldo();
        testarAlertaEstoqueCritico();
        System.out.println("Todos os testes unitários passaram com sucesso!");
    }

    private static void testarEntradaEstoque() {
        Produto p = new Produto("7891000100103", "Arroz 5kg", "Mercearia", 24.90, 50, 20);
        p.registrarEntrada(30);
        if (p.getQuantidadeEstoque() != 80) {
            throw new AssertionError("Falha no teste de entrada: esperado 80, obtido " + p.getQuantidadeEstoque());
        }
    }

    private static void testarSaidaEstoqueValida() {
        Produto p = new Produto("7891000200202", "Feijão 1kg", "Mercearia", 7.49, 40, 15);
        p.registrarSaida(15);
        if (p.getQuantidadeEstoque() != 25) {
            throw new AssertionError("Falha no teste de saída: esperado 25, obtido " + p.getQuantidadeEstoque());
        }
    }

    private static void testarSaidaMaiorQueSaldo() {
        Produto p = new Produto("7891000300301", "Óleo 900ml", "Mercearia", 6.29, 10, 15);
        try {
            p.registrarSaida(20);
            throw new AssertionError("Falha: deveria lançar exceção ao tentar retirar mais que o saldo.");
        } catch (IllegalStateException e) {
            // Comportamento esperado
        }
    }

    private static void testarAlertaEstoqueCritico() {
        Produto p = new Produto("7891000500509", "Café 500g", "Matinais", 18.50, 12, 15);
        if (!p.isEstoqueCritico()) {
            throw new AssertionError("Falha: produto com 12 unidades e mínimo 15 deveria estar crítico.");
        }
    }
}
