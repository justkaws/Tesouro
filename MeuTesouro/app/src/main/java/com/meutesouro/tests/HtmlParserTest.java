package com.meutesouro.tests;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.meutesouro.entity.MoneyTitle;
import com.meutesouro.parser.HtmlParser;
import com.meutesouro.parser.HtmlParser.ErrorCode;
import com.meutesouro.parser.IParserListener;

public class HtmlParserTest extends TestCase {
	
	private HtmlParser parser;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		parser = HtmlParser.getInstance();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		parser = null;
	}

	@Test
	public void testParseWithoutListener() {
		IParserListener listener = null;
		parser.parse(listener);
		
	}
	
	@Test
	public void testParseWithListener() {
		IParserListener listener = new MockListener();
		parser.parse(listener);
	}


}

class MockListenerEmpty implements IParserListener{
    public ErrorCode error;
    public String msg;
	@Override
	public void infoReceived(List<MoneyTitle> moneyTitlesList) {
		
	}

	@Override
	public void error(ErrorCode errorCode, String errorMessage) {
		error = errorCode;
		msg = errorMessage;
	}
	
}

class MockListener implements IParserListener{

	@Override
	public void infoReceived(List<MoneyTitle> moneyTitlesList) {
		
	}

	@Override
	public void error(ErrorCode errorCode, String errorMessage) {
		
	}
}

