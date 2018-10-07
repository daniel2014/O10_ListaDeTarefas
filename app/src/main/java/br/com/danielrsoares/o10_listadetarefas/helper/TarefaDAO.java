package br.com.danielrsoares.o10_listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.danielrsoares.o10_listadetarefas.model.Tarefa;

/**
 * Criado por danielrsoares2006@gmail.com em 07 deOutubro de 2018
 **/

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase(); // Recupera o Objeto que nos permite salvar no banco de dados
        le = db.getReadableDatabase(); //Recupera o Objeto pque nos permite ler os dados de uma tabela
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNometarefa()); // nome do campo "", valor para o campo ""


        try{
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "Tarefa Salva com Sucesso!");
        }catch (Exception e){
            Log.i("INFO", "Erro ao Salvar Tarefa" + e.getMessage() );
            return false;

        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        return false;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        return false;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor cursor = le.rawQuery(sql, null);

        while (cursor.moveToNext()){

            Tarefa tarefa = new Tarefa();

            Long id = cursor.getLong(cursor.getColumnIndex("id") );
            String nomeTarefa = cursor.getString(cursor.getColumnIndex("nome") );

            tarefa.setId(id);
            tarefa.setNometarefa(nomeTarefa);

            tarefas.add(tarefa);

        }

        return tarefas;
    }
}
