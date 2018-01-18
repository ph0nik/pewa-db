package com.pewa.controller;

import com.pewa.config.ConfigFactory;
import com.pewa.tv.TvShowSearch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phonik on 2017-12-15.
 */
@RestController
@RequestMapping("/db-img/")
public class ImageController {

    private static final String imgPath = ConfigFactory.get("dbCache.imgPath");
    private static final Logger log = LogManager.getLogger(ImageController.class);

    @GetMapping(value = "{fileName:.+}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String fileName) {

        ResponseEntity<InputStreamResource> imageContent = null;
        log.info(imgPath + " | " + fileName);
        File imageFile = new File(imgPath + fileName);
        try {
            FileInputStream imageStream = new FileInputStream(imageFile);
            imageContent = ResponseEntity.ok()
                    .contentLength(imageFile.length())
                    .contentType(MediaType.parseMediaType(Files.probeContentType(imageFile.toPath())))
                    .body(new InputStreamResource(imageStream));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageContent;


                //Files.probeContentType(file.toPath())
//                .contentLength(gridFsFile.getLength())
//                .contentType(MediaType.parseMediaType(gridFsFile.getContentType()))
//                .body(new InputStreamResource(gridFsFile.getInputStream()));
    }

}
