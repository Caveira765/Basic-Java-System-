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

    // Ajustado o nome para o singular, mantendo consistência com a View
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

        // Se a busca retornar null, o funcionário não existe
        if (f == null) {
            return false;
        }

        // CORREÇÃO: Testando 'f' e não o Map 'funcionarios'
        if (f instanceof Freelancer freelancer) {
            freelancer.setValorHora(novoValorHora);
            freelancer.setHorasTrabalhadas(novasHoras);
            return true;
        }

        return false;
    }

    // CORREÇÃO: Nomes de parâmetros mais claros e condizentes com a modelagem
    public boolean atualizarDesenvolvedor(int id, double novoSalarioBase, double novoBonus) {
        Funcionario f = funcionarios.get(id);

        if (f == null) {
            return false;
        }

        // CORREÇÃO: Testando 'f' e não o Map 'funcionarios'
        if (f instanceof Desenvolvedor desenvolvedor) {
            desenvolvedor.setSalarioBase(novoSalarioBase);
            desenvolvedor.setBonusProdutividade(novoBonus);
            return true;
        }

        return false;
    }
}