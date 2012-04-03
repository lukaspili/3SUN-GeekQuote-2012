package com.supinfo.geekquote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.supinfo.geekquote.adapter.QuoteAdapter;
import com.supinfo.geekquote.dao.DatabaseHelper;
import com.supinfo.geekquote.model.Quote;
import com.supinfo.geekquote.task.GetQuotesTask;
import com.supinfo.geekquote.task.PostQuoteTask;

public class QuoteListActivity extends Activity {

	private static final int QUOTE_ACTIVITY_REQUEST_CODE = 1;

	private Button button;

	private EditText editText;

	private ListView listView;

	private ArrayList<Quote> quotes;

	private QuoteAdapter quoteAdapter;

	private DatabaseHelper databaseHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		databaseHelper = new DatabaseHelper(this);

		if (savedInstanceState != null) {
			quotes = (ArrayList<Quote>) savedInstanceState
					.getSerializable("quotes");
		} else {
			quotes = new ArrayList<Quote>();
		}

		button = (Button) findViewById(R.id.button);
		editText = (EditText) findViewById(R.id.edit_text);
		listView = (ListView) findViewById(R.id.list);

		initButton();
		initList();
		
		new GetQuotesTask(this).execute();
	}

	private void initButton() {
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String strQuote = editText.getText().toString();
				editText.setText("");

				if (strQuote == null || strQuote.trim().isEmpty()) {
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

				Intent intent = new Intent(QuoteListActivity.this,
						QuoteActivity.class);
				intent.putExtra("quote", quote);
				intent.putExtra("quote_index", index);

				startActivityForResult(intent, QUOTE_ACTIVITY_REQUEST_CODE);
			}
		});

	}

	public void addQuote(String strQuote) {

		Quote quote = new Quote(strQuote, 0);

		new PostQuoteTask().execute(quote);

		Toast.makeText(this, "Quote ajoutée", 5000).show();
		
		new GetQuotesTask(this).execute();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == QUOTE_ACTIVITY_REQUEST_CODE) {

			if (resultCode == RESULT_OK) {

				Quote quote = (Quote) data.getExtras().getSerializable("quote");
				long index = data.getExtras().getLong("quote_index");

				quotes.set((int) index, quote);
				quoteAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putSerializable("quotes", quotes);
		super.onSaveInstanceState(outState);
	}
	
	public void onQuotesLoaded(List<Quote> loadedQuotes) {
		
		quotes.clear();
		quotes.addAll(loadedQuotes);
		quoteAdapter.notifyDataSetChanged();
		
		Toast.makeText(this, "Quotes loaded !", 2000).show();
	}
}
