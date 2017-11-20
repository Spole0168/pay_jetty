/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.ibs.common.module.frameworkimpl.file.domain.FilePersistence;
import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.core.module.customer.biz.ICustCompanyBiz;
import com.ibs.core.module.customer.dto.CustCompanyDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_CNL_TRANS_TRACE
 * @modify		: your comments goes here (author,date,reason).
 */
@SuppressWarnings("serial")
public class CustCompanyManagerAction extends CrudBaseAction {

    private CustCompanyDto custCompanyDto;
    private ICustCompanyBiz custCompanyBiz;
    private IDataDictService dataDictService;
    
    private String id;
    private String idName;
    private String code;
    private String operaType;// 0 -新增，1-查看，2-审核，3-修改
    // 存放异步查询的数据。
    private Map<String, Object> map;

    // 数据字典定义
    private static List<OptionObjectPair> countryList;
    // 会员类型
    private static  List<OptionObjectPair> custTypeList;
    private static  String custTypeRender;
    // 行业类型
    private static  List<OptionObjectPair> unitTypeList;
    private static  String unitTypeRender;
    // 法人证件类型
    private static  List<OptionObjectPair> corporateCertTypeList;
    private static  String corporateCertTypeRender;
    // 审核状态 下拉选择 01-“待审核”，02-“审核失败”，03-“审核通过”
    private static  List<OptionObjectPair> statusTypeList;
    private static  String statusTypeRender;
    //会员状态 01-未生效 02-已生效
    private static  List<OptionObjectPair> custStatusTypeList;
    private static  String custStatusTypeRender;
    // 操作界面使用
    private static  List<OptionObjectPair> statusOperaList;
    // 上传文件域对象
    private File file;
    // 上传文件名
    private String fileFileName;
    // 上传文件类型
    private String fileContentType;

    private IFilePersistenceService filePersistenceService;

    
    

    
    public CustCompanyDto getCustCompanyDto() {
        return custCompanyDto;
    }



    public void setCustCompanyDto(CustCompanyDto custCompanyDto) {
        this.custCompanyDto = custCompanyDto;
    }


    public ICustCompanyBiz getCustCompanyBiz() {
        return custCompanyBiz;
    }


    public void setCustCompanyBiz(ICustCompanyBiz custCompanyBiz) {
        this.custCompanyBiz = custCompanyBiz;
    }

    public IDataDictService getDataDictService() {
        return dataDictService;
    }


