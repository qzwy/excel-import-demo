package service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TestTable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImportExcelService {

  /**
   * 获取导入的Excel表中数据
   * @param file 文件
   * @return 返回集合
   */
  List<TestTable> importExcelWithSimple(MultipartFile file);
}
