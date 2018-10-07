package br.com.danielrsoares.o10_listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1; //Versão do Banco de Dados
    public static  String NOME_DB = "DB_TAREFAS"; //Nome do Banco de Dados
    public static String TABELA_TAREFAS = "tarefas"; // Nome da Tabela


    //Context = Passar o Contexto
    // String = Passar o Nome para o Banco de Dados
    // CursorFactory = Define Cursores
    // Version = Versão Criada do Bando de Dados
    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override //Cria nova tabela APENAS NA PRIMEIRA INSTALAÇÃO DO APP
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS //Espaçamentos nesses trecho "" são importante
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL); "; // NULL -  Nós não queremos que o nome seja nulo

        try {

            db.execSQL( sql );
            Log.i("INFO", "Sucesso ao Criar a Tabela!" );

        }catch (Exception e){
            Log.i("INFO", "Erro ao criar a tabela" + e.getMessage() );
        }
    }

    @Override //Atualizando App
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //NULL -  Nós não queremos que o nome seja nulo
        String sql = "DROP TABLE IF EXISTS " + TABELA_TAREFAS  + " ;";

        try {

            db.execSQL( sql );
            onCreate(db);
            Log.i("INFO", "Sucesso ao Atualizar Tabela!!" );

        }catch (Exception e){
            Log.i("INFO", "Erro ao criar a tabela na Atualização" + e.getMessage() );
        }

    }
}
