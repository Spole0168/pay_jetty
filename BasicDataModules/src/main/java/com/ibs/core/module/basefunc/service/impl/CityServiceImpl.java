package com.ibs.core.module.basefunc.service.impl;

import java.util.List;

import com.ibs.core.module.basefunc.dao.ICityDao;
import com.ibs.core.module.basefunc.domain.City;
import com.ibs.core.module.basefunc.service.ICityService;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

public class CityServiceImpl implements ICityService {
    private ICityDao cityDao;

    public void setCityDao(ICityDao cityDao) {
        this.cityDao = cityDao;
    }

    // 获取所有国家信息
    @Override
    public List<OptionObjectPair> getAllCountry() {
        return cityDao.getAllCountry();
    }

    // 根据 countryCode 获取 province
    @Override
    public List<OptionObjectPair> getProvinceByCountryCode(String countryCode) {
        return cityDao.getProvinceByCountryCode(countryCode);
    }

    // 根据 provinceCode 获取 city
    @Override
    public List<OptionObjectPair> getCityByProvinceCode(String provinceCode) {
        return cityDao.getCityByProvinceCode(provinceCode);
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.basefunc.service.ICityService#getCityInfoByCityCode(java.lang.String)
     */
    @Override
    public City getCityInfoByCityCode(String cityCode) {
        return cityDao.getCityInfoByCityCode(cityCode);
    }

    @Override
    public City getCityListByProvinceCode(String provinceCode) {
        return cityDao.getCityListByProvinceCode(provinceCode);
    }
}
