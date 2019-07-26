package com.nexthero.demo.controller;

import com.nexthero.demo.model.RespBean;
import com.nexthero.demo.model.UserInfo;
import com.nexthero.demo.service.UserInfoService;
import com.nexthero.demo.storage.StorageFileNotFoundException;
import com.nexthero.demo.storage.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * FileUploadController
 *
 * @version 1.0
 */
@Api(value = "图片上传相关", tags = {"图片上传相关"})
@RestController
@RequestMapping(value = "api/user")
public class FileUploadController {

    private final StorageService storageService;
    private UserInfoService userInfoService;

    @Autowired
    public FileUploadController(StorageService storageService, UserInfoService userInfoService) {
        this.storageService = storageService;
        this.userInfoService = userInfoService;
    }

//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }

//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }

    @ApiOperation(value = "图片上传", notes = "图片上传", httpMethod = "POST")
    @PostMapping("uploadFace")
    public Map<String, Object> handleFileUpload(
            HttpServletRequest httpServletRequest,
            @RequestParam("file") MultipartFile file) {

        String filename = storageService.store(file);
        String relativePath = storageService.load(filename).toString().replaceAll("\\\\", "/");
        String faceImg = httpServletRequest.getScheme()+"://"+ httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + "/" + relativePath;
        UserInfo user = userInfoService.saveUserFaceImg(Long.parseLong(httpServletRequest.getParameter("userId")), faceImg);

        Map<String, Object> map = new HashMap<>();
        RespBean respBean = null;

        if (user == null) {
            respBean = new RespBean("502", "上传错误");
        } else {
            respBean = new RespBean("200", "上传成功");
        }

        map.put("status", respBean.getStatus());
        map.put("msg", respBean.getMsg());
        map.put("data", user);

        return map;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}