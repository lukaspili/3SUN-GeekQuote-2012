package com.supinfo.geekquote.task;

import java.net.URI;
import java.text.SimpleDateFormat;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.supinfo.geekquote.model.Quote;

public class PostQuoteTask extends AsyncTask<Quote, Void, Void> {
	
	private static final String URL = "http://geekquotelukas.appspot.com/resources/quotes";

	@Override
	protected Void doInBackground(Quote... params) {
		
		Quote quote = params[0];
		
		JSONObject jsonObject = new JSONObject();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		
		try {
			jsonObject.put("strQuote", quote.getStrQuote());
			jsonObject.put("rating", quote.getRating());
			jsonObject.put("date", dateFormat.format(quote.getDate()));
			
		} catch (Exception e) {
			Log.e(getClass().getName(), "Error during json parsing : " + e.getMessage(), e);
			return null;
		}
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost();

			URI uri = new URI(URL);
			httpPost.setURI(uri);
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(jsonObject.toString()));

			httpClient.execute(httpPost);
			
		} catch (Exception e) {
			Log.e(getClass().getName(), "Error during http request : " + e.getMessage(), e);
		}
		
		return null;
	}
	
	

}
