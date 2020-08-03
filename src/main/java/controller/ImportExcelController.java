package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TestTable;
import org.bouncycastle.util.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.ImportExcelService;

@RestController
public class ImportExcelController {

  @Autowired
  private ImportExcelService importExcelService;

  @PostMapping("/Test")
  public ResponseEntity<?> testImport(@RequestParam("file") MultipartFile file) {
    List<TestTable> testTables = importExcelService.importExcelWithSimple(file);

    if(testTables == null || testTables.size() == 0 ) {
      return ResponseEntity.ok("Fail");
    }else {
      for(TestTable testTable : testTables) {
        System.out.println("testTable = " + testTable);
      }
      //批量插入list到数据库

      return ResponseEntity.ok("Successful");
    }
  }

  @GetMapping("/Test")
  public String test2(MultipartFile file) {
    return "111";
  }

}
