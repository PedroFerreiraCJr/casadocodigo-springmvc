package br.com.dotofcodex.casadocodigo.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	private static final Logger logger = LoggerFactory.getLogger(FileSaver.class);

	@Autowired
	private HttpServletRequest request;

	public FileSaver() {
		super();
	}

	public String save(String base, MultipartFile multipart) {
		//String realPath = request.getServletContext().getRealPath("/".concat(base));
		String realPath = "/home/pedro/Downloads/cdc";
		logger.info("saving file...");
		
		try {
			String path = realPath.concat("/").concat(multipart.getOriginalFilename());
			multipart.transferTo(new File(path));
			logger.info(path);
			return base.concat(multipart.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
