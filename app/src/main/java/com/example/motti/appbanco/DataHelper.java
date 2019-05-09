package com.example.motti.appbanco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    // Atributos

    private static final String DATABASE_NAME = "DB_AULA7_PDM.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TB_CLIENTE";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private SQLiteStatement deleteStmt;
    private SQLiteStatement uptadeStmt;
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(nom_cli) VALUES (?)";
//    private static final String DELETE = "DELETE FROM " + TABLE_NAME ;
//    private static final String UPDATE = "UPDATE " + TABLE_NAME;
    // Construtor da classe
    public DataHelper (Context context)
    {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
//        this.deleteStmt = this.db.compileStatement(DELETE);
//        this.uptadeStmt = this.db.compileStatement(UPDATE);
    }
    // Método de Insert no Banco de dados
    public long Insert(String name)
    {
        this.insertStmt.bindString(1, name);
        return this.insertStmt.executeInsert();
    }
    // Método para exclusão de todos os registros
    public long DeleteAll(Editable name)
    {
        this.db.delete(TABLE_NAME, null, null);
        return this.deleteStmt.executeUpdateDelete();
    }
    public String Uptade(String name){
        db.delete(TABLE_NAME, "NOMECAMPO" + "='" , null);
        return String.valueOf(this.uptadeStmt.executeUpdateDelete());
    }
    // Retorna lista com todos os itens cadastrados no banco de dados
    public List<String> SelectAll()
    {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[] { "cod_cli", "nom_cli" }, null,
                null, null, null, "cod_cli");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0) +" - " + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }
    // Classe responsável pelo gerenciamento e criação do banco de dados
    private static class OpenHelper extends SQLiteOpenHelper
    {
        OpenHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(cod_cli INTEGER PRIMARY KEY, nom_cli TEXT)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Example", "Upgrading database, this will drop tables and recreate.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
