package beerhouse.lucas.mobile.pucminas.com.beerhouse.sqlite;

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

	public void cadastrar(ReceitaDTO receitaDTO) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("NOME", receitaDTO.getNome());
		values.put("VALOR_ABV", receitaDTO.getValorABV());
		values.put("IND_TIPO", String.valueOf(receitaDTO.getTipoFamilia()));

		db.insert("TB_RECEITA", null, values);
	}


}
