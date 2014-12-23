package igorrmotta.com.shortcutexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import igorrmotta.com.shortcutexample.R;

public class MainActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			String color = bundle.getString("Color");
			int iColor = Color.parseColor(color);
			LinearLayout layout = (LinearLayout) findViewById(R.id.mainlayout);
			layout.setBackgroundColor(iColor);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void addRed_onClick(View view)
	{
		sendBroadcast(CreateShortcutIntent("#FF0000"));
	}

	public void addGreen_onClick(View view)
	{
		sendBroadcast(CreateShortcutIntent("#00FF00"));
	}

	public void addBlue_onClick(View view)
	{
		sendBroadcast(CreateShortcutIntent("#0000FF"));
	}

	private Intent CreateShortcutIntent(String color)
	{
		Intent shortcut = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");

		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, color);
		shortcut.putExtra("duplicate", true); //

		Intent x = new Intent(this, MainActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("Color", color);
		x.putExtras(bundle);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, x);
		return shortcut;
	}
}
