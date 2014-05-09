package com.meutesouro.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

public class XmlUtils {
	
	private	String xmlFileName;
	private FileInputStream fileInput;
	private Context xmlContext;
	
	public String getXmlFileName() {
		return xmlFileName;
	}

	public void setXmlFileName(String m_xmlFileName) {
		xmlFileName = m_xmlFileName;
	}

	public FileInputStream getFileInput() {
		return fileInput;
	}

	public void setFileInput(FileInputStream m_fileInput) {
		fileInput = m_fileInput;
	}
	
	public XmlUtils( Context m_context ){
		xmlContext = m_context;
	}

	
	public void XmlSaveData(String m_fileName, Object m_object){
		
		FileOutputStream fileOutput = null;
		setXmlFileName(m_fileName);
		
		try
		{
			fileOutput = xmlContext.openFileOutput(xmlFileName, Context.MODE_APPEND);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(this);
			objectOutput.close();
		} catch( Exception e) {
		}
		finally
		{
			try {
				fileOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Object XmlLoadData(String m_FileName){
		FileInputStream fileInput = null;
		Object object = null;
		try {
			fileInput = xmlContext.openFileInput(m_FileName);
			ObjectInputStream inputStream = new ObjectInputStream(fileInput);
			object = (Object)inputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return object;
	}
}

