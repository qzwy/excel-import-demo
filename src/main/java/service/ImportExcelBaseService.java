package service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

@Component
public class ImportExcelBaseService {

  /**
   * 导入值校验
   * @param sheet 工作表
   * @param row 行
   * @param colNum 列编号
   * @param errorHint 错误提示
   * @throws RuntimeException 校验通过返回空，否则抛出异常
   */
  public void validCellValue(Sheet sheet,Row row,int colNum,String errorHint) {
    if("".equals(this.getCellValue(sheet, row, colNum - 1))) {
      throw new RuntimeException("校验 :第" + (row.getRowNum() + 1) + "行" + colNum +"列"+ errorHint + "不能为空");
    }
  }

  /**
   * 从输入流中获取Excel工作表
   * @param iStream 输入流
   * @param fileName .xls\.xlsx后缀的文件名
   * @return 文件名为空返回空；
   *          格式不正确抛出异常
   *          正确返回excel工作空间对象
   */
  public Workbook getWorkbookByInputStream(InputStream iStream, String fileName){
    Workbook workbook = null;
    try {
      if (null == fileName){
        return null;
      }
      if (fileName.endsWith(".xls")){
        workbook = new HSSFWorkbook(iStream);
      }else if (fileName.endsWith(".xlsx")){
        workbook = new XSSFWorkbook(iStream);
      }else{
        throw new IOException("The document type don't support");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (iStream != null){
        try{
          iStream.close();
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }
    return workbook;
  }

  /**
   * 从workBook中获取一个sheet，如果没有就创建一个
   * @param workbook 工作空间
   * @param index 第几个sheet
   * @return 返回sheet
   */
  public Sheet getSheetByWorkbook(Workbook workbook, int index) {
    Sheet sheet = workbook.getSheetAt(index);
    if (null == sheet){
      sheet = workbook.createSheet();
    }
    sheet.setDefaultRowHeightInPoints(20);
    sheet.setDefaultColumnWidth(20);
    return sheet;
  }

  public String getCellValue(Sheet sheet, Row row, int column){
    if (sheet == null || row == null){
      return " ";
    }
    return this.getCellValue(row.getCell(column));
  }

  public String getCellValue(Cell cell){
    if(cell == null) {
      return "";
    }
    switch(cell.getCellType()) {
      case NUMERIC:
        Number number = cell.getNumericCellValue();
        String numberStr = String.valueOf(number);

        if(numberStr.endsWith(".0")) {
          numberStr = numberStr.replace(".0", "");//取整数
        }
        if(numberStr.indexOf("E") >=0 ) {
          numberStr = new DecimalFormat("#").format(number);//取整数
        }

        return numberStr;
      case STRING:
        return cell.getStringCellValue().trim();
      case FORMULA://公式
        return "";
      case BLANK:
        return "";
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      default :
        break;
    }

    return "";
  }


  /**
   * 判断该行是否为空行
   * @param row 行
   * @return 为空行返回true,不为空行返回false
   */
  public boolean isBlankRow(Row row) {
    if(row == null) {
      return true;
    }

    Iterator<Cell> iter = row.cellIterator();
    while(iter.hasNext()) {
      Cell cell = iter.next();
      if(cell == null) {
        continue;
      }

      String value = this.getCellValue(cell);
      if(!this.isNULLOrBlank(value)) {
        return false;
      }
    }

    return true;
  }

  /**
   * 判断一个对象是否为空
   * @param obj 对象
   * @return 为空返回true,不为空返回false
   */
  public boolean isNULLOrBlank(Object obj) {
    if(obj != null && !"".equals(obj.toString())) {
      return false;
    }

    return true;
  }
}
