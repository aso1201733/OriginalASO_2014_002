/**
 *
 */
package com.example.originalaso_2014_002;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author student
 *
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	public MySQLiteOpenHelper(Context context) {
		super(context, "20140021201733", null, 1);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("CREATE TABLE IF NOT EXISTS Hitokoto(_id INTEGER PRIMARY KEY "
				+ "AUTOINCREMENT NOT NULL,phrase TEXT);");

	}

	/*
	 * (非 Javadoc)
	 *
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL("drop table Hitokoto");
		onCreate(db);

	}

	public void insertHitokoto(SQLiteDatabase db, String inputMsg) {
		String sqlstr = "INSERT INTO Hitokoto(phrase) values('" + inputMsg
				+ "');";

		try {
			db.beginTransaction();
			db.execSQL(sqlstr);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("えらー1", e.toString());
		} finally {
			db.endTransaction();
		}
		return;
	}

	public void deleteHitokoto(SQLiteDatabase db, int id) {
		String sqlstr = "DELETE FROM Hitokoto WHERE _id = " + id + ";";

		try {
			db.beginTransaction();
			db.execSQL(sqlstr);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("でりーとえらー", e.toString());
		} finally {
			db.endTransaction();
		}
	}

	public String selectRamdomHitokoto(SQLiteDatabase db) {
		String rtString = null;

		String sqlstr = "SELECT _id, phrase FROM Hitokoto ORDER BY RANDOM();";

		try {
			SQLiteCursor cursor = (SQLiteCursor) db.rawQuery(sqlstr, null);
			if (cursor.getCount() != 0) {
				cursor.moveToFirst();
				rtString = cursor.getString(1);
			}
			cursor.close();
		} catch (SQLException e) {
			Log.e("えらー2", e.toString());
		} finally {

		}
		return rtString;
	}

	public SQLiteCursor selectHitokotoList(SQLiteDatabase db) {
		SQLiteCursor cursor = null;
		String sqlstr = "SELECT _id,phrase FROM Hitokoto ORDER BY _id;";
		try {
			cursor = (SQLiteCursor)db.rawQuery(sqlstr,null);

			if(cursor.getCount()!=0){
				cursor.moveToFirst();
			}

		} catch (SQLException e) {
			Log.e("カーソル取得えらー", e.toString());
		} finally {

		}

		return null;
	}

}
