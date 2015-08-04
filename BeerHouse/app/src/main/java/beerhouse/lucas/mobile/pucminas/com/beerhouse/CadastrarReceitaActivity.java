package beerhouse.lucas.mobile.pucminas.com.beerhouse;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.math.BigDecimal;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.bo.ReceitaBO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dominio.ValidacaoReceita;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dto.ReceitaDTO;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.util.MensagemUtil;


public class CadastrarReceitaActivity extends Activity {

    EditText edtNome;
    EditText edtAbv;
    RadioGroup radioGroupTipoFamilia;
    RadioButton radioAle;
    RadioButton radioLager;
    RadioButton radioHibrido;
    ReceitaBO receitaBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_receita);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();
        String valorAbv = params.getString("abv");

        edtAbv = (EditText) findViewById(R.id.edt_cadastrar_receita_abv);
        edtNome = (EditText) findViewById(R.id.edt_cadastrar_receita_nome);
        radioGroupTipoFamilia = (RadioGroup) findViewById(R.id.radiogroup_cadastrar_receita);
        radioAle = (RadioButton) findViewById(R.id.radio_cadastrar_receita_ale);
        radioLager = (RadioButton) findViewById(R.id.radio_cadastrar_receita_lager);
        radioHibrido = (RadioButton) findViewById(R.id.radio_cadastrar_receita_hibrido);
        receitaBO = new ReceitaBO(CadastrarReceitaActivity.this);

        // set campo com o parametro passado
        edtAbv.setText(valorAbv);
        edtAbv.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar_receita, menu);
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

    /**
     * Método que cadastra a receita no banco de dados atraves da classe de
     * negocio ReceitaBO.
     *
     * Apos o cadastro a activity eh finalizada.
     * @param view
     */
    public void cadastrarReceita(View view){

        String nome = edtNome.getText().toString();
        int checkedRadioButton = radioGroupTipoFamilia.getCheckedRadioButtonId();
        String msgObrigatoriedade = receitaBO.validarObrigatoriedadeCampos(nome, checkedRadioButton);
        if(msgObrigatoriedade == null){
            char radioSelecionado = 0;
            switch (checkedRadioButton) {
                case R.id.radio_cadastrar_receita_ale:
                    radioSelecionado = 'A';
                    break;
                case R.id.radio_cadastrar_receita_lager:
                    radioSelecionado = 'L';
                    break;
                case R.id.radio_cadastrar_receita_hibrido:
                    radioSelecionado = 'H';
                    break;
            }

            // add
            ReceitaDTO receitaDTO = new ReceitaDTO();
            receitaDTO.setNome(edtNome.getText().toString());
            receitaDTO.setValorABV(edtAbv.getText().toString());
            receitaDTO.setTipoFamilia(radioSelecionado);

            ValidacaoReceita resultado = receitaBO.castrarReceita(receitaDTO);
            MensagemUtil.addMsg(this, resultado.getMensagem());

            // finaliza activity
            onBackPressed();
        }
        else{
            MensagemUtil.addMsg(CadastrarReceitaActivity.this, msgObrigatoriedade);
        }
    }

}
