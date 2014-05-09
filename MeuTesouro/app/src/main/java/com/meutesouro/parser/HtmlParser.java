package com.meutesouro.parser;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.annotation.SuppressLint;
import android.util.Log;
import com.meutesouro.entity.MoneyTitle;
import com.meutesouro.entity.TitleTax;

public class HtmlParser {
	public enum ErrorCode {
		NoInternetConnectionError
	}
	
	private static String TAG = "HtmlParser";
	private static String URL = "http://www3.tesouro.gov.br/tesouro_direto/consulta_titulos_novosite/consultatitulos.asp";
	private static HtmlParser instance = null;
	
	private Document htmlDocument;
	private IParserListener listener;
	
	private FetchHtmlTask task;
	
	private HtmlParser() {
		
	}
	
	public static HtmlParser getInstance() {
		if (instance == null)
			instance = new HtmlParser();
		
		return instance;
	}
	
	public FetchHtmlTask getFetchHtmlTask() {
		return task;
	}
	
	public void setHtmlDocument(Document htmlDocument) {
		this.htmlDocument = htmlDocument;
		if (htmlDocument != null)
			PopulateMoneyTitleList();
		else if (listener != null)
			listener.error(ErrorCode.NoInternetConnectionError, "Connection failed.");
	}
	
	public void parse(IParserListener listener) {
		this.listener = listener;
		new FetchHtmlTask(this).execute(URL);
	}
	
	@SuppressLint("SimpleDateFormat")
	public void PopulateMoneyTitleList() {
		ArrayList<MoneyTitle> moneyTitleList = new ArrayList<MoneyTitle>();
		
		int i = 4;
		Elements lineItems = htmlDocument.select("tr:nth-child(" + i + ") td");
		while (lineItems.size() > 0)
		{
			if (lineItems.size() > 1)
			{
				Log.d(TAG, "Line: " + i);
				
				MoneyTitle moneyTitle = new MoneyTitle();
				moneyTitle.setName(lineItems.get(0).text().trim());
				
				String dateAsString = lineItems.get(1).text().trim();
				Date date = null;
				try {
					SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
					date = dateFormatter.parse(dateAsString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				moneyTitle.setExpiredDate(date);
				
				TitleTax anualTax = new TitleTax();
				
				String taxesBuying = lineItems.get(2).text().trim();
				anualTax.setTaxesBuying(convertTax(taxesBuying));
				
				String taxesSelling = lineItems.get(3).text().trim();
				anualTax.setTaxesSelling(convertTax(taxesSelling));
				
				moneyTitle.setAnualTitleTax(anualTax);
				
				TitleTax currentTax = new TitleTax();
				
				String currentTaxesBuying = lineItems.get(4).text().trim();
				currentTax.setTaxesBuying(convertTax(currentTaxesBuying));
				
				String currentTaxesSelling = lineItems.get(5).text().trim();
				currentTax.setTaxesSelling(convertTax(currentTaxesSelling));
				
				moneyTitle.setCurrentTitleTax(currentTax);
				
				moneyTitleList.add(moneyTitle);
			}
			
			i++;
			lineItems = htmlDocument.select("tr:nth-child(" + i + ") td");
		}
		
		Log.d(TAG, "Finished parsing.");
		
		if (listener != null)
			listener.infoReceived(moneyTitleList);
		
		task = null;
	}
	
	public double convertTax(String taxAsString)
	{
		String auxTax = taxAsString;
		auxTax = auxTax.replaceFirst("%", "");
		auxTax = auxTax.replaceFirst("R", "");
		auxTax = auxTax.replaceFirst("\\$", "");
		auxTax = auxTax.replaceFirst("\\.", "");
		auxTax = auxTax.replaceFirst(",", ".");
		double tax;
		try
		{
			tax = Double.parseDouble(auxTax);
		}
		catch (Exception e)
		{
			tax = 0;
		}
		
		return tax;
	}
}
