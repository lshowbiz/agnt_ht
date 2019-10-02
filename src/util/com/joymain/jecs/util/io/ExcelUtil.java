package com.joymain.jecs.util.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.joymain.jecs.util.bean.ReflectUtil;
import com.joymain.jecs.util.date.DateUtil;
import com.joymain.jecs.util.string.StringUtil;

/**
 * 
 * 利用jexcel读取和写入Excel的公用方法
 * 
 */

public class ExcelUtil {

	/**
	 * WritableWorkbook wwb = Workbook.createWorkbook(file); WritableSheet wsheet = wwb.createSheet(sheetName, sheetNo); WritableSheet wsheet = wwb.getSheet(sheetName); WritableSheet wsheet = wwb.getSheet(sheetNo); WritableCell wcell = ws.getWritableCell(column, row);
	 * 
	 * wb.getSheets() 取得Excel文件的所有工作表对象 wb.getNumberOfSheets() 取得Excel文件的工作表的数量 wb.getSheet(sheetName) 获取工作表中名为sheetName工作表对象 wb.getSheet(sheetNo) 获得工作表第sheetNo个工作表对象,sheetNo=0 表示第一个 sheet.getColumns() 得到工作表的总列数 sheet.getRows() 得到工作表的总行数
	 */

	//----------------------------读文件方法--------------------------------------
	/**
	 * 创建一个只读的Workbook对象，对excel的任何读操作都要先用此方法定位到某一文件
	 * @param file 要读取的excel文件对象
	 * @return 只读的Workbook对象
	 */
	public static Workbook getWorkbook(File file) throws Exception {
		InputStream is = new FileInputStream(file);//创建文件输入流
		Workbook wb = Workbook.getWorkbook(is);//通过文件输入流创建读取Excel的workbook对象
		return wb;
	}

	/**
	 * 创建一个只读的Workbook对象，对excel的任何读操作都要先用此方法定位到某一文件
	 * @param is 要读取的输入流
	 * @return 只读的Workbook对象
	 * @throws Exception
	 */
	public Workbook getWorkbook(InputStream is) throws Exception {
		Workbook wb = Workbook.getWorkbook(is);//通过文件输入流创建读取Excel的workbook对象
		return wb;
	}

	/**
	 * 获得第一个工作表对象
	 * @param file 要读取的excel文件对象
	 * @param sheetNo 工作表序号
	 * @return 工作表对象
	 * @throws Exception
	 */
	public Sheet getSheet(File file, int sheetNo) throws Exception {
		InputStream is = new FileInputStream(file);
		Workbook wb = Workbook.getWorkbook(is);
		Sheet sheet = wb.getSheet(sheetNo);
		return sheet;
	}

	/**
	 * 得到某行某列的单元格内容，返回字符串型 如果某个单元格类型为数值型并且内容为空的话，那读取出的内容应为0。
	 * @param sheet 工作表对象
	 * @param column 列数
	 * @param row 行数
	 * @return 单元格内容
	 * @throws Exception
	 */
	public String getContents(Sheet sheet, int column, int row) throws Exception {
		//得到第几列第几行的单元格的单元格内容
		Cell cell = sheet.getCell(column, row);
		if (cell.getType() == CellType.DATE) {
			DateCell datec1 = (DateCell) cell;
			Date date = datec1.getDate();
			if (date != null) {
				return DateUtil.getDateTime("yyyy-MM-dd HH:mm:ss", date);
			}
		}
		String result = cell.getContents();
		return result;
	}

	/**
	 * 获取工作表某一行每个单元格的内容，
	 * @param sheet 工作表对象
	 * @param row 行数
	 * @return 行内容集合类型
	 */
	public ArrayList getRowContentList(Sheet sheet, int row) {
		Cell[] cell = sheet.getRow(row);
		ArrayList rowlist = new ArrayList();
		if (cell.length > 0) {
			for (int i = 0; i < cell.length; i++) {
				String content = cell[i].getContents();
				rowlist.add(content);
			}
		}
		return rowlist;
	}

	/**
	 * 获取工作表某一列每个单元格的内容。 如果某个单元格类型为数值型并且内容为空的话，那读取出的内容应为0。
	 * @param sheet 工作表对象
	 * @param column 列数
	 * @return 列内容集合类型
	 */
	public ArrayList getColContentList(Sheet sheet, int column) {
		Cell[] cell = sheet.getColumn(column);
		ArrayList columnlist = new ArrayList();
		if (cell.length > 0) {
			for (int i = 0; i < cell.length; i++) {
				String content = cell[i].getContents();
				columnlist.add(content);
			}
		}
		return columnlist;
	}

