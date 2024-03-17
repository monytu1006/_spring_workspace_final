package com.myfinal.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myfinal.www.domain.nmFileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;


@Slf4j
@Component
public class nmFileHandler {
	
	private final String UP_DIR = "D:\\moon\\_myProject\\_java\\_fileUpload";
	
	public List<nmFileVO> uploadFiles(MultipartFile[] files){
		List<nmFileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		
		String today = date.toString();
		
		today = today.replace("-", File.separator);
		log.info(">>>>> today : " + today);

		File folders = new File(UP_DIR, today);
		

		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		for(MultipartFile file : files ) {
			nmFileVO nmfvo = new nmFileVO();
			
			nmfvo.setNmSaveDir(today);
			nmfvo.setNmFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			
			nmfvo.setNmFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			log.info(">>> uuid>> {}", uuid.toString());
			String uuidStr = uuid.toString();
			nmfvo.setNmUuid(uuidStr);		
			
			String fullFileName = uuidStr + "_" + fileName;
			File storeFile = new File(folders, fullFileName);
			try {
				file.transferTo(storeFile);
				
				if(isImageFile(storeFile)) {
					nmfvo.setNmFileType(1);

					File thumbNail = new File(folders, uuidStr+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75,75).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 생성 오류");
			}
			flist.add(nmfvo);
		}
		
		return flist;
	}
	
	public boolean isImageFile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile);
		
		return mimeType.startsWith("image") ? true:false;
	}
}









