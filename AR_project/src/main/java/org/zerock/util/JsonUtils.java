package org.zerock.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.PrinterMoreInfoManufacturer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.zerock.domain.DrawBoardVO;
import org.zerock.domain.GeoBoardVO;
import org.zerock.domain.KuGeoBoardVO;
import org.zerock.domain.KuSignBoardVO;
import org.zerock.domain.SignBoardVO;
import org.zerock.dto.GpsDTO;
import org.zerock.dto.PinmarkDTO;

public class JsonUtils {
	
	private static GpsDTO gpsDTO;
	private final static int distance_radius = 5000;// 반경 범위
	private static JSONArray jArray, pinArray;
	private static JSONObject jObject;
	private static JSONParser parser;
	private static Iterator it;
	private static List<PinmarkDTO> list;
	
	// 이미지 파일 json형식
	public static JSONArray uploadJsonPicture(String[] imgArr) {
		jArray = new JSONArray();
		for (int i = 0; i < imgArr.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("picName", imgArr[i]);

			jArray.add(obj);
		}
		return jArray;
	}
	
	/////////////////pinmark read 공통메소드///////////////////////////////////////////////
	private static List<PinmarkDTO> readPaserPinmark(JSONArray jArray) throws ParseException{
		parser = new JSONParser();
		list = new ArrayList<PinmarkDTO>();
		for(int i=0; i< jArray.size();i++){
			JSONObject object =(JSONObject)jArray.get(i);
			PinmarkDTO pinmarkDTO = new PinmarkDTO();
			pinmarkDTO.setpName(object.get("pName").toString());
			pinmarkDTO.setpLat(object.get("pLat").toString());
			pinmarkDTO.setpLon(object.get("pLon").toString());
			list.add(pinmarkDTO);
		}
		return list;
	}
	
	//readpage
	public static List<PinmarkDTO> jsonPaserPinmark(GeoBoardVO vo) throws ParseException{
		parser = new JSONParser();
		jArray = (JSONArray) parser.parse(vo.getPinmarker());
		return readPaserPinmark(jArray);
	}
	public static List<PinmarkDTO> jsonPaserPinmark(KuGeoBoardVO vo) throws ParseException{
		parser = new JSONParser();
		jArray = (JSONArray) parser.parse(vo.getPinmarker());
		return readPaserPinmark(jArray);
	}
	
	// pinmark json형식
	public static JSONArray registJsonPinmark(String[] pName, String[] pLat, String[] pLon) {
		jArray = new JSONArray();
		for (int i = 0; i < pName.length; i++) {
			JSONObject obj = new JSONObject();
			obj.put("pName", pName[i]);
			obj.put("pLat", Double.parseDouble(pLat[i]));
			obj.put("pLon", Double.parseDouble(pLon[i]));

			jArray.add(obj);
		}
		return jArray;
	}

	/////////////////pinmark 수정 공통메소드///////////////////////////////////////////////
	private static JSONArray modifyParserPinmark(JSONArray jsonArray,String[] pName, String[] pLat, String[] pLon){
		String[] modName = new String[pName.length];
		String[] modLat = new String[pName.length];
		String[] modLon = new String[pName.length];

		int arrLength = pName.length <= jsonArray.size() ? pName.length : jsonArray.size();
		for (int i = 0; i < arrLength; i++) {
			jObject = (JSONObject) jsonArray.get(i);
			modName[i] = (String) jObject.get("pName");
			modLat[i] = String.valueOf((double) jObject.get("pLat"));
			modLon[i] = String.valueOf((double) jObject.get("pLon"));
		}
		for (int j = 0; j < pName.length; j++) {
			if (!(pName[j].equals(""))) {
				modName[j] = pName[j];
				modLat[j] = pLat[j];
				modLon[j] = pLon[j];
			}
		}
		jArray = JsonUtils.registJsonPinmark(modName, modLat, modLon);
		return jArray;
	}
	// pinmark 수정
	public static JSONArray jsonModifyPinmark(GeoBoardVO modiVO, String[] pName, String[] pLat, String[] pLon)
			throws ParseException {

		parser = new JSONParser();
		jArray = (JSONArray) parser.parse(modiVO.getPinmarker());
		
		return modifyParserPinmark(jArray, pName, pLat, pLon);
	}

