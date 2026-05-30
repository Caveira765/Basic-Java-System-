package model;

public class Desenvolvedor implements Funcionario {

    private String nome;
    private double salarioBase;
    private double bonusProdutividade;

    public Desenvolvedor(String nome, double salarioBase, double bonusProdutividade) {
        setNome(nome);
        setSalarioBase(salarioBase);
        setBonusProdutividade(bonusProdutividade);
    }

    @Override
    public double calcularPagamento() {
        return salarioBase + bonusProdutividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("O salário base não pode ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    public double getBonusProdutividade() {
        return bonusProdutividade;
    }

    public void setBonusProdutividade(double bonusProdutividade) {
        if (bonusProdutividade < 0) {
            throw new IllegalArgumentException("O bônus não pode ser negativo.");
        }
        this.bonusProdutividade = bonusProdutividade;
    }

    // O toString garante que a impressão no console seja legível
    @Override
    public String toString() {
        return "Desenvolvedor | Nome: " + nome + " | Salário Base: R$ " + salarioBase + " | Bônus: R$ " + bonusProdutividade;
    }
}