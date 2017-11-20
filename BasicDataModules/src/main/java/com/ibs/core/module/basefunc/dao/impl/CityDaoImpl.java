package com.ibs.core.module.basefunc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.ibs.core.module.basefunc.dao.ICityDao;
import com.ibs.core.module.basefunc.domain.City;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;

@Repository
public class CityDaoImpl extends BaseEntityDao<City> implements ICityDao {
    // 获取所有国家信息
    @Override
    public List<OptionObjectPair> getAllCountry() {
        List<OptionObjectPair> list = new ArrayList<OptionObjectPair>();
        OptionObjectPair objectPair = null;

        String sql = "select distinct COUNTRY_CODE countryCode, COUNTRY_NAME  countryName from T_COR_CITY  order by COUNTRY_CODE";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addScalar("countryCode", new StringType());
        query.addScalar("countryName", new StringType());
        List<Object[]> objs = query.list();
        if (objs != null) {
            for (Object[] obj : objs) {
                objectPair = new OptionObjectPair(obj[0].toString(), obj[1].toString());
                list.add(objectPair);
            }
        }
        return list;
    }

    // 根据 countryCode 获取 province
    @Override
    public List<OptionObjectPair> getProvinceByCountryCode(String countryCode) {
        List<OptionObjectPair> list = new ArrayList<OptionObjectPair>();
        OptionObjectPair objectPair = null;

        String sql = "select distinct PROVINCE_CODE provinceCode,PROVINCE_NAME provinceName from T_COR_CITY where COUNTRY_CODE ='"
                + countryCode + "' order by PROVINCE_CODE";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addScalar("provinceCode", new StringType());
        query.addScalar("provinceName", new StringType());
        List<Object[]> objs = query.list();
        if (objs != null) {
            for (Object[] obj : objs) {
                objectPair = new OptionObjectPair(obj[0].toString(), obj[1].toString());
                list.add(objectPair);
            }
        }
        return list;
    }

    // 根据 provinceCode 获取 city
    @Override
    public List<OptionObjectPair> getCityByProvinceCode(String provinceCode) {
        List<OptionObjectPair> list = new ArrayList<OptionObjectPair>();
        OptionObjectPair objectPair = null;

        String sql = "select distinct CITY_CODE cityCode,CITY_NAME cityName from T_COR_CITY where PROVINCE_CODE ='"
                + provinceCode + "' order by CITY_CODE";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addScalar("cityCode", new StringType());
        query.addScalar("cityName", new StringType());
        List<Object[]> objs = query.list();
        if (objs != null) {
            for (Object[] obj : objs) {
                objectPair = new OptionObjectPair(obj[0].toString(), obj[1].toString());
                list.add(objectPair);
            }
        }
        return list;
    }

    /* (non-Javadoc)
     * @see com.ibs.core.module.basefunc.dao.ICityDao#getCityInfoByCityCode(java.lang.String)
     */
    @Override
    public City getCityInfoByCityCode(String cityCode) {
        Criteria criteria = super.getSession().createCriteria(City.class);
        criteria.add(Restrictions.eq("isValid", CorConstants.DATA_DICT__IS_VALID));
        if(null!=cityCode && cityCode.length()>0){
            criteria.add(Restrictions.eq("cityCode", cityCode));
            List<City> list = criteria.list();
            if(null!=list&&list.size()>0){
                return list.get(0);     
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public City getCityListByProvinceCode(String provinceCode) {
        String hql = " from City where provinceCode = '"+provinceCode+"'";
        List<City> list = super.findByHql(hql, null, null);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

}
