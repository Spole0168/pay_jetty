package com.ibs.core.module.basefunc.test;

import java.util.List;

import org.junit.Test;
import org.w3c.dom.ls.LSInput;

import com.ibs.core.module.basefunc.domain.City;
import com.ibs.core.module.basefunc.service.ICityService;
import com.ibs.core.module.mdmbasedata.domain.PblBankDict;
import com.ibs.core.module.mdmbasedata.service.IPblBankDictService;

public class PblBankDictServiceTest extends BasefuncTestBase {
	private IPblBankDictService pblBankDictService;
	private ICityService cityService;
	@Test
	public void testGetCityListByProvinceCode(){
	    City city = cityService.getCityListByProvinceCode("310000");
	    logger.info("国家编码="+city.getCountryCode());
	}
	
	@Test
	public void testGetByBankBranchCode(){
		logger.info("enter into PblBankDictServiceTest.testGetByBankBranchCode");
		PblBankDict pblBankDict = pblBankDictService.getByBankBranchCode("103110005058");
		logger.info("银行名称：" + pblBankDict.getBankName() + ",分行名称：" + pblBankDict.getBankBranchName());
		assertEquals(pblBankDict.getBankBranchCode(), "103110005058");
	}
	
	@Test
    public void testGetByBankBranchCode11(){
        logger.info("enter into PblBankDictServiceTest.testGetByBankBranchCode");
        List<PblBankDict> list = pblBankDictService.getBankBranchList("10132233");
        System.out.println(list.size()+"  "+list.get(0).getBankName());
    }
	public IPblBankDictService getPblBankDictService() {
		return pblBankDictService;
	}
	public void setPblBankDictService(IPblBankDictService pblBankDictService) {
		this.pblBankDictService = pblBankDictService;
	}

    public ICityService getCityService() {
        return cityService;
    }

    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }
	
	
}
