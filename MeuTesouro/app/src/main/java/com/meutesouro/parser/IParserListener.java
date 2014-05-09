package com.meutesouro.parser;

import java.util.List;

import com.meutesouro.entity.MoneyTitle;
import com.meutesouro.parser.HtmlParser.ErrorCode;

public interface IParserListener {
	public void infoReceived(List<MoneyTitle> moneyTitlesList);
	public void error(ErrorCode errorCode, String errorMessage);
}
