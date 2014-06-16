package com.example.originalaso_2014_002;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HitokotoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自動生成されたメソッド・スタブ
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hitokoto_activity);

		Intent intent = getIntent();
		String hitokoto = intent.getStringExtra("hitokoto");
		TextView tv = (TextView)findViewById(R.id.hitokotoDisplay);

		tv.setText(hitokoto);
	}

}