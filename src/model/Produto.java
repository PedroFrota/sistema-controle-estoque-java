package model;

public class Produto {
    private int id;
    private String codigoBarras;
    private String nome;
    private String categoria;
    private double precoUnitario;
    private int quantidadeEstoque;
    private int estoqueMinimo;

    public Produto() {}

    public Produto(String codigoBarras, String nome, String categoria, double precoUnitario, int quantidadeEstoque, int estoqueMinimo) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.categoria = categoria;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.estoqueMinimo = estoqueMinimo;
    }

    public void registrarEntrada(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de entrada deve ser maior que zero.");
        }
        this.quantidadeEstoque += quantidade;
    }

    public void registrarSaida(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de saída deve ser maior que zero.");
        }
        if (quantidade > this.quantidadeEstoque) {
            throw new IllegalStateException("Saldo insuficiente em estoque para realizar a saída.");
        }
        this.quantidadeEstoque -= quantidade;
    }

    public boolean isEstoqueCritico() {
        return this.quantidadeEstoque <= this.estoqueMinimo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) { this.precoUnitario = precoUnitario; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public int getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
}
