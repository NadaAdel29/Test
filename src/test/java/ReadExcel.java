import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ReadExcel {
    public String [][]readSheet() throws IOException, InvalidFormatException {
        File MyFile= new File(".\\TestDate\\Book1.xlsx");
        XSSFWorkbook WB= new XSSFWorkbook(MyFile);
        XSSFSheet mysheet = WB.getSheet("Sheet1");
        int numberOfRows= mysheet.getPhysicalNumberOfRows();
                int numberOfColumns= mysheet.getRow(0).getLastCellNum();
                String[][]myArray= new String[numberOfRows-1][numberOfColumns];
                for (int i=1;i<numberOfRows;i++)
                {
                    for (int a=0;a<numberOfColumns;a++)
                {
                    XSSFRow row= mysheet.getRow(i);
                    myArray[i-1][a]=row.getCell(a).getStringCellValue();
                }
                }
        return myArray;
    }
}