	public static JSONArray jsonModifyPinmark(KuGeoBoardVO modiVO, String[] pName, String[] pLat, String[] pLon)
			throws ParseException {

		parser = new JSONParser();
		jArray = (JSONArray) parser.parse(modiVO.getPinmarker());
		
		return modifyParserPinmark(jArray, pName, pLat, pLon);
	}

	/////////////////picture 수정 공통메소드///////////////////////////////////////////////
	private static String[] modifyPicture(JSONArray jArray, int fileSize) {
		if (fileSize == 0) {
			fileSize = jArray.size();
		}
		String[] imgArr = new String[fileSize];
		int arrLength = fileSize <= jArray.size() ? fileSize : jArray.size();
		for (int i = 0; i < arrLength; i++) {
			jObject = (JSONObject) jArray.get(i);

			imgArr[i] = (String) jObject.get("picName");
		}
		return imgArr;
	}

	// picture 수정
	public static String[] jsonParserPicture(DrawBoardVO modiVO, int fileSize) throws ParseException {
		parser = new JSONParser();
		System.out.println("============================================");
		System.out.println(modiVO.getPicture());
		jArray = (JSONArray) parser.parse(modiVO.getPicture());
		return modifyPicture(jArray, fileSize);
	}

	public static String[] jsonParserPicture(SignBoardVO modiVO, int fileSize) throws ParseException {
		parser = new JSONParser();
		jArray = (JSONArray) parser.parse(modiVO.getPicture());
		return modifyPicture(jArray, fileSize);
	}

	public static String[] jsonParserPicture(KuSignBoardVO modiVO, int fileSize) throws ParseException {
		parser = new JSONParser();
		jArray = (JSONArray) parser.parse(modiVO.getPicture());
		return modifyPicture(jArray, fileSize);
	}

	// Aquarium의 Data Set GPS_service json형식
	public static JSONArray getJsonDataSetFileList(List gpsList, String gpsPath, String mobile_lat,
			String mobile_long) {
		jArray = new JSONArray();
		parser = new JSONParser();
		it = gpsList.iterator();

		while (it.hasNext()) {
			gpsDTO = (GpsDTO) it.next();

			if (GpsUtils.distance1(Double.parseDouble(mobile_lat), Double.parseDouble(mobile_long),
					gpsDTO.getLatitude(), gpsDTO.getLongitude()) * 1000 <= distance_radius) {
				jObject = new JSONObject();
				jObject.put("Xml", gpsPath + gpsDTO.getXml());
				jObject.put("Dat", gpsPath + gpsDTO.getDat());
				jArray.add(jObject);
			}
		}
		return jArray;
	}

	// Draw & Sign 의 Picture && Data Set GPS_service json형식
	public static JSONArray getJsonPictureList(List gpsList, String gpsPath, String mobile_lat, String mobile_long)
			throws ParseException {
		jArray = new JSONArray();
		parser = new JSONParser();
		it = gpsList.iterator();

		while (it.hasNext()) {
			gpsDTO = (GpsDTO) it.next();

			if (GpsUtils.distance1(Double.parseDouble(mobile_lat), Double.parseDouble(mobile_long),
					gpsDTO.getLatitude(), gpsDTO.getLongitude()) * 1000 <= distance_radius) {
				jObject = new JSONObject();
				jObject.put("Xml", gpsPath + gpsDTO.getXml());
				jObject.put("Dat", gpsPath + gpsDTO.getDat());

				pinArray = (JSONArray) parser.parse(gpsDTO.getPicture());
				jObject.put("PinMarker", pinArray);
				jArray.add(jObject);
			}
		}
		return jArray;
	}

