package com.example.simpleui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
	
    public static class PlaceholderFragment extends Fragment {

    	private CheckBox cbox;
    	private Button button;
    	private EditText editText;

    	//add @20140421
    	private CheckBox checkBox;
		private SharedPreferences sp;
		private SharedPreferences.Editor editor;
    	
        public PlaceholderFragment() {
        }
        
        private void send() {
			String mytext = editText.getText().toString();

			if (mytext != null && !mytext.equals("")) {
				if (cbox.isChecked()){
					mytext = "*****************";
				}
				Toast.makeText(getActivity(), mytext, Toast.LENGTH_SHORT).show();
			
				//write to file
				editor.putString("text", editText.getText().toString());
				editor.commit();
				
			}else{
				Toast.makeText(getActivity(), "Please Input!", Toast.LENGTH_SHORT).show();
			
			}
			editText.setText("");
        }

        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	        	
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            sp = getActivity()
					.getSharedPreferences("settings", Context.MODE_PRIVATE);
			editor = sp.edit();
			
            cbox = (CheckBox) rootView.findViewById(R.id.checkBox1);
            button = (Button) rootView.findViewById(R.id.button1);
            editText = (EditText) rootView.findViewById(R.id.editText1);
            
            editText.setOnKeyListener(new OnKeyListener() {
				
				@Override
				public boolean onKey(View v, int kcode, KeyEvent e) {
					if (e.getAction() == KeyEvent.ACTION_DOWN &&kcode==KeyEvent.KEYCODE_ENTER) {
						send();
						return true;
					} 
					return false;
				}
			});
            
            button.setText("������");
            
            button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					send();
				}
			});
            
            return rootView;
        }
    }

}