	/**
	 * 关闭可读Workbook对象
	 * @param wb
	 */
	public void closeWorkbook(Workbook wb) {
		try {
			wb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	//--------------------------------写文件方法-----------------------------------

	/**
	 * 创建一个可写入的工作表，把创建可写的WritableWorkbook对象和工作表对象都集成到此方法中。
	 * @param file 要创建的excel文件对象
	 * @param sheetName 要创建的工作表名
	 * @param sheetNo 要创建的工作表序号
	 * @return WritableSheet 创建过的工作表
	 */
	public WritableSheet CreateWritableExcel(File file, String sheetName, int sheetNo) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		WritableSheet ws = wwb.createSheet(sheetName, sheetNo);
		return ws;
	}

	/**
	 * 创建一个可写入的工作表，把创建可写的WritableWorkbook对象和工作表对象都集成到此方法中。
	 * @param file 要创建的excel文件对象的流
	 * @param sheetName 要创建的工作表名
	 * @param sheetNo 要创建的工作表序号
	 * @return WritableSheet 创建过的工作表
	 */
	public WritableSheet CreateWritableExcel(OutputStream os, String sheetName, int sheetNo) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(sheetName, sheetNo);
		return ws;
	}

	/**
	 * 在工作表中某个单元格插入一个字符串值
	 * 
	 * @param wsheet 工作表对象
	 * @param column 列号
	 * @param row 行号
	 * @param value 要插入的字符串
	 */
	public void addString(WritableSheet wsheet, int column, int row, String value) throws Exception {
		Label labelC = new Label(column, row, value);
		wsheet.addCell(labelC);
	}
	public void addString(WritableSheet wsheet, int column, int row, String value,WritableCellFormat wcf) throws Exception {
		Label labelC = new Label(column, row, value,wcf);
		wsheet.addCell(labelC);
	}


