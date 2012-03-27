package com.supinfo.geekquote;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.supinfo.geekquote.adapter.QuoteAdapter;
import com.supinfo.geekquote.model.Quote;

public class QuoteListActivity extends Activity {
		
	private Button button;
	
	private EditText editText;
	
	private ListView listView;
    
	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	
	private QuoteAdapter quoteAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edit_text);
        listView = (ListView) findViewById(R.id.list);
        
        initButton();
        initList();
    }
    
    private void initButton() {
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
    }
    
    private void initList() {
    	quoteAdapter = new QuoteAdapter(this, quotes);
    	listView.setAdapter(quoteAdapter);
    }
    
    public void addQuote(String strQuote) {
    	
    	quotes.add(new Quote(strQuote));
    	quoteAdapter.notifyDataSetChanged();
    	
    	Toast.makeText(this, "Quote ajouté", 5000).show();
    }
}