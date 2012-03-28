package com.supinfo.geekquote;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.supinfo.geekquote.adapter.QuoteAdapter;
import com.supinfo.geekquote.model.Quote;

public class QuoteListActivity extends Activity {
	
	private static final int QUOTE_ACTIVITY_REQUEST_CODE = 1;
		
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
    	
    	listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View row,
					int position, long index) {
				
				Quote quote = quoteAdapter.getItem(position);
				
				Intent intent = new Intent(QuoteListActivity.this, QuoteActivity.class);
				intent.putExtra("quote", quote);
				intent.putExtra("quote_index", index);
				
				startActivityForResult(intent, QUOTE_ACTIVITY_REQUEST_CODE);
			}
		});
    	
    }
    
    public void addQuote(String strQuote) {
    	
    	quotes.add(new Quote(strQuote));
    	quoteAdapter.notifyDataSetChanged();
    	
    	Toast.makeText(this, "Quote ajouté", 5000).show();
    }
}