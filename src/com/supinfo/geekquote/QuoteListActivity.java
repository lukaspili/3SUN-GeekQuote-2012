package com.supinfo.geekquote;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.supinfo.geekquote.model.Quote;

public class QuoteListActivity extends Activity {

	private ArrayList<Quote> quotes = new ArrayList<Quote>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String[] quotesArray = getResources().getStringArray(R.array.quotes);
        
        for(String strQuote : quotesArray) {
        	addQuote(strQuote);
        }
    }
    
    public void addQuote(String strQuote) {
    	quotes.add(new Quote(strQuote));
    	Toast.makeText(this, "Quote ajouté", 5000).show();
    }
}