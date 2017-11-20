package com.ibs.core.module.customer.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.core.module.customer.biz.ICorMertBankAcntChgBiz;
import com.ibs.core.module.customer.biz.ICustBiz;
import com.ibs.core.module.customer.biz.IMerchantChgBiz;
import com.ibs.core.module.customer.biz.IMerchatBiz;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorMertBankAcntChg;
import com.ibs.core.module.customer.domain.MerchantChg;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.exception.ServiceException;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.SelectRenderUtils;
import com.ibs.portal.framework.util.StringUtils;

import net.sf.json.JSONArray;

public class MerchantManagementChgAction extends CrudBaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private MerchantDto merchantDto;
    private IMerchantChgBiz merchantChgBiz;
    private IMerchatBiz merchantBiz;
    private IDataDictService dataDictService;
    private MerchantChg merchantChg;
    private CorMertBankAcntChg corMertBankAcntChg;
    private ICorMertBankAcntChgBiz corMertBankAcntChgBiz;
    private ICustBiz custBiz;
    private IFilePersistenceService filePersistenceService;
    private String id;
    private String countryCode;
    private String provinceCode;
    private String cityCode;
    private String bankCode;
    private String operaType;
    private String custName;
    // 数据字典定义
    private static List<OptionObjectPair> countryList;
    // 行业类型
    private  static List<OptionObjectPair> industryList;
    private static String industryRender;
    // 审核状态 下拉选择 01-“待审核”，02-“审核失败”，03-“审核通过”
    private static List<OptionObjectPair> statusTypeList;
    private  static String statusTypeRender;
    // 商户风险等级
    private static List<OptionObjectPair> riskLevelList;
    private static String riskLevelRender;
    // 止入止出
    private static List<OptionObjectPair> isCheckinList;
    private static String isCheckinRender;
    private static List<OptionObjectPair> isCheckoutList;
    private static String isCheckoutRender;

    private static List<OptionObjectPair> statusOperaList;// 操作界面使用
    // 商户状态
    private static List<OptionObjectPair> mertStatusList;
    private static String mertStatusRender;
    // 上传相关定义
    private File file;
    private String fileFileName;
    private String fileContentType;
    // 存放异步查询的数据。
    private Map<String, Object> map;
    @Override
    public String create() {
        creatDict();
        countryList = merchantBiz.getCountryInfo();

        if (CorConstants.OPERA_AUD.equals(operaType) || CorConstants.OPERA_INF.equals(operaType)
                || CorConstants.OPERA_UPD.equals(operaType)) {//
            if (null != id && id.length() > 0) {
                merchantChg = merchantChgBiz.findMerchatChgById(id);
                corMertBankAcntChg = corMertBankAcntChgBiz.findCorMertBankAcntChg(id);
                merchantDto = merchantBiz.MerchantDtoByMerchantCode(id);
                CorCust cust = custBiz.getCustByCustCode(merchantChg.getCustCode());
                custName = cust.getLocalName();
            }
        }
        return SUCCESS;
    }

    @Override
    public String saveOrUpdate() {
        logger.info("entering action::MerchantManagementAction.search()");
        map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(operaType)) {

            if (null != file && file.length() > 0) {
                // path
//                String path = request.getContextPath();
                String voucherNum = null;
//                String voucherLocation = null;
                try {
                    voucherNum = filePersistenceService.saveFile(file, fileFileName, fileContentType);
//                    voucherLocation = path + "/downloadFile.action?fileId=" + voucherNum;
                } catch (Exception e) {
                    logger.error("Failed to upload voucher: ", e);
                    throw new ServiceException("Failed to upload voucher: ", e);
                }
                merchantChg.setVoutherUploadPath(voucherNum);
            }

            if (CorConstants.OPERA_UPD.equals(operaType)) {
                // 修改
                merchantChgBiz.updateMerchantInfo(merchantChg, corMertBankAcntChg, id, getUserName());
            } else {
                // 审核
                merchantChgBiz.review(merchantChg, merchantDto, id, getUserName());
            }
            map.put("exists", "Y");
        }
        return SUCCESS;
    }

    @Override
    public String modify() {
        return null;
    }

    @Override
    public String list() {
        creatDict();
        return SUCCESS;
    }

    @Override
    public String search() {
        merchantDto = new MerchantDto();
        merchantDto.setCustCode(getSearchFields().get("cust_code"));
        merchantDto.setCustName(getSearchFields().get("cust_name"));
        merchantDto.setMerchantName(getSearchFields().get("merchant_name"));
        merchantDto.setMerchantCode(getSearchFields().get("merchant_code"));
        merchantDto.setStatus(getSearchFields().get("status"));
        merchantDto.setStartDate(getSearchFields().get("createStartTime"));
        merchantDto.setEndDate(getSearchFields().get("createEndTime"));
        merchantDto.setRiskLevel(getSearchFields().get("risk_level"));
        merchantDto.setMertStatus(getSearchFields().get("mert_status"));
        Page<MerchantDto> result = (Page<MerchantDto>) merchantChgBiz.findMerchantChgListByPage(queryPage, merchantDto);
        setResult(result);
        return AJAX_RETURN_TYPE;
    }

    @Override
    public String delete() {
       
        message = "01";
        merchantChg = merchantChgBiz.findMerchatChgById(id);
        if(merchantChg!=null){
            
            merchantChgBiz.delete(id);
        }
        message = "03";
        
        return AJAX_RETURN_TYPE;
    }

    /**
     * 模拟企业类型数据字典
     */

    public void creatDict() {
        // 审核状态 下拉选择 01-“待审核”，02-“审核失败”，03-“审核通过”
//        statusTypeList = new ArrayList<OptionObjectPair>();
//        statusOperaList = new ArrayList<OptionObjectPair>();
//        riskLevelList = new ArrayList<OptionObjectPair>();
//        isCheckinList = new ArrayList<OptionObjectPair>();
//        isCheckoutList = new ArrayList<OptionObjectPair>();
//        industryList = new ArrayList<OptionObjectPair>();
//        mertStatusList = new ArrayList<OptionObjectPair>();
        if(null!=mertStatusList&&mertStatusList.size()>0){
            logger.info("getDictRenders()  会员管理模块 数据字典已经加载过了");
            return;
        }
        mertStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_MERT_STATUS_TYPE);
        mertStatusRender = SelectRenderUtils.toRenderString(mertStatusList);

        // 行业类型
        industryList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_GLOBEBILL_INDUSTRY);
        industryRender = SelectRenderUtils.toRenderString(industryList);

        // 状态
        statusTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_MERCHANT_AUDIT_STATUS);
        statusTypeRender = SelectRenderUtils.toRenderString(statusTypeList);
        statusOperaList = new ArrayList<OptionObjectPair>();
        for (OptionObjectPair entry : statusTypeList) {
            if (!CorConstants.DATA_DICT__CHECK_STATUS_UNCHECK.equals(entry.getKey()) && !CorConstants.DATA_DICT__CHECK_STATUS_RESUBMIT.equals(entry.getKey())) {
                statusOperaList.add(entry);
            }
        }
        // 行业等级
        riskLevelList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_RISK_LEVEL);
        riskLevelRender = SelectRenderUtils.toRenderString(riskLevelList);
        // 止入
        isCheckinList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_IS_OR_NO);
        isCheckinRender = SelectRenderUtils.toRenderString(isCheckinList);

        // 止出
        isCheckoutList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_IS_OR_NO);
        isCheckoutRender = SelectRenderUtils.toRenderString(isCheckoutList);

    }

    public String getProvince() {
        logger.info("entering action::MerchantManagementAction.getProvince() ---- countryCode = " + countryCode);
        List<OptionObjectPair> provinceInfos = merchantBiz.getProvinceInfo(countryCode);
        message = JSONArray.fromObject(provinceInfos).toString();
        logger.info("entering action::MerchantManagementAction.getProvince() ---- message = " + message);
        return AJAX_RETURN_TYPE;
    }

    public String getCity() {
        logger.info("entering action::MerchantManagementAction.getCity() ---- ProvinceCode = " + provinceCode);
        List<OptionObjectPair> cityInfos = merchantBiz.getCityInfo(provinceCode);
        message = JSONArray.fromObject(cityInfos).toString();
        logger.info("entering action::MerchantManagementAction.getCity() ---- message = " + message);
        return AJAX_RETURN_TYPE;
    }

    public String getBankName() {
        logger.info("entering action::MerchantManagementAction.getBankName() ---- cityCode = " + cityCode);
        List<OptionObjectPair> bankInfos = merchantBiz.getBankName(cityCode);
        message = JSONArray.fromObject(bankInfos).toString();
        logger.info("entering action::MerchantManagementAction.getBankName() ---- message = " + message);
        return AJAX_RETURN_TYPE;
    }

    public String getBankBranchName() {
        logger.info("entering action::MerchantManagementAction.getBankBranchName() ---- cityCode = " + cityCode
                + ",bankCode= " + bankCode);
        List<OptionObjectPair> bankBranchInfos = merchantBiz.getBankBranchName(bankCode, cityCode);
        message = JSONArray.fromObject(bankBranchInfos).toString();
        logger.info("entering action::MerchantManagementAction.getBankBranchName() ---- message = " + message);
        return AJAX_RETURN_TYPE;
    }

    @Override
    public String export() {
       
        return null;
    }

    public MerchantDto getMerchantDto() {
        return merchantDto;
    }

    public void setMerchantDto(MerchantDto merchantDto) {
        this.merchantDto = merchantDto;
    }

    public IMerchantChgBiz getMerchantChgBiz() {
        return merchantChgBiz;
    }

    public void setMerchantChgBiz(IMerchantChgBiz merchantChgBiz) {
        this.merchantChgBiz = merchantChgBiz;
    }

    public IMerchatBiz getMerchantBiz() {
        return merchantBiz;
    }

    public void setMerchantBiz(IMerchatBiz merchantBiz) {
        this.merchantBiz = merchantBiz;
    }

    public IDataDictService getDataDictService() {
        return dataDictService;
    }

    public void setDataDictService(IDataDictService dataDictService) {
        this.dataDictService = dataDictService;
    }

    public MerchantChg getMerchantChg() {
        return merchantChg;
    }

    public void setMerchantChg(MerchantChg merchantChg) {
        this.merchantChg = merchantChg;
    }

    public CorMertBankAcntChg getCorMertBankAcntChg() {
        return corMertBankAcntChg;
    }

    public void setCorMertBankAcntChg(CorMertBankAcntChg corMertBankAcntChg) {
        this.corMertBankAcntChg = corMertBankAcntChg;
    }

    public ICorMertBankAcntChgBiz getCorMertBankAcntChgBiz() {
        return corMertBankAcntChgBiz;
    }

    public void setCorMertBankAcntChgBiz(ICorMertBankAcntChgBiz corMertBankAcntChgBiz) {
        this.corMertBankAcntChgBiz = corMertBankAcntChgBiz;
    }

    public ICustBiz getCustBiz() {
        return custBiz;
    }

    public void setCustBiz(ICustBiz custBiz) {
        this.custBiz = custBiz;
    }

    public IFilePersistenceService getFilePersistenceService() {
        return filePersistenceService;
    }

    public void setFilePersistenceService(IFilePersistenceService filePersistenceService) {
        this.filePersistenceService = filePersistenceService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public static List<OptionObjectPair> getCountryList() {
        return countryList;
    }

    public static void setCountryList(List<OptionObjectPair> countryList) {
        MerchantManagementChgAction.countryList = countryList;
    }

    public static List<OptionObjectPair> getIndustryList() {
        return industryList;
    }

    public static void setIndustryList(List<OptionObjectPair> industryList) {
        MerchantManagementChgAction.industryList = industryList;
    }

  

    public static List<OptionObjectPair> getStatusTypeList() {
        return statusTypeList;
    }

    public static void setStatusTypeList(List<OptionObjectPair> statusTypeList) {
        MerchantManagementChgAction.statusTypeList = statusTypeList;
    }

    public static String getStatusTypeRender() {
        return statusTypeRender;
    }

    public static void setStatusTypeRender(String statusTypeRender) {
        MerchantManagementChgAction.statusTypeRender = statusTypeRender;
    }

    public static List<OptionObjectPair> getRiskLevelList() {
        return riskLevelList;
    }

    public static void setRiskLevelList(List<OptionObjectPair> riskLevelList) {
        MerchantManagementChgAction.riskLevelList = riskLevelList;
    }

    public static String getRiskLevelRender() {
        return riskLevelRender;
    }

    public static void setRiskLevelRender(String riskLevelRender) {
        MerchantManagementChgAction.riskLevelRender = riskLevelRender;
    }

    public static List<OptionObjectPair> getIsCheckinList() {
        return isCheckinList;
    }

    public static void setIsCheckinList(List<OptionObjectPair> isCheckinList) {
        MerchantManagementChgAction.isCheckinList = isCheckinList;
    }

    public static String getIsCheckinRender() {
        return isCheckinRender;
    }

    public static void setIsCheckinRender(String isCheckinRender) {
        MerchantManagementChgAction.isCheckinRender = isCheckinRender;
    }

    public static List<OptionObjectPair> getIsCheckoutList() {
        return isCheckoutList;
    }

    public static void setIsCheckoutList(List<OptionObjectPair> isCheckoutList) {
        MerchantManagementChgAction.isCheckoutList = isCheckoutList;
    }

    public static String getIsCheckoutRender() {
        return isCheckoutRender;
    }

    public static void setIsCheckoutRender(String isCheckoutRender) {
        MerchantManagementChgAction.isCheckoutRender = isCheckoutRender;
    }

    public static List<OptionObjectPair> getStatusOperaList() {
        return statusOperaList;
    }

    public static void setStatusOperaList(List<OptionObjectPair> statusOperaList) {
        MerchantManagementChgAction.statusOperaList = statusOperaList;
    }

    public static List<OptionObjectPair> getMertStatusList() {
        return mertStatusList;
    }

    public static void setMertStatusList(List<OptionObjectPair> mertStatusList) {
        MerchantManagementChgAction.mertStatusList = mertStatusList;
    }

    public static String getMertStatusRender() {
        return mertStatusRender;
    }

    public static void setMertStatusRender(String mertStatusRender) {
        MerchantManagementChgAction.mertStatusRender = mertStatusRender;
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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public static String getIndustryRender() {
        return industryRender;
    }

    public static void setIndustryRender(String industryRender) {
        MerchantManagementChgAction.industryRender = industryRender;
    }

   
    
}
