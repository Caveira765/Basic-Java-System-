package model;

public class Freelancer implements Funcionario {

    private String nome;
    private double valorHora;
    private int horasTrabalhadas;

    public Freelancer(String nome, double valorHora, int horasTrabalhadas) {
        setNome(nome);
        setValorHora(valorHora);
        setHorasTrabalhadas(horasTrabalhadas);
    }

    @Override
    public double calcularPagamento() {
        return valorHora * horasTrabalhadas;
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

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        if (valorHora < 0) {
            throw new IllegalArgumentException("O valor da hora não pode ser negativo.");
        }
        this.valorHora = valorHora;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        if (horasTrabalhadas < 0) {
            throw new IllegalArgumentException("As horas trabalhadas não podem ser negativas.");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public String toString() {
        return "Freelancer | Nome: " + nome + " | Valor/Hora: R$ " + valorHora + " | Horas Trabalhadas: " + horasTrabalhadas;
    }
}