	/**
	 * 在工作表中某个单元格插入一个数值
	 * @param wsheet 工作表对象
	 * @param column 列号
	 * @param row 行号
	 * @param value 要插入的数值
	 */
	public void addNumber(WritableSheet wsheet, int column, int row, double value) {
		jxl.write.Number labelN = new jxl.write.Number(column, row, value);
		try {
			wsheet.addCell(labelN);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}
	
	public void addNumber(WritableSheet wsheet, int column, int row, double value,WritableCellFormat wcf) {
		jxl.write.Number labelN = new jxl.write.Number(column, row, value,wcf);
		try {
			wsheet.addCell(labelN);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 在工作表中某个单元格插入一个布尔型的值
	 * @param wsheet 工作表对象
	 * @param column 列号
	 * @param row 行号
	 * @param value 要插入的布尔型的值
	 */
	public void addBoolean(WritableSheet wsheet, int column, int row, boolean value) {
		try {
			jxl.write.Boolean labelB = new jxl.write.Boolean(column, row, value);
			wsheet.addCell(labelB);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 在工作表某一单元格中添加日期类型的数据
	 * @param wsheet 工作表对象
	 * @param column 列号
	 * @param row 行号
	 * @param value 要插入的日期类型的数据
	 */
	public void addDateTime(WritableSheet wsheet, int column, int row, Date value) {
		try {
			jxl.write.DateTime labelDT = new jxl.write.DateTime(column, row, value);
			wsheet.addCell(labelDT);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 合并单元格
	 * @param wsheet 工作表对象
	 * @param beginColumn 开始列
	 * @param beginRow 开始行
	 * @param endColumn 结束列
	 * @param endRow 结束行
	 */
	public void mergeCells(WritableSheet wsheet, int beginColumn, int beginRow, int endColumn, int endRow) {
		try {
			wsheet.mergeCells(beginColumn, beginRow, endColumn, endRow);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		} catch (WriteException we) {
			we.printStackTrace();
		}
	}

	/**
	 * 设置工作表中某一列的列宽
	 * @param wsheet 工作表对象
	 * @param columnNo 列号
	 * @param width 列宽
	 */
	public void setColumnWidth(WritableSheet wsheet, int columnNo, int width) {
		wsheet.setColumnView(columnNo, width);
	}

	/**
	 * 设置工作表中某一行的行高
	 * @param wsheet 工作表对象
	 * @param rowNo 行号
	 * @param height 行高
	 */
	public void setRowHeight(WritableSheet wsheet, int rowNo, int height) {
		try {
			wsheet.setRowView(rowNo, height);
		} catch (RowsExceededException ree) {
			ree.printStackTrace();
		}
	}

	/**
	 * 写入工作表，任何创建或者修改更新的操作最后都要调用此方法。
	 * @param wwb Excel文档对象
	 */
	public void writeExcel(WritableWorkbook wwb) {
		try {
			wwb.write();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * 关闭可写的Workbook对象
	 * @param wwb 可写的excel对象
	 */
	public void closeWritableWorkbook(WritableWorkbook wwb) {
		try {
			wwb.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 将数据库中的数据导入到工作表 File file=new File("c:/aaa.xls"); ExcelUtil eu=new ExcelUtil(); eu.batchWriteSheet(file,"bbb",0,0,list);
	 * @param file 要生成的excel文件
	 * @param sheetName 生成的工作表名称
	 * @param beginColumn 开始列
	 * @param beginRow 开始行
	 * @param datalist 插入工作表的数据列表
	 * @throws Exception
	 */
	public void batchWriteSheet(File file, String sheetName, int beginColumn, int beginRow, List datalist) throws Exception {
		ReflectUtil reflect = new ReflectUtil();
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		WritableSheet wsheet = wwb.createSheet(sheetName, 0);
		String str = "";
		if (!datalist.isEmpty()) {
			for (int i = 0; i < datalist.size(); i++) {
				Object obj = datalist.get(i);
				Method[] method = reflect.getPublicMethods(obj, "get");
				for (int n = 0; n < method.length; n++) {
					Object object = method[n].invoke(obj, null);
					str = StringUtil.getAvailStr(object);
					addString(wsheet, beginColumn + n, beginRow, str);
				}
				beginRow++;
			}
		}
		writeExcel(wwb);
		closeWritableWorkbook(wwb);
	}

	public static void main(String[] args) throws Exception {

		//--------------------------------------读文件-------------------------------------

		File file = new File("E://book1.xls"); //E:/SAVES/bx.XLS
		ExcelUtil eu = new ExcelUtil();
		//获取可读的工作表对象，定位到要读取的excel文件
		Workbook workbook = eu.getWorkbook(file);

		//读取此文件的第一个工作表，工作表序号从0开始。
		Sheet sheet = workbook.getSheet(0);

		System.out.println("Columns:" + sheet.getColumns());//获取工作表列数
		System.out.println("Rows:" + sheet.getRows());//获取工作表行数

		for (int i = 0; i < sheet.getRows(); i++) {
			for (int j = 0; j < sheet.getColumns(); j++) {
				System.out.print(eu.getContents(sheet, j, i) + " ");
			}
			System.out.println("***");
		}
		eu.closeWorkbook(workbook);

		//-----------------------------------写文件------------------------------------- 
		/*File file = new File("d:/aaa.xls");
		ExcelUtil eu = new ExcelUtil(); //创建可写入的工作表 
		WritableWorkbook wwb = Workbook.createWorkbook(file); 
		//在此创建的新excel文件创建一工作表 
		WritableSheet wsheet = wwb.createSheet("第一张工作表", 0); 
		//在工作表第三列第三行插入一字符串 
		eu.addString(wsheet, 2, 2, "我是一匹来自北方的狼"); 
		//得到此字符串内容 
		System.out.println(eu.getContents(wsheet, 2, 2)); 
		//插入一布尔型的值 
		eu.addBoolean(wsheet, 3, 3, false); 
		//插入一日期时间值 
		eu.addDateTime(wsheet, 4, 4, new Date()); 
		//插入数值 
		eu.addNumber(wsheet, 5, 5, 3.1415926); 
		eu.addNumber(wsheet, 5, 5, 3.14);
		//修改此单元格的值，和创建文件的用法一样。 
		eu.writeExcel(wwb); 
		eu.closeWritableWorkbook(wwb);*/

		System.out.println(DateUtil.convertDateToString(DateUtil.convertStringToDate("2008-08-09 08:00:00")));
	}
}
