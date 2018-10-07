package br.com.danielrsoares.o10_listadetarefas.helper;

import java.util.List;

import br.com.danielrsoares.o10_listadetarefas.model.Tarefa;

/**
 * Criado por danielrsoares2006@gmail.com em 07 deOutubro de 2018
 **/

public interface ITarefaDAO {

    public boolean salvar(Tarefa tarefa);
    public boolean atualizar(Tarefa tarefa);
    public boolean deletar( Tarefa tarefa);
    public List<Tarefa> listar();
}
