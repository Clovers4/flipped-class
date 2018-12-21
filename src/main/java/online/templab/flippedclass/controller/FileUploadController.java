package online.templab.flippedclass.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import online.templab.flippedclass.common.excel.ExcelService;
import online.templab.flippedclass.common.multipart.StorageService;
import online.templab.flippedclass.common.multipart.excp.EncodeException;
import online.templab.flippedclass.common.multipart.excp.StorageFileNotFoundException;
import online.templab.flippedclass.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 作为文件上传/下载 api 的参考样例
 *
 * @author wk
 */
@Slf4j
@Controller
@RequestMapping(value = "/multipart")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ExcelService excelService;

    @GetMapping(value = "")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping(value = "/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);

        // 解决下载文件时，文件名乱码问题
        String encodedFilename = "";
        try {
            encodedFilename = new String(file.getFilename().getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new EncodeException(e.getMessage());
        }

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + encodedFilename + "\"").body(file);
    }

    @PostMapping(value = "loadStudentList")
    public String loadStudentList(@RequestParam("file") MultipartFile file, Long klassId,
                                  RedirectAttributes redirectAttributes) {
        List<Student> records = excelService.loadStudentList(file);
        log.info("klassId : " + klassId);
        log.info(records.toString());
        for (Student record : records) {
            log.info(record.toString());
        }

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/multipart";
    }

    @PostMapping(value = "")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        storageService.storeReport(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/multipart";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