    public void setDataDictService(IDataDictService dataDictService) {
        this.dataDictService = dataDictService;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getIdName() {
        return idName;
    }


    public void setIdName(String idName) {
        this.idName = idName;
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getOperaType() {
        return operaType;
    }


    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }


    public Map<String, Object> getMap() {
        return map;
    }


    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


    public static List<OptionObjectPair> getCountryList() {
        return countryList;
    }


    public static void setCountryList(List<OptionObjectPair> countryList) {
        CustCompanyManagerAction.countryList = countryList;
    }


    public static List<OptionObjectPair> getCustTypeList() {
        return custTypeList;
    }


    public static void setCustTypeList(List<OptionObjectPair> custTypeList) {
        CustCompanyManagerAction.custTypeList = custTypeList;
    }


    public static String getCustTypeRender() {
        return custTypeRender;
    }


    public static void setCustTypeRender(String custTypeRender) {
        CustCompanyManagerAction.custTypeRender = custTypeRender;
    }


    public static List<OptionObjectPair> getUnitTypeList() {
        return unitTypeList;
    }


    public static void setUnitTypeList(List<OptionObjectPair> unitTypeList) {
        CustCompanyManagerAction.unitTypeList = unitTypeList;
    }


    public static String getUnitTypeRender() {
        return unitTypeRender;
    }


    public static void setUnitTypeRender(String unitTypeRender) {
        CustCompanyManagerAction.unitTypeRender = unitTypeRender;
    }


    public static List<OptionObjectPair> getCorporateCertTypeList() {
        return corporateCertTypeList;
    }


    public static void setCorporateCertTypeList(List<OptionObjectPair> corporateCertTypeList) {
        CustCompanyManagerAction.corporateCertTypeList = corporateCertTypeList;
    }


    public static String getCorporateCertTypeRender() {
        return corporateCertTypeRender;
    }


    public static void setCorporateCertTypeRender(String corporateCertTypeRender) {
        CustCompanyManagerAction.corporateCertTypeRender = corporateCertTypeRender;
    }


    public static List<OptionObjectPair> getStatusTypeList() {
        return statusTypeList;
    }


    public static void setStatusTypeList(List<OptionObjectPair> statusTypeList) {
        CustCompanyManagerAction.statusTypeList = statusTypeList;
    }


    public static String getStatusTypeRender() {
        return statusTypeRender;
    }


    public static void setStatusTypeRender(String statusTypeRender) {
        CustCompanyManagerAction.statusTypeRender = statusTypeRender;
    }


    public static List<OptionObjectPair> getStatusOperaList() {
        return statusOperaList;
    }


    public static void setStatusOperaList(List<OptionObjectPair> statusOperaList) {
        CustCompanyManagerAction.statusOperaList = statusOperaList;
    }


    public static List<OptionObjectPair> getCustStatusTypeList() {
        return custStatusTypeList;
    }


    public static void setCustStatusTypeList(List<OptionObjectPair> custStatusTypeList) {
        CustCompanyManagerAction.custStatusTypeList = custStatusTypeList;
    }


    public static String getCustStatusTypeRender() {
        return custStatusTypeRender;
    }


    public static void setCustStatusTypeRender(String custStatusTypeRender) {
        CustCompanyManagerAction.custStatusTypeRender = custStatusTypeRender;
    }


    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }


    public String getFileFileName() {
        return fileFileName;
    }


    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }


    public String getFileContentType() {
        return fileContentType;
    }


    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }


    public IFilePersistenceService getFilePersistenceService() {
        return filePersistenceService;
    }


    public void setFilePersistenceService(IFilePersistenceService filePersistenceService) {
        this.filePersistenceService = filePersistenceService;
    }


    /**
     * 加载数据字典
     */
    protected void getDictRenders() {//CORE_GLOBEBILL_INDUSTRY
        if(null!=custTypeList&&custTypeList.size()>0){
            logger.info("CustCompanyManagerAction.java--getDictRenders()  会员管理模块 数据字典已经加载过了");
            return;
        }
        logger.info("CustCompanyManagerAction.java--getDictRenders()  会员管理模块 初次加载数据字典");
        countryList = custCompanyBiz.getAreasInfo("allCountry", null);
        //客户类型
        custTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_CUST_TYPE);
        /**
         * 2017年1月5日17:09:07
         * Spole
         * Bug #1572
         * 产生原因：由于会员类型数据字典中包含了个人，所以导致会员开户中会出个人会员的现象。
         * 解决方法：移除加载数据字典时候，去掉会员类型中的个人。
         */
        for(int i=0;i<custTypeList.size();i++){
            OptionObjectPair oop = custTypeList.get(i);
            if(CorConstants.DATA_DICT__CORE_CUST_TYPE_PERSONAL.equals(oop.getKey())){//去掉会员类型中的个人。
                custTypeList.remove(oop);
            }
        }
        custTypeRender = SelectRenderUtils.toRenderString(custTypeList);
        //行业类型
        unitTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_GLOBEBILL_INDUSTRY);
        unitTypeRender = SelectRenderUtils.toRenderString(unitTypeList);
        
        //证件类型
        corporateCertTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_CERT_TYPE);
        corporateCertTypeRender = SelectRenderUtils.toRenderString(corporateCertTypeList);
        
        //审核状态
        statusTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_MERCHANT_AUDIT_STATUS);
        for(int i=0;i<statusTypeList.size();i++){
            OptionObjectPair oop = statusTypeList.get(i);
            if(CorConstants.DATA_DICT__CHECK_STATUS_RESUBMIT.equals(oop.getKey())){
                statusTypeList.remove(oop);
            }
        }
        statusTypeRender = SelectRenderUtils.toRenderString(statusTypeList);
        
        //会员状态
        custStatusTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_CUST_STATUS);
        custStatusTypeRender = SelectRenderUtils.toRenderString(custStatusTypeList);
        
        statusOperaList = new ArrayList<OptionObjectPair>();
        //
        for(OptionObjectPair entry : statusTypeList) {
            if(     !CorConstants.DATA_DICT__CHECK_STATUS_UNCHECK.equals(entry.getKey())&&
                    !CorConstants.DATA_DICT__CHECK_STATUS_RESUBMIT.equals(entry.getKey())
                    ){
                statusOperaList.add(entry); 
            }
        }
    }


    /**
     * 查询首页
     */
    public String list() {
        getDictRenders();
        return SUCCESS;
    }

    /**
     * 查询
     */
    public String search() {
        /**
         * 第一步，获取查询参数，并设置到queryPage
         */
        setQueryCondition();

        /**
         * 第二步，执行查询,并设置结果
         */
        Page<CustCompanyDto> result = (Page<CustCompanyDto>) custCompanyBiz.findCustCompanyDtosBySql(queryPage,
                custCompanyDto);

        /**
         * 第三步，返回
         */
        setResult(result);
        return AJAX_RETURN_TYPE;
    }

    // 设置查询参数
    private void setQueryCondition() {
        custCompanyDto = new CustCompanyDto();
        custCompanyDto.setCustCode(getSearchFields().get("custCode"));
        custCompanyDto.setLocalName(getSearchFields().get("localName"));
        custCompanyDto.setUnitType(getSearchFields().get("unitType"));
        custCompanyDto.setSocialCreditId(getSearchFields().get("socialCreditId"));
        custCompanyDto.setBusinessLicense(getSearchFields().get("businessLicense"));
        custCompanyDto.setStartCreateTime(getSearchFields().get("startCreateTime"));
        custCompanyDto.setEndCreateTime(getSearchFields().get("endCreateTime"));
        custCompanyDto.setStatus(getSearchFields().get("status"));
        custCompanyDto.setCustStatus(getSearchFields().get("custStatus"));

        logger.info("CustCompanyManagerAction.java---setQueryCondition() 查询参数：=====" + custCompanyDto.toString());
    }

    /**
     * 跳转到子页面
     */
    public String create() {
        getDictRenders(); // 加载数据字典。
        custCompanyDto = new CustCompanyDto();
        logger.info("CustCompanyManagerAction.java---create() this.getOperaType()" + this.getOperaType());
        if (CorConstants.OPERA_AUD.equals(operaType) || CorConstants.OPERA_INF.equals(operaType)
                || CorConstants.OPERA_UPD.equals(operaType)) {//
            if (null != id && id.length() > 0) {
                custCompanyDto = custCompanyBiz.getCustCompanyDtoById(id);
                //显示 凭证文件名称
                if(null != custCompanyDto &&null != custCompanyDto.getVoucher()&&custCompanyDto.getVoucher().length()>0 ){
                    FilePersistence pzfile = filePersistenceService.getFile(custCompanyDto.getVoucher());
                    fileFileName = pzfile.getFileName();
                }
            }
        }
        custCompanyDto.setOperaType(this.getOperaType());
        logger.info("CustCompanyManagerAction.java---create() custCompanyDto" + custCompanyDto);
        return SUCCESS;
    }

    /**
     * 操作 【新增，修改，审核，查看】 //opreaType : 0 -新增，1-查看，2-审核，3-修改
     */
    public String saveOrUpdate() {
        logger.info("CustCompanyManagerAction.java---saveOrUpdate() this.getOperaType()" + this.getOperaType() + "custCompanyDto=" + custCompanyDto);
        if (!StringUtils.isEmpty(operaType)) {
            if (null != file && file.length() > 0) {
                String voucherNum = null;
                try {
                    voucherNum = filePersistenceService.saveFile(file, fileFileName, fileContentType);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                custCompanyDto.setVoucher(voucherNum);
            }
            // 设置凭证信息和 操作类型
            custCompanyDto.setOperaType(operaType);
            custCompanyDto.setId(this.id);
            custCompanyDto.setUpdator(this.getUserName());
            logger.info("CustCompanyManagerAction.java---saveOrUpdate() custCompanyDto=" + custCompanyDto);
            custCompanyBiz.opera(custCompanyDto);
        }
        return SUCCESS;
    }

    @Override
    public String modify() {
        return SUCCESS;
    }

    @Override
    public String delete() {
        return SUCCESS;
    }

    @Override
    public String export() {
        return SUCCESS;
    }

    public String getAreasInfo() {
        logger.info("CustCompanyManagerAction.java---getAreasInfo() ---- Code = " + code);
        List<OptionObjectPair> provinceInfos = custCompanyBiz.getAreasInfo(idName, code);
        message = JSONArray.fromObject(provinceInfos).toString();
        logger.info("CustCompanyManagerAction.java---getAreasInfo() ---- message = " + message);
        return AJAX_RETURN_TYPE;
    }
    /**
     * 判断库中是否存在该企业数据
     * Y 存在客户信息， N 不存在
     * Creator		:	Spole
     * Date			:	2016年12月17日
     * Title		: 	CustCompanyManagerAction
     * return_type	:	String
     */
    public String existsInCustInfo() {
        logger.info("CustCompanyManagerAction.java---existsInCustInfo() ---- custCompanyDto = " + custCompanyDto);
        boolean flag = false;
        map = new HashMap<String, Object>();
        flag = custCompanyBiz.existsInCustInfo(custCompanyDto);
        if(flag){//true 存在客户信息  Y
            map.put("status", CorConstants.CORE_AJAX_RESULT_Y);
        }else{//false  不存在  N
            map.put("status", CorConstants.CORE_AJAX_RESULT_N);
        }
        logger.info("CustCompanyManagerAction.java---existsInCustInfo() ---- map = " + map);
        return AJAX_RETURN_TYPE;
    }
    /**
     * 黑名单校验
     * 
     * Y 客户信息存在黑名单中， N 不存在黑名单中
     * Creator		:	Spole
     * Date			:	2016年12月17日
     * Title		: 	CustCompanyManagerAction
     * return_type	:	String
     * @throws Exception 
     */
    public String existsInBlackList() throws Exception {
        logger.info("CustCompanyManagerAction.java---existsInBlackList() ---- custCompanyDto = " + custCompanyDto);
        
        // true : 存在黑名单中，false 不存在黑名单
        /**
         * 根据企业名称或者社会信用代码或者营业执照编码校验此会员是否在黑名单列表里，
         * 如果存在，提示“该企业在黑名单范围内，无法新建成功”,同时插入一条黑名单数据到会员黑名单管理表中。
         */
        boolean flag = false;
        map = new HashMap<String, Object>();
        try {
        } catch (Exception e) {
            logger.error("调用  风控校验   出现问题",e);
            throw new Exception(e);
        }
        if(flag){//true 存在客户信息  Y
            map.put("status", CorConstants.CORE_AJAX_RESULT_Y);
        }else{//false  不存在  N
            map.put("status", CorConstants.CORE_AJAX_RESULT_N);
        }
        logger.info("CustCompanyManagerAction.java---existsInBlackList() ---- map = " + map);
        return AJAX_RETURN_TYPE;
    }
    
    

    public String activeAccount() {
        logger.info("CustCompanyManagerAction.java---activeAccount() ---- id = " + id);
        map = new HashMap<String, Object>();
        boolean flag = false;
        if (null != id && id.length() > 0) {
            custCompanyDto = custCompanyBiz.getCustCompanyDtoById(id);
            custCompanyDto.setUpdator(getUserName());
            flag = custCompanyBiz.activeAccount(custCompanyDto);
        }
        if (flag) {
            map.put("status", CorConstants.CORE_AJAX_RESULT_Y);
        } else {
            map.put("status", CorConstants.CORE_AJAX_RESULT_N);
        }

        return AJAX_RETURN_TYPE;
    }

}