	// Geo GPS_service json형식
	public static JSONArray getJsonGeoList(List gpsList, String gpsPath, String mobile_lat, String mobile_long)
			throws ParseException {

		jArray = new JSONArray();
		parser = new JSONParser();
		it = gpsList.iterator();

		while (it.hasNext()) {
			gpsDTO = (GpsDTO) it.next();

			if (GpsUtils.distance1(Double.parseDouble(mobile_lat), Double.parseDouble(mobile_long),
					gpsDTO.getLatitude(), gpsDTO.getLongitude()) * 1000 <= distance_radius) {
				jObject = new JSONObject();
				jObject.put("Location_name", gpsDTO.getTitle());
				jObject.put("Latitude", gpsDTO.getLatitude());
				jObject.put("Longitude", gpsDTO.getLongitude());
				jObject.put("Xml", gpsPath + gpsDTO.getXml());
				jObject.put("Dat", gpsPath + gpsDTO.getDat());

				pinArray = (JSONArray) parser.parse(gpsDTO.getPinmarker());
				jObject.put("PinMarker", pinArray);
				jArray.add(jObject);
			}
		}
		return jArray;
	}

	// Kudan Aquarium의 Karmarker GPS_service json형식
	public static JSONArray getJsonKarmarkerList(List gpsList, String gpsPath, String mobile_lat, String mobile_long) {
		jArray = new JSONArray();
		parser = new JSONParser();
		it = gpsList.iterator();

		while (it.hasNext()) {
			gpsDTO = (GpsDTO) it.next();

			if (GpsUtils.distance1(Double.parseDouble(mobile_lat), Double.parseDouble(mobile_long),
					gpsDTO.getLatitude(), gpsDTO.getLongitude()) * 1000 <= distance_radius) {
				jObject = new JSONObject();
				jObject.put("karmarker", gpsPath + gpsDTO.getKarmarker());
				jArray.add(jObject);
			}
		}
		return jArray;
	}

	// Kudan Geo의 GPS_service json형식
	public static JSONArray getJsonKuGeoList(List gpsList, String gpsPath, String mobile_lat, String mobile_long)
			throws ParseException {
		jArray = new JSONArray();
		parser = new JSONParser();
		it = gpsList.iterator();

		while (it.hasNext()) {
			gpsDTO = (GpsDTO) it.next();

			if (GpsUtils.distance1(Double.parseDouble(mobile_lat), Double.parseDouble(mobile_long),
					gpsDTO.getLatitude(), gpsDTO.getLongitude()) * 1000 <= distance_radius) {
				jObject.put("Location_name", gpsDTO.getTitle());
				jObject.put("Latitude", gpsDTO.getLatitude());
				jObject.put("Longitude", gpsDTO.getLongitude());
				jObject.put("karmarker", gpsPath + gpsDTO.getKarmarker());

				pinArray = (JSONArray) parser.parse(gpsDTO.getPinmarker());
				jObject.put("PinMarker", pinArray);
				jArray.add(jObject);
			}
		}
		return jArray;
	}

	// Kudan sign의 GPS_service json형식
	public static JSONArray getJsonKuPictureList(List gpsList, String gpsPath, String mobile_lat, String mobile_long)
			throws ParseException {
		jArray = new JSONArray();
		parser = new JSONParser();
		it = gpsList.iterator();

		while (it.hasNext()) {
			gpsDTO = (GpsDTO) it.next();

			if (GpsUtils.distance1(Double.parseDouble(mobile_lat), Double.parseDouble(mobile_long),
					gpsDTO.getLatitude(), gpsDTO.getLongitude()) * 1000 <= distance_radius) {
				jObject = new JSONObject();
				jObject.put("karmarker", gpsPath + gpsDTO.getKarmarker());

				pinArray = (JSONArray) parser.parse(gpsDTO.getPicture());
				jObject.put("PinMarker", pinArray);
				jArray.add(jObject);
			}
		}
		return jArray;
	}

}
