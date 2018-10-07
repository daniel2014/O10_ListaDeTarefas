package br.com.danielrsoares.o10_listadetarefas.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import br.com.danielrsoares.o10_listadetarefas.R;
import br.com.danielrsoares.o10_listadetarefas.helper.TarefaDAO;
import br.com.danielrsoares.o10_listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //Recuperar tarefa, caso seja edição do putExtra Serializable vindo de MainActivity
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar ou Exibir Tarefa na Caia de Texto
        if (tarefaAtual !=null){ //Se tiver algum texto é porque queremos editar
            editTarefa.setText(tarefaAtual.getNometarefa());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar:

                //Executa ação para o item salvar
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaAtual !=null){ // Irá Edição

                    Toast.makeText(this, "Preencha o Campo", Toast.LENGTH_SHORT).show();

                }else { //Irá Salvar

                    String nomeTarefa = editTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()){

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNometarefa(nomeTarefa);
                        tarefaDAO.salvar(tarefa);

                        if (tarefaDAO.salvar(tarefa)){
                            finish();
                            Toast.makeText(AdicionarTarefaActivity.this, "Sucesso ao salvar tarefa!",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "Erro ao Salvar Tarefa", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
