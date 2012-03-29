package com.supinfo.geekquote;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.supinfo.geekquote.dao.DatabaseHelper;
import com.supinfo.geekquote.model.Quote;

public class QuoteActivity extends Activity {

	private TextView strQuoteTextView;

	private TextView dateTextView;

	private RatingBar ratingBar;

	private Button buttonCancel;

	private Button buttonOk;

	private Quote quote;

	private Dialog dialog;

	private EditText dialogEditText;

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
				quote.setStrQuote(strQuoteTextView.getText().toString());

				ContentValues contentValues = new ContentValues();
				contentValues.put("strquote", quote.getStrQuote());
				contentValues.put("rating", quote.getRating());

				SQLiteDatabase db = new DatabaseHelper(QuoteActivity.this).getWritableDatabase();
				String[] args = { "asd" };
				db.update("quotes", contentValues, "id=?", args);
				db.close();

				getIntent().putExtra("quote", quote);

				setResult(RESULT_OK, getIntent());
				finish();
			}
		});

		strQuoteTextView.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				showDialog(1);
				return false;
			}
		});

		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog);

		dialogEditText = (EditText) dialog.findViewById(R.id.dialog_edit);

		Button dialogButtonCancel = (Button) dialog
				.findViewById(R.id.dialog_button_cancel);
		dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		Button dialogButtonOk = (Button) dialog
				.findViewById(R.id.dialog_button_ok);
		dialogButtonOk.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				strQuoteTextView.setText(dialogEditText.getText().toString());
				dialog.dismiss();
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		dialogEditText.setText(quote.getStrQuote());
		return dialog;
	}
}