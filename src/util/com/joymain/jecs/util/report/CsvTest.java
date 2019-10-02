package com.joymain.jecs.util.report;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class CsvTest {
	/**
     * CSV导出
     * 
     * @throws Exception
     */
    public void exportCsv() {
        List<String> dataList=new ArrayList<String>();
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,女");
        boolean isSuccess=CSVUtils.exportCsv(new File("D:/test/ljq.csv"), dataList);
        System.out.println(isSuccess);
    }
    
    /**
     * CSV导出
     * 
     * @throws Exception
     */
    public void importCsv()  {
        List<String> dataList=CSVUtils.importCsv(new File("D:/test/ljq.csv"));
        if(dataList!=null && !dataList.isEmpty()){
            for(String data : dataList){
                System.out.println(data);
            }
        }
    }
    
    public static void main(String[] args) {
    	new CsvTest().exportCsv();
	}
}
