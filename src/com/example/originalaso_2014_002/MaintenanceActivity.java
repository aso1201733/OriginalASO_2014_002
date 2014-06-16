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


public class MaintenanceActivity extends Activity implements
AdapterView.OnItemClickListener,View.OnClickListener {

	SQLiteDatabase sdb = null;

	int selectedID = -1;
	int lastPosition = -1;

	MySQLiteOpenHelper helper = null;


	@Override
	protected void onResume(){
		super.onResume();

		Button btnDelete = (Button)findViewById(R.id.deleteButton);
		Button btnMainte_Back = (Button)findViewById(R.id.backButton);
		ListView listHitokoto = (ListView)findViewById(R.id.hitokotoList);

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

	public void onClick(View v){

	}

	private void setDBValuetoList(ListView listHitokoto){

		SQLiteCursor cursor = null;

		if(sdb == null){
			helper = new MySQLiteOpenHelper(getApplicationContext());
		}

		onItemClick(AdapterView ab, View bcv, int cd, long de){

		}

		try{
			sdb = helper.getWritableDatabase();
		}catch(SQLiteException e){
			Log.e("éÊìæÉGÉâÅ[",e.toString());
		}

		cursor = this.helper.selectHitokotoList(sdb);
		int db_layout = android.R.layout.simple_list_item_activated_1;
		String[]from = {"phrase"};
		int[] to = new int[]{android.R.id.text1};

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,db_layout,cursor,from,to,0);

		listHitokoto.setAdapter(adapter);
	}
}
