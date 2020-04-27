package com.cyclingrecord.controllers;


import com.cyclingrecord.FileResponse;
import com.cyclingrecord.services.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.MalformedURLException;
import java.util.stream.Collectors;


@Controller
public class GalleryController {
    private StorageService storageService;

    public GalleryController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/gallery")
    public String listAllFiles(Model model){
        model.addAttribute("files", storageService.loadAll().map(
                path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(path.getFileName().toString())
                .toUriString())
                .collect(Collectors.toList()));
        return "gallery";
    }

    @GetMapping("/download/{filename;.+")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws MalformedURLException {
        Resource resource = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload-file")
    @ResponseBody
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String name = storageService.store(file);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

}

