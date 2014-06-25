package com.example.originalaso_2014_002;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MaintenanceActivity extends Activity implements
		AdapterView.OnItemClickListener, View.OnClickListener {

	SQLiteDatabase sdb = null;

	int selectedID = -1;
	int lastPosition = -1;

	MySQLiteOpenHelper helper = null;

	@Override
	protected void onResume() {
		super.onResume();

		Button btnDelete = (Button) findViewById(R.id.deleteButton);
		Button btnMainte_Back = (Button) findViewById(R.id.backButton);
		ListView listHitokoto = (ListView) findViewById(R.id.hitokotoList);

		btnDelete.setOnClickListener(this);
		btnMainte_Back.setOnClickListener(this);

		listHitokoto.setOnItemClickListener(this);

		this.setDBValuetoList(listHitokoto);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintenance_activity);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.deleteButton:
			if (this.selectedID != -1) {
				this.deleteFromHitokoto(this.selectedID);
				ListView hitokotoList = (ListView) findViewById(R.id.hitokotoList);
				this.setDBValuetoList(hitokotoList);

				this.selectedID = -1;
				this.lastPosition = -1;
			} else {
				Toast.makeText(MaintenanceActivity.this, "削除する行を選んでください",
						Toast.LENGTH_SHORT).show();
				Log.d("とーすとのところ", "bbb");
			}
			break;

		case R.id.backButton:
			finish();
			break;
		}
	}

	private void deleteFromHitokoto(int id) {
		if (sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}
		try {
			sdb = helper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.e("デリーとえらー", e.toString());
		}

		this.helper.deleteHitokoto(sdb, id);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (this.selectedID != -1) {
			parent.getChildAt(this.lastPosition).setBackgroundColor(0);
		}
		view.setBackgroundColor(android.graphics.Color.LTGRAY);

		SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
		this.selectedID = cursor.getInt(cursor.getColumnIndex("_id"));

		this.lastPosition = position;

	}

	private void setDBValuetoList(ListView listHitokoto) {

		SQLiteCursor cursor = null;

		if (sdb == null) {
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}

		try {
			sdb = helper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.e("取得エラー", e.toString());
		}

		cursor = this.helper.selectHitokotoList(sdb);
		int db_layout = android.R.layout.simple_list_item_activated_1;
		String[] from = { "phrase" };
		int[] to = new int[] { android.R.id.text1 };

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, db_layout,
				cursor, from, to, 0);
		Log.d("はしってる", "aaa");

		listHitokoto.setAdapter(adapter);
	}

}
