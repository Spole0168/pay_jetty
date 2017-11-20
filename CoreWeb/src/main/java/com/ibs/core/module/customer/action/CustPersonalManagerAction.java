/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.core.module.basefunc.domain.City;
import com.ibs.core.module.customer.biz.ICustPersonalBiz;
import com.ibs.core.module.customer.dto.CustPersonalDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;


/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CustPersonalManagerAction extends CrudBaseAction {

    private ICustPersonalBiz custPersonalBiz;
    private CustPersonalDto custPersonalDto;
    private IDataDictService dataDictService;
    private String id;
    private String operaType;
    // 存放异步查询的数据。
    private Map<String, Object> map;
    
    //证件类型
    private static  List<OptionObjectPair> certTypeList;
    private static  String certTypeRender;
    //会员状态 01-未生效 02-已生效
    private static  List<OptionObjectPair> custStatusTypeList;
    private static  String custStatusTypeRender;
    //实名认证级别
    private static  List<OptionObjectPair> realNameLeveTypeList;
    private static  String realNameLeveTypeRender;
    
    

    public ICustPersonalBiz getCustPersonalBiz() {
        return custPersonalBiz;
    }


    public Map<String, Object> getMap() {
        return map;
    }


    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    public void setCustPersonalBiz(ICustPersonalBiz custPersonalBiz) {
        this.custPersonalBiz = custPersonalBiz;
    }

    public IDataDictService getDataDictService() {
        return dataDictService;
    }

    public void setDataDictService(IDataDictService dataDictService) {
        this.dataDictService = dataDictService;
    }

    public CustPersonalDto getCustPersonalDto() {
        return custPersonalDto;
    }

    public void setCustPersonalDto(CustPersonalDto custPersonalDto) {
        this.custPersonalDto = custPersonalDto;
    }
    
    public static List<OptionObjectPair> getCertTypeList() {
        return certTypeList;
    }

    public static void setCertTypeList(List<OptionObjectPair> certTypeList) {
        CustPersonalManagerAction.certTypeList = certTypeList;
    }

    public static String getCertTypeRender() {
        return certTypeRender;
    }

    public static void setCertTypeRender(String certTypeRender) {
        CustPersonalManagerAction.certTypeRender = certTypeRender;
    }

    public static List<OptionObjectPair> getCustStatusTypeList() {
        return custStatusTypeList;
    }

    public static void setCustStatusTypeList(List<OptionObjectPair> custStatusTypeList) {
        CustPersonalManagerAction.custStatusTypeList = custStatusTypeList;
    }

    public static String getCustStatusTypeRender() {
        return custStatusTypeRender;
    }

    public static void setCustStatusTypeRender(String custStatusTypeRender) {
        CustPersonalManagerAction.custStatusTypeRender = custStatusTypeRender;
    }

    public static List<OptionObjectPair> getRealNameLeveTypeList() {
        return realNameLeveTypeList;
    }

    public static void setRealNameLeveTypeList(List<OptionObjectPair> realNameLeveTypeList) {
        CustPersonalManagerAction.realNameLeveTypeList = realNameLeveTypeList;
    }

    public static String getRealNameLeveTypeRender() {
        return realNameLeveTypeRender;
    }

    public static void setRealNameLeveTypeRender(String realNameLeveTypeRender) {
        CustPersonalManagerAction.realNameLeveTypeRender = realNameLeveTypeRender;
    }

    private void getDictRenders() {
        if(null!=certTypeList&&certTypeList.size()>0){
            logger.info("CorCustPersonalManagerAction.java--getDictRenders()  个人会员管理模块 数据字典已经加载过了");
            return;
        }
        logger.info("CorCustPersonalManagerAction.java--getDictRenders()  个人会员管理模块 初次加载数据字典");
        //证件类型
        certTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_CERT_TYPE);
        certTypeRender = SelectRenderUtils.toRenderString(certTypeList);
        //会员状态
        custStatusTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_CUST_STATUS);
        custStatusTypeRender = SelectRenderUtils.toRenderString(custStatusTypeList);
        for(int i=0;i<custStatusTypeList.size();i++){
            OptionObjectPair oop = custStatusTypeList.get(i);
            if(CorConstants.DATA_DICT__CORE_MERCHANT_STATUS_INVALID.equals(oop.getKey())){//去掉会员类型中的个人。
                custStatusTypeList.remove(oop);
            }
        }
        //实名认证级别
        realNameLeveTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_AUTHENTICATION_LEVEL);
        realNameLeveTypeRender = SelectRenderUtils.toRenderString(realNameLeveTypeList);
        
    }
    @Override
    public String list() {
        getDictRenders();
        return SUCCESS;
    }

    @Override
    public String search() {
        setQueryCondition();
        logger.info("CorCustPersonalManagerAction.java--search()  查询Paramters ="+custPersonalDto);
        Page<CustPersonalDto> result = (Page<CustPersonalDto>) custPersonalBiz.findCustPersonalDtosBySql(queryPage, custPersonalDto);
        setResult(result);
        return AJAX_RETURN_TYPE;
    }
    
    /**
     * <p> 
     * <p>
     * <p>
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年1月17日
     * <p>Title			: 	CorCustPersonalManagerAction
     * <p>return_type	:	void
     */
    private void setQueryCondition() {
        custPersonalDto = new CustPersonalDto();
        custPersonalDto.setCustCode(getSearchFields().get("custCode"));
        custPersonalDto.setLocalName(getSearchFields().get("localName"));
        custPersonalDto.setCertType(getSearchFields().get("certType"));
        custPersonalDto.setCertNum(getSearchFields().get("certNum"));
        custPersonalDto.setCustStatus(getSearchFields().get("custStatus"));//
        custPersonalDto.setRealNameLeve(getSearchFields().get("realNameLeve"));
        custPersonalDto.setStartCreateTime(getSearchFields().get("startCreateTime"));//startCreateTime
        custPersonalDto.setEndCreateTime(getSearchFields().get("endCreateTime"));//endCreateTime
        custPersonalDto.setTelephone(getSearchFields().get("telephone"));              //手机号码telephone
        logger.info("CorCustPersonalManagerAction.java---setQueryCondition() 查询参数：=====" + custPersonalDto.toString());
    }

    @Override
    public String create() {
        custPersonalDto = custPersonalBiz.getCustPersonalDtoById(id);
        String citycode = custPersonalDto.getCity();
        if(null!= citycode && citycode.length()>0 ){
            City cc = custPersonalBiz.getCityInfoByCityCode(custPersonalDto.getCity());
            custPersonalDto.setCountry(cc.getCountryName());
            custPersonalDto.setProvience(cc.getProvinceName());
            custPersonalDto.setCity(cc.getCityName());
        }
        return SUCCESS;
    }
    /**
     * <p> 激活未生效的个人会员  --生成个人账户
     * <p>
     * <p>Creator		:	Spole
     * <p>Date			:	2017年2月7日
     * <p>Title			: 	CustPersonalManagerAction
     * <p>return_type	:	String
     */
    public String activePersonalAccount(){
        logger.info("CorCustPersonalManagerAction.java--activePersonalAccount()  Paramters id="+id);
        map = new HashMap<String, Object>();
        boolean flag = false;
        if (null != id && id.length() > 0) {
            custPersonalDto = custPersonalBiz.getCustPersonalDtoById(id);
            custPersonalDto.setUpdator(getUserName());
            custPersonalDto.setCreator(getUserName());
            flag = custPersonalBiz.activePersonalAccount(custPersonalDto);
        }
        if (flag) {
            map.put("status", CorConstants.CORE_AJAX_RESULT_Y);
        } else {
            map.put("status", CorConstants.CORE_AJAX_RESULT_N);
        }

        return AJAX_RETURN_TYPE;
    }
    
    public String modify() {
        return null;
    }

    @Override
    public String saveOrUpdate() {
        return null;
    }

    @Override
    public String delete() {
        return null;
    }

    @Override
    public String export() {
        return null;
    }


}
