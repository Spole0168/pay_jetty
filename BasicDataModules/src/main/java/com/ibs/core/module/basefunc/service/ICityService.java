package com.ibs.core.module.basefunc.service;

import java.util.List;
import java.util.Map;

import com.ibs.core.module.basefunc.domain.City;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

public interface ICityService {

    List<OptionObjectPair> getAllCountry();

    List<OptionObjectPair> getProvinceByCountryCode(String countryCode);

    List<OptionObjectPair> getCityByProvinceCode(String provinceCode);
    /**
     * 
     * <p>根据cityCode 获得相关的 区域信息
     * <p>不存在，返回null
     * <p>Creator       :   Spole
     * <p>Date          :   2017年1月3日
     * <p>Title     :   ICityDao
     * <p>return_type   :   City
     */
    City getCityInfoByCityCode(String cityCode);
    /**
     * 根据省编号得到城市列表
     * @param provinceCode
     * @return
     */
    City getCityListByProvinceCode(String provinceCode);
}
