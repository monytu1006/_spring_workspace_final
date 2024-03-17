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

import com.myfinal.www.domain.cmFileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;


@Slf4j
@Component
public class cmFileHandler {
	
	private final String UP_DIR = "D:\\moon\\_myProject\\_java\\_fileUpload";
	
	public List<cmFileVO> uploadFiles(MultipartFile[] files){
		List<cmFileVO> flist = new ArrayList<>();
		LocalDate date = LocalDate.now();
		
		String today = date.toString();
		
		today = today.replace("-", File.separator);
		log.info(">>>>> today : " + today);
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		for(MultipartFile file : files ) {
			cmFileVO cmfvo = new cmFileVO();
			
			cmfvo.setCmSaveDir(today);
			cmfvo.setCmFileSize(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			
			cmfvo.setCmFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			log.info(">>> uuid>> {}", uuid.toString());
			String uuidStr = uuid.toString();
			cmfvo.setCmUuid(uuidStr);		

			String fullFileName = uuidStr + "_" + fileName;
			File storeFile = new File(folders, fullFileName);

			try {
				file.transferTo(storeFile);
				

				if(isImageFile(storeFile)) {
					cmfvo.setCmFileType(1);

					File thumbNail = new File(folders, uuidStr+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75,75).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.info("파일 생성 오류");
			}

			flist.add(cmfvo);
		}
		return flist;
	}
	
	public boolean isImageFile(File storeFile) throws IOException{
		String mimeType = new Tika().detect(storeFile);
		
		return mimeType.startsWith("image") ? true:false;
	}
}









