package beerhouse.lucas.mobile.pucminas.com.beerhouse;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.bo.ReceitaBO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dto.ReceitaDTO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.util.MensagemUtil;


public class ListarReceitaActivity extends Activity {

    private ReceitaBO receitaBO;
    private List<ReceitaDTO> receitaDTOList;
    private ListView lvReceitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_receita);

        receitaBO = new ReceitaBO(ListarReceitaActivity.this);
        listarReceitas();
    }

    private void listarReceitas(){

        //receitaBO.deletarTodos();
        receitaDTOList = receitaBO.listarPessoas();
        if(receitaDTOList.isEmpty()) {
            MensagemUtil.addMsgOk(ListarReceitaActivity.this, getString(R.string.lbl_principal_listar),getString(R.string.lbl_listar_receita_nenhuma_receita_encontrada), R.drawable.about);
        }else{
            lvReceitas = (ListView) findViewById(R.id.lst_receitas);

            List<CharSequence> valores = new ArrayList<>();
            for (ReceitaDTO receitaDTO : receitaDTOList) {
                valores.add(receitaDTO.getNome() + ": " + receitaDTO.getValorABV() + "%");
            }

            ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>
                    (this, android.R.layout.simple_list_item_1, valores);
            lvReceitas.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_receita, menu);
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
