package com.meutesouro.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.AsyncTask;

public class FetchHtmlTask extends AsyncTask<String, Integer, Document> {
	HtmlParser parser;
	
	public FetchHtmlTask(HtmlParser parser) {
		this.parser = parser;
	}

    protected Document doInBackground(String... url) {
        try {
        	Document htmlDocument = Jsoup.connect(url[0]).get();
        	return htmlDocument;
        } catch (Exception e) {
            return null;
        }
    }
    
    protected void onPostExecute(Document htmlDocument) {
		parser.setHtmlDocument(htmlDocument);
    }
}
