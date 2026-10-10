package controller;

import model.*;
import java.util.HashMap;
import java.util.Map;

public class FuncionarioController {

    private int proximoId;
    private Map<Integer, Funcionario> funcionarios;

    public FuncionarioController() {
        this.funcionarios = new HashMap<>();
        this.proximoId = 1;
    }

    public void adicionarFuncionario(Funcionario f) {
        funcionarios.put(proximoId, f);
        proximoId++;
    }

    public Map<Integer, Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public Funcionario buscarPorId(int id) {
        return funcionarios.get(id);
    }

    public boolean removerFuncionario(int id) {
        if (!funcionarios.containsKey(id)) {
            return false;
        }

        funcionarios.remove(id);
        return true;
    }

    public boolean atualizarFreelancer(int id, double novoValorHora, int novasHoras) {
        Funcionario f = funcionarios.get(id);


        if (f == null) {
            return false;
        }

        if (f instanceof Freelancer freelancer) {
            freelancer.setValorHora(novoValorHora);
            freelancer.setHorasTrabalhadas(novasHoras);
            return true;
        }

        return false;
    }

    public boolean atualizarDesenvolvedor(int id, double novoSalarioBase, double novoBonus) {
        Funcionario f = funcionarios.get(id);

        if (f == null) {
            return false;
        }

        if (f instanceof Desenvolvedor desenvolvedor) {
            desenvolvedor.setSalarioBase(novoSalarioBase);
            desenvolvedor.setBonusProdutividade(novoBonus);
            return true;
        }

        return false;
    }
}