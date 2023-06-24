//package pl.nlo.jira.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import pl.nlo.jira.entity.ImageEntity;
//import pl.nlo.jira.repository.ImageDataRepository;
//
//import javax.transaction.Transactional;
//import java.io.IOException;
//import java.util.Optional;
//
//@Service
//public class ImageDataService {
//
//    @Autowired
//    private ImageDataRepository imageDataRepository;
//
//    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {
//
//        imageDataRepository.save(ImageData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(ImageUtil.compressImage(file.getBytes())).build());
//
//        return new ImageUploadResponse("Image uploaded successfully: " +
//                file.getOriginalFilename());
//
//    }
//
//    @Transactional
//    public ImageEntity getInfoByImageByName(String name) {
//        Optional<ImageEntity> dbImage = imageDataRepository.findByName(name);
//
//        return ImageEntity.builder()
//                .name(dbImage.get().getName())
//                .type(dbImage.get().getType())
//                .imageData(ImageUtil.decompressImage(dbImage.get().getImageEntity())).build();
//
//    }
//
//    @Transactional
//    public byte[] getImage(String name) {
//        Optional<ImageEntity> dbImage = imageDataRepository.findByName(name);
//        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
//        return image;
//    }
//
//
//}