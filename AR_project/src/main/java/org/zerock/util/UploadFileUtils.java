package org.zerock.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.AquariumBoardVO;
import org.zerock.domain.DrawBoardVO;
import org.zerock.domain.GeoBoardVO;
import org.zerock.domain.KuAquariumBoardVO;
import org.zerock.domain.KuGeoBoardVO;
import org.zerock.domain.KuSignBoardVO;
import org.zerock.domain.SignBoardVO;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	private static JSONArray jsonArray;
	private static final String servicePath = "upload";

	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		// UUID uid = UUID.randomUUID();
		// String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, originalName);
		// File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		if (FileUtils.getMediaType(formatName) != null) {
			// uploadedFileName = makeThumbnail(uploadPath, savedPath,
			// savedName);
			uploadedFileName = makeThumbnail(uploadPath, savedPath, originalName);
			System.out.println("=================dat xml===================");
			System.out.println(uploadedFileName);
			System.out.println("=================dat xml===================");

		} else {
			// uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
			uploadedFileName = makeIcon(uploadPath, savedPath, originalName);
			System.out.println("============makeIcon=====dat xml===================");
			System.out.println(uploadedFileName);
			System.out.println("===========makeIcon======dat xml===================");
		}
		return servicePath+uploadedFileName;

	}

	public static JSONArray uploadJsonImageFile(String uploadPath, MultipartFile... img) throws IOException, Exception {
		int i = 0;
		String[] imgArr = new String[img.length];

		for (MultipartFile imgfile : img) {
			imgArr[i] = uploadImageFile(uploadPath, imgfile.getOriginalFilename(), imgfile.getBytes());
			i++;
		}
		return JsonUtils.uploadJsonPicture(imgArr);
	}

	private static String uploadImageFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		if (FileUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);

		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;

	}

	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

		String iconName = uploadPath + path + File.separator + fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	// 썸네일 이미지 생성(이미지 파일)
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		// s_로 시작하면 썸네일 이미지이다.
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	public static String allUploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;

		if (FileUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		} else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;

	}

	public static void deleteFile(String uploadPath, String... files) {

		logger.info("delete all files: " + files);
		if (files == null || files.length == 0) {
		}
		for (String fileName : files) {
			if (fileName != null) {
				String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
				MediaType mType = FileUtils.getMediaType(formatName);
				if (mType != null) {
					String front = fileName.substring(0, 12);
					String end = fileName.substring(14);
					new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
				}
				new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
			}
		}
	}

	public static AquariumBoardVO modifyFile(AquariumBoardVO board, AquariumBoardVO modiVO, String uploadPath,
			MultipartFile... file) throws Exception {

		String modFileTpye;
		board.setXml(modiVO.getXml());
		board.setDat(modiVO.getDat());
		for (MultipartFile modFile : file) {

			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getFileType(modFileTpye) != null) {
				if (FileUtils.getFileType(modFileTpye).equals("XML")) {

					UploadFileUtils.deleteFile(uploadPath, modiVO.getXml());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setXml(modFileTpye);

				} else if (FileUtils.getFileType(modFileTpye).equals("DAT")) {
					UploadFileUtils.deleteFile(uploadPath, modiVO.getDat());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setDat(modFileTpye);
				}
			}
		}
		return board;
	}

	public static DrawBoardVO modifyFile(DrawBoardVO board, DrawBoardVO modiVO, String uploadPath, MultipartFile[] file,
			MultipartFile... img) throws Exception {

		String modFileTpye, modImgFileType;
		board.setXml(modiVO.getXml());
		board.setDat(modiVO.getDat());
		board.setPicture(modiVO.getPicture());
		for (MultipartFile modFile : file) {

			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getFileType(modFileTpye) != null) {
				if (FileUtils.getFileType(modFileTpye).equals("XML")) {

					UploadFileUtils.deleteFile(uploadPath, modiVO.getXml());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setXml(modFileTpye);

				} else if (FileUtils.getFileType(modFileTpye).equals("DAT")) {
					UploadFileUtils.deleteFile(uploadPath, modiVO.getDat());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setDat(modFileTpye);
				}
			} else if (FileUtils.getMediaType(modFileTpye) != null) {
				UploadFileUtils.deleteFile(uploadPath, modiVO.getPicture());
				modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(), modFile.getBytes());
				board.setPicture(modFileTpye);
			}
		}
		String[] imgArr = JsonUtils.jsonParserPicture(modiVO, img.length);
		String[] removeArr =JsonUtils.jsonParserPicture(modiVO, 0);
		int i = 0;

		for (MultipartFile modImgFile : img) {

			modImgFileType = modImgFile.getOriginalFilename()
					.substring(modImgFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getMediaType(modImgFileType) != null) {
				if (modImgFile.getOriginalFilename() != "") {
					UploadFileUtils.deleteFile(uploadPath, imgArr[i]);
					imgArr[i] = UploadFileUtils.uploadImageFile(uploadPath, modImgFile.getOriginalFilename(),
							modImgFile.getBytes());
				}
			}
			i++;
		}
		for(int j=img.length;j<removeArr.length;j++){
			System.out.println("img.length : "+img.length);
			UploadFileUtils.deleteFile(uploadPath, removeArr[j]);
		}
		jsonArray = JsonUtils.uploadJsonPicture(imgArr);
		board.setPicture(jsonArray.toJSONString());
		// 배열이 바뀐거 json으로 바꿔 주고 board에 set picture 해주자
		return board;
	}

	public static GeoBoardVO modifyFile(GeoBoardVO board, GeoBoardVO modiVO, String uploadPath, MultipartFile... file)
			throws Exception {

		String modFileTpye;
		board.setXml(modiVO.getXml());
		board.setDat(modiVO.getDat());
		for (MultipartFile modFile : file) {

			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getFileType(modFileTpye) != null) {
				if (FileUtils.getFileType(modFileTpye).equals("XML")) {

					UploadFileUtils.deleteFile(uploadPath, modiVO.getXml());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setXml(modFileTpye);

				} else if (FileUtils.getFileType(modFileTpye).equals("DAT")) {
					UploadFileUtils.deleteFile(uploadPath, modiVO.getDat());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setDat(modFileTpye);
				}
			}
		}
		return board;
	}

	public static SignBoardVO modifyFile(SignBoardVO board, SignBoardVO modiVO, String uploadPath, MultipartFile[] file,
			MultipartFile... img) throws Exception {

		String modFileTpye, modImgFileType;
		board.setXml(modiVO.getXml());
		board.setDat(modiVO.getDat());
		board.setPicture(modiVO.getPicture());
		for (MultipartFile modFile : file) {

			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getFileType(modFileTpye) != null) {
				if (FileUtils.getFileType(modFileTpye).equals("XML")) {

					UploadFileUtils.deleteFile(uploadPath, modiVO.getXml());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setXml(modFileTpye);

				} else if (FileUtils.getFileType(modFileTpye).equals("DAT")) {
					UploadFileUtils.deleteFile(uploadPath, modiVO.getDat());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setDat(modFileTpye);
				}
			} else if (FileUtils.getMediaType(modFileTpye) != null) {
				UploadFileUtils.deleteFile(uploadPath, modiVO.getPicture());
				modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(), modFile.getBytes());
				board.setPicture(modFileTpye);
			}
		}
		String[] imgArr = JsonUtils.jsonParserPicture(modiVO, img.length);
		String[] removeArr =JsonUtils.jsonParserPicture(modiVO, 0);
		int i = 0;

		for (MultipartFile modImgFile : img) {

			modImgFileType = modImgFile.getOriginalFilename()
					.substring(modImgFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getMediaType(modImgFileType) != null) {
				if (modImgFile.getOriginalFilename() != "") {
					UploadFileUtils.deleteFile(uploadPath, imgArr[i]);
					imgArr[i] = UploadFileUtils.uploadImageFile(uploadPath, modImgFile.getOriginalFilename(),
							modImgFile.getBytes());
				}
			}
			i++;
		}
		for(int j=img.length;j<removeArr.length;j++){
			System.out.println("img.length : "+img.length);
			UploadFileUtils.deleteFile(uploadPath, removeArr[j]);
		}
		jsonArray = JsonUtils.uploadJsonPicture(imgArr);
		board.setPicture(jsonArray.toJSONString());
		// 배열이 바뀐거 json으로 바꿔 주고 board에 set picture 해주자
		return board;
	}

	public static KuAquariumBoardVO modifyFile(KuAquariumBoardVO board, KuAquariumBoardVO modiVO, String uploadPath,
			MultipartFile... file) throws Exception {

		String modFileTpye;
		board.setKarmarker(modiVO.getKarmarker());
		for (MultipartFile modFile : file) {
			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);
			if (FileUtils.getFileType(modFileTpye) != null) {
				if (FileUtils.getFileType(modFileTpye).equals("KARMARKER")) {
					UploadFileUtils.deleteFile(uploadPath, modiVO.getKarmarker());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setKarmarker(modFileTpye);

				}
			}
		}
		return board;
	}

	public static KuGeoBoardVO modifyFile(KuGeoBoardVO board, KuGeoBoardVO modiVO, String uploadPath,
			MultipartFile... file) throws Exception {

		String modFileTpye;
		board.setKarmarker(modiVO.getKarmarker());

		for (MultipartFile modFile : file) {

			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getFileType(modFileTpye) != null) {
				if (FileUtils.getFileType(modFileTpye).equals("KARMARKER")) {

					UploadFileUtils.deleteFile(uploadPath, modiVO.getKarmarker());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setKarmarker(modFileTpye);

				}
			}
		}

		return board;
	}

	public static KuSignBoardVO modifyFile(KuSignBoardVO board, KuSignBoardVO modiVO, String uploadPath,
			MultipartFile[] file, MultipartFile... img) throws Exception {

		String modFileTpye, modImgFileType;
		board.setKarmarker(modiVO.getKarmarker());

		for (MultipartFile modFile : file) {

			modFileTpye = modFile.getOriginalFilename().substring(modFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getFileType(modFileTpye) != null) {

				if (FileUtils.getFileType(modFileTpye).equals("KARMARKER")) {

					UploadFileUtils.deleteFile(uploadPath, modiVO.getKarmarker());
					modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(),
							modFile.getBytes());
					board.setKarmarker(modFileTpye);

				}
			} else if (FileUtils.getMediaType(modFileTpye) != null) {

				UploadFileUtils.deleteFile(uploadPath, modiVO.getPicture());
				modFileTpye = UploadFileUtils.uploadFile(uploadPath, modFile.getOriginalFilename(), modFile.getBytes());
				board.setPicture(modFileTpye);
			}
		}
		String[] imgArr = JsonUtils.jsonParserPicture(modiVO, img.length);
		String[] removeArr =JsonUtils.jsonParserPicture(modiVO, 0);
		int i = 0;

		for (MultipartFile modImgFile : img) {

			modImgFileType = modImgFile.getOriginalFilename()
					.substring(modImgFile.getOriginalFilename().lastIndexOf(".") + 1);

			if (FileUtils.getMediaType(modImgFileType) != null) {
				if (modImgFile.getOriginalFilename() != "") {
					UploadFileUtils.deleteFile(uploadPath, imgArr[i]);
					imgArr[i] = UploadFileUtils.uploadImageFile(uploadPath, modImgFile.getOriginalFilename(),
							modImgFile.getBytes());
				}
			}
			i++;
		}
		for(int j=img.length;j<removeArr.length;j++){
			System.out.println("img.length : "+img.length);
			UploadFileUtils.deleteFile(uploadPath, removeArr[j]);
		}
		jsonArray = JsonUtils.uploadJsonPicture(imgArr);
		board.setPicture(jsonArray.toJSONString());
		// 배열이 바뀐거 json으로 바꿔 주고 board에 set picture 해주자
		return board;
	}

}
