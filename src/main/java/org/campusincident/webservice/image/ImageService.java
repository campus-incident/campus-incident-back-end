package org.campusincident.webservice.image;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.campusincident.webservice.exceptions.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	
	private @Autowired ImageRepository repoImage;

	public Image store(MultipartFile file) throws IOException {
		Image img = new Image();
		img.setMimetype(file.getContentType());
		img.setPayload(file.getBytes());
		return this.repoImage.save(img);
	}
	
	public Image store(Resource file, String mimetype) throws IOException {
		byte[] bytes = IOUtils.toByteArray(file.getInputStream());
		Image img = new Image();
		img.setMimetype(mimetype);
		img.setPayload(bytes);
		return this.repoImage.save(img);
	}
	
	/**
	 * @throws ImageNotFoundException when image with given id not found
	 */
	public Image get(Long id) {
		return this.repoImage.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
	}
	
}
