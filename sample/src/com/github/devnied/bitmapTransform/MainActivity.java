package com.github.devnied.bitmapTransform;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView view = (ImageView) findViewById(R.id.imageView1);

		view.setImageBitmap(BitmapTransform.createBitmap(this,R.drawable.ic_launcher, R.drawable.sanstitre,
				PorterDuff.Mode.MULTIPLY, true, false));
	}

}
