package com.example.commom.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对excel操作的工具类
 * 可以根据自己用的类去生成
 * @Author 阿杰
 * @create 2021-03-31 15:50
 */
public class EasyExcelUtil {

    /**
     * 读取excel
     * @param file 导入的文件流
     * @param model 生成的类
     * @param <T>
     * @return 对象数组
     */
    public static<T> List<T> readExcel(InputStream file, Class<T> model) {
        List<T> list = new ArrayList<>();
        EasyExcel
                //读取的文件
                .read(file)
                //反射获取类型
                .head(model)
                //excel类型 (可以指定 xlx,xlsx,csx)
                //.excelType(ExcelTypeEnum.XLSX)
                //读取的excel左下角的名字
                .sheet(0)
                //注册监听器
                .registerReadListener(new AnalysisEventListener<T>() {
                    @Override
                    public void invoke(T t, AnalysisContext analysisContext) {
                        list.add(t);
                    }

                    @Override
                    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                        System.out.println("读取完毕" + model);
                    }
                }).doRead();
        return list;
    }

    /**
     * 响应给浏览器的excel文件
     * @param response servlet响应对象
     * @param fileName 设置文件明
     * @param list  数据列表
     * @param clazz 响应类
     * @param <T>
     * @throws IOException
     */
    public static<T> void writerExcel(HttpServletResponse response, String fileName, List<T> list, Class<T> clazz) throws IOException {
        try {
            String fileName1 = new String(fileName.getBytes(), StandardCharsets.ISO_8859_1);
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName1 );
            EasyExcel.write(response.getOutputStream(), clazz)
                    //设置不自动关闭流
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("模板")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
            //重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
        }
    }
}
