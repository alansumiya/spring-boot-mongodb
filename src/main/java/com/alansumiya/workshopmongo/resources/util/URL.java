package com.alansumiya.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	
	public static String decodeParam(String text) {
		//vai tentar decodifica para o formato UTF-8, se não conseguir retorna vazio
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	 public static Date converDate(String textDate, Date defaultValue) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		 try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultValue;
		}
	 }
}
