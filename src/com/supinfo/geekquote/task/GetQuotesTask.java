package com.supinfo.geekquote.task;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.supinfo.geekquote.QuoteListActivity;
import com.supinfo.geekquote.model.Quote;

public class GetQuotesTask extends AsyncTask<Void, Void, List<Quote>>{
	
	private static final String URL = "http://geekquotelukas.appspot.com/resources/quotes";
	
	private QuoteListActivity activity;
	
	public GetQuotesTask(QuoteListActivity activity) {
		this.activity = activity;
	}
	
	@Override
	protected List<Quote> doInBackground(Void... params) {
		
		List<Quote> quotes = new ArrayList<Quote>();
		
		String res;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet();

			URI uri = new URI(URL);
			
			httpGet.setURI(uri);
			httpGet.setHeader("Accept", "application/json");

			HttpResponse httpResponse = httpClient.execute(httpGet);
			res = EntityUtils.toString(httpResponse.getEntity());
			
		} catch (Exception e) {
			Log.e(getClass().getName(), "Error during http request : " + e.getMessage(), e);
			return quotes;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		try {
			JSONObject json = new JSONObject(res);
			JSONArray array = json.getJSONArray("quote");
			
			for(int i = 0; i < array.length(); i++) {
				
				JSONObject object = array.getJSONObject(i);
				
				Quote quote = new Quote();
				quote.setId(object.getLong("id"));
				quote.setStrQuote(object.getString("strQuote"));
				quote.setRating(object.getInt("rating"));
				quote.setDate(dateFormat.parse(object.getString("date")));
				
				quotes.add(quote);
			}
			
		} catch (Exception e) {
			Log.e(getClass().getName(), "Error during json parsing : " + e.getMessage(), e);
		}

		
		return quotes;
	}
	
	@Override
	protected void onPostExecute(List<Quote> result) {
		activity.onQuotesLoaded(result);
	}

}
