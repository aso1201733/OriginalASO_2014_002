package com.example.originalaso_2014_002;

//import jp.ac.st.asojuku.originalaso_2014_001.R;
import android.util.Log;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class MainActivity extends Activity implements View.OnClickListener {
	public SQLiteDatabase sdb = null;
	public MySQLiteOpenHelper helper = null;

	public void onClick(View v) {
		Intent intent = null;

		switch (v.getId()) {
		case R.id.checkButton:
			Log.d("いべんと発生！", "でばっぐ");
			intent = new Intent(MainActivity.this, HitokotoActivity.class);

			intent.putExtra("hitokoto", "おくるあたい");
			startActivity(intent);
			break;

		case R.id.maintenanceButton:
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onResume() {
		// TODO 閾ｪ蜍慕函謌舌＆繧後◆繝｡繧ｽ繝�ラ繝ｻ繧ｹ繧ｿ繝�
		super.onResume();
		Button maintenanceButton = (Button) findViewById(R.id.maintenanceButton);
		maintenanceButton.setOnClickListener(this);

		Button checkButton = (Button) findViewById(R.id.checkButton);
		checkButton.setOnClickListener(this);

		Button ragistrationButton = (Button) findViewById(R.id.ragistrationButton);
		ragistrationButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
