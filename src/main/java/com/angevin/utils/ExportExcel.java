package com.angevin.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 导出excel的方法
 * @author
 *
 */
public class ExportExcel<T> {


	/**
	 * 
	 * @param title: sheet名称
	 * @param dataList: 数据
	 * @param headers: 表头信息
	 * @param fieldNames: 字段列表
	 * @param out
	 * @throws Exception
	 */
	public void exportExcept(String title, List<T> dataList, List<String> headers, List<String> fieldNames, OutputStream out ) throws Exception{
		//声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		//样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
	    // 生成一个字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setColor(HSSFColor.VIOLET.index);
		titleFont.setFontHeightInPoints((short) 12);
		titleStyle.setFont(titleFont);
		
		HSSFCellStyle contentStyle = workbook.createCellStyle();
		HSSFFont contentFont = workbook.createFont();
		contentFont.setFontHeightInPoints((short) 12);
		contentStyle.setWrapText(true);
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		contentStyle.setFont(contentFont);
		
		//产生表格标题行
		HSSFRow titleRow = sheet.createRow(0);
		int rowIndex = 0;
		if(headers != null && headers.size() != 0){
			for(int i = 0; i < headers.size(); i++){
				HSSFCell cell = titleRow.createCell(i);
				cell.setCellStyle(titleStyle);
				String[] hearderContent = headers.get(i).split("_");
				HSSFRichTextString text = new HSSFRichTextString(hearderContent[0]);
				Short colSize = 4000;
				if(hearderContent.length == 2 && hearderContent[1] != null && hearderContent[1].compareTo("") != 0){
					colSize = Short.parseShort(hearderContent[1]);
				}
				cell.setCellValue(text);
				sheet.setColumnWidth(i, colSize);
			}
			rowIndex++;
		}
		if(dataList != null && !dataList.isEmpty()){
			for(int i = 0 ; i < dataList.size(); i++){
				T t = dataList.get(i);
				List<String> getMethodNames = new ArrayList<String>();
				for(String fieldName : fieldNames){
			        //反射得到方法名
		            String getMethodName = "get"
		            	+ fieldName.substring(0, 1).toUpperCase() 
		            	+ fieldName.substring(1);
					getMethodNames.add(getMethodName);
				}
				int methodNum = getMethodNames.size();
				if(methodNum != 0){
					HSSFRow contentRow = sheet.createRow(rowIndex);
					rowIndex++;
					for(int j = 0; j < methodNum; j++){
						HSSFCell cell = contentRow.createCell(j);
			            cell.setCellStyle(contentStyle);
			            try{
			            	Class invokeClass = t.getClass();
			            	Method getMethod = invokeClass.getMethod(getMethodNames.get(j), new Class[] {});
			            	Object value = getMethod.invoke(t, new Object[] {});
			            	String strValue = null;
			            	//类型转换
			            	if(value instanceof Date){
			            		Date date = (Date) value;
			                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			                    strValue = sdf.format(date);
			            	}else{
			            		if(value == null){
			            			strValue = "";
			            		}else{
			            			strValue = value.toString();
			            		}
			            		
			            	}
			            	//如果是数字，则改变数据类型，否则excel中会显示错误
			            	if(strValue != null && strValue.compareTo("") != 0){
			                    Pattern p = Pattern.compile("^(\\d+([.]?\\d+){0,1})$");  
			                    Matcher matcher = p.matcher(strValue);
			                    if(matcher.matches()){
			                       cell.setCellValue(Double.parseDouble(strValue));
			                    }else{
			                       HSSFRichTextString richString = new HSSFRichTextString(strValue);
			                       cell.setCellValue(richString);

			                    }
			            	}
			            }catch(Exception e){
			            	e.printStackTrace();
			            	throw e;
			            }
					}
				}
			}
		}

		try {
			workbook.write(out);
		} catch (IOException e){
			e.printStackTrace();
			throw e;
	    }
	}
	
	
	
}
