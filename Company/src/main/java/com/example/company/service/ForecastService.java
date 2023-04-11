package com.example.company.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.commom.domain.company.Forecast;
import com.example.commom.domain.system.User;
import com.example.commom.utils.IdWorker;
import com.example.company.dao.ForecastDao;
import com.example.company.listener.ImportListener;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * (Forecast表)服务实现类
 *
 * @author makejava
 * @since 2023-04-09 10:08:18
 */
@Service("forecastService")
public class ForecastService {
    @Resource
    private ForecastDao forecastDao;

    @Resource
    private IdWorker idWorker;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public Forecast selectById(String id) {
        return this.forecastDao.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param start 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public IPage<Forecast> selectPage(int start, int limit) {
        return this.forecastDao.selectPage(new Page(start,limit),null);
    }

    /**
     * 查询所有
     *
     * @return 实例对象的集合
     */
     public List<Forecast> selectAll() {
        return this.forecastDao.selectList(null);
     }
     
    
    /**
     * 新增数据
     *
     * @param forecast 实例对象
     * @return 实例对象
     */
    public int insert(Forecast forecast) {
    
        return this.forecastDao.insert(forecast);
    }


    /**
     * 修改数据
     *
     * @param forecast 实例对象
     * @return 实例对象
     */
    public Forecast update(Forecast forecast) {
        this.forecastDao.updateById(forecast);
        return this.selectById(forecast.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public int deleteById(String id) {
        return this.forecastDao.deleteById(id);
    }

    public List<Forecast> selectAllByCompanyId(String companyId) {
        LambdaQueryWrapper<Forecast> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Forecast::getCompanyId,companyId);
        return forecastDao.selectList(lambdaQueryWrapper);
    }

    public List<Forecast> selectByDay(String companyId, Date date) {
        LambdaQueryWrapper<Forecast> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Forecast::getCompanyId,companyId);
        lambdaQueryWrapper.eq(Forecast::getDate,date);
        return forecastDao.selectList(lambdaQueryWrapper);
    }

    public void importForecast(MultipartFile file,String companyId) throws IOException {
        EasyExcel.read(file.getInputStream(), Forecast.class, new ImportListener(this,companyId)).sheet().doRead();
    }
}

