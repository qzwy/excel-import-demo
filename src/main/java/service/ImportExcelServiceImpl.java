package service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TestTable;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportExcelServiceImpl extends ImportExcelBaseService implements ImportExcelService{

  @Override
  public List<TestTable> importExcelWithSimple(MultipartFile file) {
    int rowNum =0;//已取值的行数
    int colNum = 0;//列号
    int realRowCount = 0;//真正有数据的行数

    System.out.println("file = " + file.getOriginalFilename());

    //得到工作空间
    Workbook workbook = null;
    try {
      workbook = super.getWorkbookByInputStream(file.getInputStream(), file.getOriginalFilename());
    } catch (IOException e) {
      e.printStackTrace();
    }

    //得到工作表
    Sheet sheet = super.getSheetByWorkbook(workbook, 2);
    if (sheet.getRow(2000) != null){
      throw new RuntimeException("系统已限制单批次导入必须小于或等于2000笔！");
    }

    realRowCount = sheet.getPhysicalNumberOfRows();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<TestTable> list = new ArrayList<TestTable>();


    for(Row row:sheet) {
      TestTable testTable = new TestTable();
      if(realRowCount == rowNum) {
        break;
      }

      if(super.isBlankRow(row)) {//空行跳过
        continue;
      }

      if(row.getRowNum() == -1) {
        continue;
      }else {
        if(row.getRowNum() < 2) {//第一行表头跳过
          continue;
        }
      }

      rowNum ++;
      colNum = 0;

//      super.validCellValue(sheet, row, ++ colNum, "公司");
      testTable.setCompanyName(super.getCellValue(sheet, row, 0));

//      super.validCellValue(sheet, row, ++ colNum, "产业公司");
      testTable.setIndustryCompany(super.getCellValue(sheet, row, 1));

//      super.validCellValue(sheet, row, ++ colNum, "部门");
      testTable.setDepartment(super.getCellValue(sheet, row, 2));

//      super.validCellValue(sheet, row, ++ colNum, "PAMS编号");
      testTable.setPamsid(super.getCellValue(sheet, row, 3));

//      super.validCellValue(sheet, row, ++ colNum, "项目名称");
      testTable.setProjectName(super.getCellValue(sheet, row, 4));

//      super.validCellValue(sheet, row, ++ colNum, "项目负责人");
      testTable.setPrincipal(super.getCellValue(sheet, row, 5));

//      super.validCellValue(sheet, row, ++ colNum, "中标时间");
      testTable.setBin_time(super.getCellValue(sheet, row, 6));

//      super.validCellValue(sheet, row, ++ colNum, "中标金额");
      testTable.setBidAmount(super.getCellValue(sheet, row, 7));

//      super.validCellValue(sheet, row, ++ colNum, "项目状态");
      testTable.setState(super.getCellValue(sheet, row, 8));

//      super.validCellValue(sheet, row, ++ colNum, "项目类型");
      testTable.setProjectType(super.getCellValue(sheet, row, 9));

      list.add(testTable);
    }
    return list;
  }
}
