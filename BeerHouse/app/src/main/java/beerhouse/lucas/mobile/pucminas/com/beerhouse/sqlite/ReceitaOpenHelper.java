package beerhouse.lucas.mobile.pucminas.com.beerhouse.sqlite;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import beerhouse.lucas.mobile.pucminas.com.beerhouse.comuns.Constantes;
import beerhouse.lucas.mobile.pucminas.com.beerhouse.dto.ReceitaDTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReceitaOpenHelper extends SQLiteOpenHelper {

	public ReceitaOpenHelper(Context context) {
		super(context, Constantes.DB_CONFIG_NOME, null, Integer.parseInt(Constantes.DB_CONFIG_VERSAO));
	}

	/**
	 * Cria o a tabela Receita caso não exista.
	 * @param db
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE TB_RECEITA (");
		sql.append(" ID_RECEITA INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append(" NOME TEXT NOT NULL,");
		sql.append(" IND_TIPO CHAR NOT NULL,");
		sql.append(" VALOR_ABV TEXT NOT NULL)");
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	/**
	 * Insere a receita no banco de dados.
	 * @param receitaDTO
	 */
	public void cadastrar(ReceitaDTO receitaDTO) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("NOME", receitaDTO.getNome());
		values.put("VALOR_ABV", receitaDTO.getValorABV());
		values.put("IND_TIPO", String.valueOf(receitaDTO.getTipoFamilia()));

		db.insert("TB_RECEITA", null, values);
	}

	/**
	 * @return retorna as receitas cadastrada no banco de dados.
	 */
	public List<ReceitaDTO> listar() {
		List<ReceitaDTO> lista = new ArrayList<>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(true, "TB_RECEITA", null, null, null, null, null, "ID_RECEITA", null, null);
		while (cursor.moveToNext()) {
			ReceitaDTO receitaDTO = new ReceitaDTO();
			receitaDTO.setId(cursor.getInt(cursor.getColumnIndex("ID_RECEITA")));
			receitaDTO.setNome(cursor.getString(cursor.getColumnIndex("NOME")));
			receitaDTO.setValorABV(cursor.getString(cursor.getColumnIndex("VALOR_ABV")));
			receitaDTO.setTipoFamilia(cursor.getString(cursor.getColumnIndex("IND_TIPO")).charAt(0));

			lista.add(receitaDTO);
		}

		return lista;
	}

	public void deletarTodos() {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete("TB_RECEITA", null, null);
	}


}
