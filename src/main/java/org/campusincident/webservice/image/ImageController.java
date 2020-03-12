package org.campusincident.webservice.image;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageController {	

	@Autowired ImageService servImage;
	
	@GetMapping("/images/{imageId}")
	public ResponseEntity<byte[]> serveFile(@PathVariable Long imageId) {
		Image img = this.servImage.get(imageId);
		return ResponseEntity.ok().contentType(MediaType.valueOf(img.getMimetype())).body(img.getPayload());
	}
	
	@PostMapping("/images")
	public ImageSaved handleFileUpload(@RequestBody MultipartFile file) throws IOException {
		Image img = servImage.store(file);
		ImageSaved res = new ImageSaved();
		res.setId(img.getId());
		return res;
	}
	
}
