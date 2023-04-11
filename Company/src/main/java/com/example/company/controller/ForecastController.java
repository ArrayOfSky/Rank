package com.example.company.controller;

import com.example.commom.domain.company.Forecast;
import com.example.commom.domain.system.User;
import com.example.commom.entity.Result;
import com.example.commom.entity.ResultCode;
import com.example.commom.utils.EasyExcelUtil;
import com.example.company.service.ForecastService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * (Forecast)控制层
 *
 * @author makejava
 * @since 2023-04-09 10:08:17
 */
@RestController
@RequestMapping("/company")
@CrossOrigin
public class ForecastController {
    /**
     * 服务对象
     */
    @Resource
    private ForecastService forecastService;

    /**
     * 导入
     */
    @PostMapping("/forecast/import/{companyId}")
    public Result importForecast(MultipartFile file, @PathVariable("companyId")String companyId) throws IOException {
        forecastService.importForecast(file,companyId);
        return new Result(ResultCode.SUCCESS,null);
    }

    /**
     * 导出
     */
    @GetMapping("/forecast/download/{fileName}/{companyId}/{date}")
    public Result download(HttpServletResponse response, @PathVariable("fileName")String fileName, @PathVariable("companyId")String companyId,@PathVariable("date") Date date){
        try {
            List<Forecast> list = forecastService.selectByDay(companyId,date);;
            EasyExcelUtil.writerExcel(response,fileName,list,Forecast.class);
            return new Result(ResultCode.SUCCESS,null);
        } catch (Exception e) {
            return new Result(ResultCode.FAIL,null);
        }
    }

    /**
     * 自动生成
     */
  @RequestMapping("/forecast/{companyId}/{date}")
    public Result selectByDay(@PathVariable("companyId") String companyId, @PathVariable("date") Date date){
      System.out.println(companyId);
      System.out.println(date);
        List<Forecast> list = forecastService.selectByDay(companyId,date);
        return new Result(ResultCode.SUCCESS,list);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param forecast 参数对象
     * @return 单条数据
     */
    @RequestMapping(value = "/forecast/get", method = RequestMethod.GET)
    public Result selectOne(Forecast forecast) {
        Forecast result = forecastService.selectById(forecast.getId());
        return new Result(ResultCode.SUCCESS,result);
    }
    
    /**
     * 新增一条数据
     *
     * @param forecast 实体类
     * @return Response对象
     */
    @RequestMapping(value = "/forecast/insert", method = RequestMethod.POST)
    public Result insert(@RequestBody Forecast forecast) {
        int result = forecastService.insert(forecast);
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 修改一条数据
     *
     * @param forecast 实体类
     * @return Response对象
     */
    @RequestMapping(value = "/forecast/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Forecast forecast) {
        Forecast result = forecastService.update(forecast);
     return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 删除一条数据
     *
     * @param forecast 参数对象
     * @return Response对象
     */
    @RequestMapping(value = "/forecast/delete", method = RequestMethod.DELETE)
    public Result delete(Forecast forecast) {
        int result = forecastService.deleteById(forecast.getId());
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 查询全部
     *
     * @return Response对象
     */
    @RequestMapping(value = "/forecast/selectAll", method = RequestMethod.GET)
    public Result selectAll() {
        List<Forecast> result = forecastService.selectAll();
    return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 分页查询
     *
     * @param start 偏移
     * @param limit 条数
     * @return Response对象
     */
    @RequestMapping(value = "/forecast/selectPage", method = RequestMethod.GET)
    public Result selectPage(Integer start, Integer limit) {
        return new Result(ResultCode.SUCCESS,forecastService.selectPage(start, limit));
    }
    
}

