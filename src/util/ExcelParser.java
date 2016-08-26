package util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelParser {
	
	private HSSFWorkbook  workbook;
	private String filePath;
	public List<Map<String, String>> list ;
	
	/**
	 * 构造函数
	 * @param filePath
	 * @throws Exception 
	 */
	public ExcelParser(String filePath) throws Exception{
		this.filePath = filePath;
		this.load();
	}
	
	public void load() throws Exception{
//		try {
			FileInputStream	file = new FileInputStream(filePath);
			System.out.println();
//		} catch (Exception e1) {
			System.out.println("excel文件不存在");
//		}
	}
	
	/**获取单元格数据
	 * @param sheetName sheet页名称
	 * @param rowNum  行数
	 * @param columnNum  列数
	 * @return
	 */
	private String getCellValue(String sheetName ,int rowNum ,int columnNum){
		
		try {
			workbook = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet =workbook.getSheet(sheetName);
			
			HSSFCell cell = sheet.getRow(rowNum).getCell(columnNum);
			try {
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String cellText = cell.getStringCellValue();
				return cellText ;
			} catch (Exception e) {
//				cell.setCellValue(str);
				System.out.println("此处有空值");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null ;
	}
	
	/**获取sheet页的全部内容,返回一个Map<String,String>
	 * @param sheetName  sheet页名称
	 * @return 
	 */
	
	public Map<String, String> getData(String sheetName) {
		
		Map<String, String> map = new LinkedHashMap<String,String>();
		
		try {
			workbook = new HSSFWorkbook(new FileInputStream(filePath));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum =sheet.getLastRowNum();
			
			for(int i=firstRowNum+1;i<=lastRowNum;i++){
				if(sheet.getRow(i)==null ){
					i++;
				}
				try {
					int	size = sheet.getRow(i).getLastCellNum();
					for(int j=0;j<size;j++){
							map.put( this.getCellValue(sheetName, firstRowNum, j), this.getCellValue(sheetName, i, j));
					}
				} catch (Exception e) {
					System.out.println(sheet.getRow(i)+ "有空行，请检查数据");
				}
				//遍历map
				Iterator<Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					System.out.println(it.next());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return map;
		
	}
	
}
