package com.meeket.app.controllers.file;


import com.meeket.app.helper.IUserLogged;
import com.meeket.app.helper.UploadDownloadUtil;
import com.meeket.app.models.ResponseObject;
import com.meeket.app.models.file.MyFile;
import com.meeket.app.models.user.User;
import com.meeket.app.payload.response.MyFileResponse;
import com.meeket.app.repository.file.MyFileRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/v1/files")
public class MyFileController {

    @Autowired
    MyFileRepository myFileRepository;

//    @Autowired
//    IUserLogged userLogged;


//    @GetMapping(value = "/meo.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<byte[]> getImage() throws IOException {
//
//        var imgFile = new ClassPathResource("image/meo.jpg");
//        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(bytes);
//    }

//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    @PostMapping(value ="/upload", consumes = {"multipart/form-data"})
//    ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile multipartFile){
//
//        long userid = userLogged.getId();
//        User userUpload = userLogged.getUser();
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        long size = multipartFile.getSize();
//        UploadDownloadUtil uploadDownloadUtil = new UploadDownloadUtil();
//        String fileCode = uploadDownloadUtil.saveFile( userid,fileName,multipartFile);
//
//        if(fileCode==null){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FAILED","Could not upload the file" ,"")
//            );
//
//        }else{
//
//            MyFile myFile =MyFile.builder().
//                    name(fileName)
//                    .size(size)
//                    .code(fileCode)
//                    .userId(userUpload)
//                    .build();
//            myFileRepository.save(myFile);
//
//
//            var myFileResponse = MyFileResponse.builder()
//                    .forUserId(userid)
//                    .data(myFile)
//                    .build();
//
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("TRUE","Uploaded the file successfully ",myFileResponse));
//        }
//
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(value = "/admin/page/{pageNo}", method = GET)
//    @Operation(summary = "pageで　fileの情報を取得")
//    @ResponseBody
//    ResponseEntity<ResponseObject> getMyFilePage(@PathVariable int pageNo){
//        Pageable pageable = PageRequest.of(pageNo,5);
//        Page<MyFile> myFilePage = myFileRepository.findAll(pageable);
//        if(!myFilePage.isEmpty()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE","Get page at "+ pageNo,myFilePage)
//            );
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE","Cannot find list  myFile with pageNo= "+ pageNo ,"")
//            );
//        }
//    }
//
//
//    @GetMapping(value = "/admin/{id}")
//    @Operation(summary = "idで　fileの情報を取得")
//    @PreAuthorize("hasRole('ADMIN')")
//    ResponseEntity<ResponseObject> getMyFileById(@Parameter(description = "idを入力して MyFileの情報を取得の情報を取得")
//                                               @PathVariable("id") int id){
//
//        MyFile myFile = myFileRepository.getMyFileById(id);
//
//        MyFileResponse myFileResponse = MyFileResponse.builder()
//                .forUserId(myFile.getUserId().getId())
//                .data(myFile)
//                .build();
//
//        if(!Objects.isNull(myFileResponse)){
//            return  ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE","Get myFile  by id "+id,myFileResponse)
//            );
//        }else{
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE","Get MyFile  at id "+ id+" not found","")
//            );
//        }
//
//    }
//
//    @GetMapping(value = "/admin/code/{code}")
//    @PreAuthorize("hasRole('ADMIN')")
//    @Operation(summary = "codeで　myFileの情報を取得")
//    ResponseEntity<ResponseObject> getMyFileByCode(@Parameter(description = "codeを入力して myFileの情報を取得の情報を取得")
//                                                 @PathVariable("code") String code){
//        MyFile myFile = myFileRepository.getMyFileByCode(code);
//
//        MyFileResponse myFileResponse = MyFileResponse.builder()
//                .forUserId(myFile.getUserId().getId())
//                .data(myFile)
//                .build();
//
//        if(!Objects.isNull(myFileResponse)){
//            return  ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE","Get myFile  by code "+code,myFileResponse)
//            );
//        }else{
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE","Get myFile  by code  "+ code+" not found","")
//            );
//        }
//
//    }
//    @GetMapping(value = "")
//    @Operation(summary = "ユーザーのすべてmyFileの情報を取得")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    ResponseEntity<ResponseObject> getListForUser(){
//
//        long userid= userLogged.getId();
//
//        List<MyFile> myFiles = myFileRepository.getListForUser(userid);
//
//        MyFileResponse myFileResponse = MyFileResponse.builder()
//                .forUserId(userid)
//                .data(myFiles)
//                .build();
//
//        if(!Objects.isNull(myFileResponse)){
//            return  ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("TRUE","Get myFile  of "+ userLogged,myFileResponse)
//            );
//        }else{
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FALSE","Get myFile  of  "+ userLogged+" not found","")
//            );
//        }
//
//    }
//
//    @GetMapping(value ="/download", consumes = {"multipart/form-data"})
//    ResponseEntity<?> downloadFile(@RequestParam("fileCode") String fileCode){
//
//        UploadDownloadUtil uploadDownloadUtil = new UploadDownloadUtil();
//
//        Resource resource = null;
//
//        resource = uploadDownloadUtil.getFileAsResource(fileCode);
//
//        if(resource == null){
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("FAILED","Could not upload the file" ,"")
//            );
//
//        }else {
//            String contentType ="application/octet-stream";
//            String headerValue =String.format("attachment; filename=\"" + resource.getFilename() + "\"");
//
//            return ResponseEntity.status(HttpStatus.OK)
//                    .contentType(MediaType.parseMediaType(contentType))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,headerValue)
//                    .body(resource);
//
//        }
//
//
//    }
}
