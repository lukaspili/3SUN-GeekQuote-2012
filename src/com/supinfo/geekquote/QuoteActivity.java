package com.supinfo.geekquote;

import com.supinfo.geekquote.model.Quote;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class QuoteActivity extends Activity {
	
	private TextView strQuoteTextView;
	
	private TextView dateTextView;
	
	private RatingBar ratingBar;
	
	private Button buttonCancel;
	
	private Button buttonOk;
	
	private Quote quote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quote_activity);
		
		strQuoteTextView = (TextView) findViewById(R.id.quote_activity_text);
		dateTextView = (TextView) findViewById(R.id.quote_activity_date);
		ratingBar = (RatingBar) findViewById(R.id.quote_activity_rating);
		buttonCancel = (Button) findViewById(R.id.quote_activity_button_cancel);
		buttonOk = (Button) findViewById(R.id.quote_activity_button_ok);
		
		quote = (Quote) getIntent().getExtras().getSerializable("quote");
		strQuoteTextView.setText(quote.getStrQuote());
		dateTextView.setText(quote.getDate().toString());
		ratingBar.setRating(quote.getRating());
		
		buttonCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				setResult(RESULT_CANCELED);
				finish();
			}
		});
		
		buttonOk.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				quote.setRating((int) ratingBar.getRating());
				getIntent().putExtra("quote", quote);
				
				setResult(RESULT_OK, getIntent());
				finish();
			}
		});
	}
	
	
	
	
	
	
	

}
