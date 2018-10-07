package br.com.danielrsoares.o10_listadetarefas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.danielrsoares.o10_listadetarefas.R;
import br.com.danielrsoares.o10_listadetarefas.adapter.TarefaAdapter;
import br.com.danielrsoares.o10_listadetarefas.helper.RecyclerItemClickListener;
import br.com.danielrsoares.o10_listadetarefas.helper.TarefaDAO;
import br.com.danielrsoares.o10_listadetarefas.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listTarefas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurarar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        /*
        DbHelper db = new DbHelper(getApplicationContext());

        ContentValues cv = new ContentValues(); // Basicamente defini como se fosse um Array
        cv.put("nome", "Teste"); // Dentro da tabela tarefas será inserido esses valores

        db.getWritableDatabase().insert("tarefas", null, cv); //Salvar Escrita no Banco de Dados
        */


        //Configurar Evento de Clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                //Recuperar Tarefa para Edição
                                Tarefa tarefaSelecionada = listTarefas.get(position);

                                //Enviar Tarefa para Adicionar Tarefa
                                Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                                intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                                startActivity(intent);


                                 Snackbar.make(view, "Clicado Rápido - Posição: " + position, Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                 Snackbar.make(view, "Clique Longo - Posição: " + position, Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdicionarTarefaActivity.class));
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //    .setAction("Action", null).show();
            }
        });
    }

    public void carregarListaTarefas(){

        //Exibir Lista de Tarefas no RecyclerView

        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listTarefas = tarefaDAO.listar();



        //Adapter
        tarefaAdapter = new TarefaAdapter(listTarefas);

        //Configurar recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(tarefaAdapter);
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
