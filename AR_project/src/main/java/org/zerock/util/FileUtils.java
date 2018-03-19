package org.zerock.util;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlType;

import org.springframework.http.MediaType;

public class FileUtils {
	
	private static Map<String, MediaType> mediaMap;
	private static Map<String, String> fileMap;
	
	static{
		
		mediaMap = new HashMap<String, MediaType>();		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
		
		fileMap = new HashMap<String, String>();	
		fileMap.put("JPG", "JPG");
		fileMap.put("PNG", "PNG");
		fileMap.put("XML", "XML");
		fileMap.put("DAT", "DAT");
		fileMap.put("KARMARKER", "KARMARKER");
		fileMap.put("TE", "Te");

		
	}
	
	public static MediaType getMediaType(String type){
		
		return mediaMap.get(type.toUpperCase());
	}
	
	public static String getFileType(String type){
		return fileMap.get(type.toUpperCase());
	}
	public static void main(String args[]){
		System.out.println(getMediaType("jpg"));
	}
}



