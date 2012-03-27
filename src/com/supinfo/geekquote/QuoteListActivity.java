package com.supinfo.geekquote;

import java.util.ArrayList;

import com.supinfo.geekquote.model.Quote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuoteListActivity extends Activity {
	
	private LinearLayout linearLayout;
	
	private Button button;
	
	private EditText editText;
    
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        linearLayout = (LinearLayout) findViewById(R.id.vertical_layout);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String strQuote = editText.getText().toString();
				editText.setText("");
				
				if(strQuote == null || strQuote.trim().isEmpty()) {
					return;
				}
				
				addQuote(strQuote);
			}
		});
        
        String[] quotesArray = getResources().getStringArray(R.array.quotes);
        
        for(String strQuote : quotesArray) {
        	addQuote(strQuote);
        }
    }
    
    public void addQuote(String strQuote) {
    	
    	quotes.add(new Quote(strQuote));
    	
    	TextView textView = new TextView(this);
    	textView.setText(strQuote);
    	
    	if(quotes.size() % 2 != 0) {
    		textView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    	}
    	 
    	linearLayout.addView(textView);
    	
    	Toast.makeText(this, "Quote ajouté", 5000).show();
    }
}