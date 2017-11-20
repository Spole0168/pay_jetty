package com.ibs.core.module.customer.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.file.service.IFilePersistenceService;
import com.ibs.core.module.customer.biz.ICorMertBankAcntBiz;
import com.ibs.core.module.customer.biz.ICustBiz;
import com.ibs.core.module.customer.biz.IMerchantChgBiz;
import com.ibs.core.module.customer.biz.IMerchatBiz;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.core.module.customer.domain.Merchant;
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

public class MerchantManagementAction extends CrudBaseAction {

    private static final long serialVersionUID = 1L;

    private Merchant merchant;
    private MerchantDto merchantDto;
    private CorMertBankAcnt corMertBankAcnt;

    private IMerchatBiz merchantBiz;
    private ICustBiz custBiz;
    private ICorMertBankAcntBiz corMertBankAcntBiz;
    private IMerchantChgBiz merchantChgBiz;
    private IFilePersistenceService filePersistenceService;
    private IDataDictService dataDictService;

    private String id;
    private String reviewFlag;
    private String custCode;
    private String custName;
    private String status;
    private String reviewFailureReasons;
    private String countryCode;
    private String provinceCode;
    private String cityCode;
    private String bankCode;
    private String operaType;// 0 -新增，1-查看，2-审核，3-修改
    private String selectType;
    private boolean isModify;
    private String checkSelect;
    private String checkType;
    private String categoryCode;
    private String mccCode;
    // 上传相关定义
    private File file;
    private String fileFileName;
    private String fileContentType;
    // 数据字典定义
    private static List<OptionObjectPair> countryList;
    // 行业类型
    private static List<OptionObjectPair> industryList;
    private static String industryRender;
    // 审核状态 下拉选择 01-“待审核”，02-“审核失败”，03-“审核通过”
    private static List<OptionObjectPair> statusTypeList;
    private static String statusTypeRender;
    // 商户风险等级
    private static List<OptionObjectPair> riskLevelList;
    private static String riskLevelRender;
    // 止入止出
    private  static List<OptionObjectPair> isCheckinList;
    private static String isCheckinRender;
    private static List<OptionObjectPair> isCheckoutList;
    private static String isCheckoutRender;

    private static List<OptionObjectPair> statusOperaList;// 操作界面使用
//    商户准入界面查询条件使用
    private static List<OptionObjectPair> newStatusOperaList;// 操作界面使用
    
    // 商户状态
    private static List<OptionObjectPair> mertStatusList;
    private static String mertStatusRender;
    
    // 行业子类型
    private static List<OptionObjectPair> categoryTypeList;
    private static String categoryTypeRender;

    // 存放异步查询的数据。
    private Map<String, Object> map;

    @Override
    public String create() {
        creatDict();
        logger.info("entering action::MerchantManagementAction.create()");
        countryList = merchantBiz.getCountryInfo();
        merchantDto = new MerchantDto();
        corMertBankAcnt = new CorMertBankAcnt();
        merchant = new Merchant();
        if (CorConstants.OPERA_AUD.equals(operaType) || CorConstants.OPERA_INF.equals(operaType)
                || CorConstants.OPERA_UPD.equals(operaType)) {//
            if (null != id && id.length() > 0) {
                merchant = merchantBiz.findMerchatById(id);
                corMertBankAcnt = corMertBankAcntBiz.find(merchant.getMerchantCode());
                merchantDto = merchantBiz.MerchantDtoByMerchantCode(merchant.getMerchantCode());
                CorCust cust = custBiz.getCustByCustCode(merchant.getCustCode());
                custName = cust.getLocalName();
            }
        }
        merchantDto.setOperaType(this.operaType);
        return SUCCESS;
    }

    @Override
    public String modify() {
        return SUCCESS;
    }

    @Override
    public String saveOrUpdate() {
        logger.info("entering action::MerchantManagementAction.search()");
        map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(operaType)) {

            if (null != file && file.length() > 0) {
                // path
                String voucherNum = null;
                try {
                    voucherNum = filePersistenceService.saveFile(file, fileFileName, fileContentType);
                } catch (Exception e) {
                    logger.error("Failed to upload voucher: ", e);
                    throw new ServiceException("Failed to upload voucher: ", e);
                }
                merchant.setVoutherUploadPath(voucherNum);
            }

            if (CorConstants.OPERA_ADD.equals(operaType)) {
                // 新增
                merchantBiz.saveMerchantInfo(merchant, corMertBankAcnt, getUserName());
            } else if (CorConstants.OPERA_UPD.equals(operaType)) {
                // 修改
                merchantBiz.updateMerchantInfo(merchant, corMertBankAcnt, id, getUserName());
            } else {
                // 审核
                merchantBiz.review(merchant, merchantDto, id, getUserName());
            }
            map.put("exists", "Y");
        }
        return SUCCESS;
    }

    // 激活
    public String merchantActiveAccount() {
        logger.info("entering action::MerchantManagementAction.activeAccount()");
        message = "01";
        if (null != id && id.length() > 0) {
            merchant = merchantBiz.findMerchatById(id);

            merchantBiz.saveCorAccount(merchant, getUserName());
            message = "03";// 激活成功

        }

        return AJAX_RETURN_TYPE;
    }

    public String mertIscheck() {

        merchantDto = new MerchantDto();
        if (null != id && id.length() > 0) {
            merchantDto.setId(id);
            merchantDto.setCheckSelect(checkSelect);
            merchantDto.setCheckType(checkType);
            merchantBiz.mertIscheck(merchantDto);
            message = "01";

        }
        return AJAX_RETURN_TYPE;
    }

    public String applyChange() {
        message = "01";
        MerchantChg merchantChg = new MerchantChg();
        if (null != id && id.length() > 0) {
            merchant = merchantBiz.findMerchatById(id);
            corMertBankAcnt = corMertBankAcntBiz.find(merchant.getMerchantCode());
            merchantDto = merchantChgBiz.getMerchantChgAut(merchant.getMerchantCode());
            if (merchantDto != null) {
                message = "02";
            } else {
                merchantBiz.saveChange(merchant, corMertBankAcnt, getUserName());
                // 提交变更申请时，审核需要单独插入
                merchantChg = merchantChgBiz.getMerchantChg(merchant.getMerchantCode());
                if (merchantChg != null) {
                    merchantBiz.saveChangeBankAcct(merchantChg.getId(), corMertBankAcnt, getUserName());
                    merchantBiz.saveChangeAudit(merchantChg.getId(), getUserName());
                }
                message = "03";
            }

        }

        return AJAX_RETURN_TYPE;
    }

    @Override
    public String list() {
        // 加载数据字典
        creatDict();
        return SUCCESS;
    }

    /**
     * 模拟企业类型数据字典
     */

    public void creatDict() {
        if(null!=mertStatusList&&mertStatusList.size()>0){
            logger.info("getDictRenders() HHHHHHHHHHHH---已经加载过数据字典");
            return;
        }
        // 审核状态 下拉选择 01-“待审核”，02-“审核失败”，03-“审核通过”


        mertStatusList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_MERT_STATUS_TYPE);
        mertStatusRender = SelectRenderUtils.toRenderString(mertStatusList);
        // 行业子类型
        categoryTypeList= merchantBiz.getCategoryInfo(null,null);
        categoryTypeRender = SelectRenderUtils.toRenderString(categoryTypeList);
        // 行业类型
        industryList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_GLOBEBILL_INDUSTRY);
        industryRender = SelectRenderUtils.toRenderString(industryList);

        // 状态
        statusTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_MERCHANT_AUDIT_STATUS);
        statusTypeRender = SelectRenderUtils.toRenderString(statusTypeList);
        statusOperaList = new ArrayList<OptionObjectPair>();
        //商户准入界面查询条件
        newStatusOperaList = new ArrayList<OptionObjectPair>();
        for (OptionObjectPair entry : statusTypeList) {
            if (!CorConstants.DATA_DICT__CHECK_STATUS_RESUBMIT.equals(entry.getKey())) {
                newStatusOperaList.add(entry);
            }
        }
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

    @Override
    public String search() {
        creatDict();
        logger.info("entering action::MerchantManagementAction.search()");
        // 封装查询条件
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
        Page<MerchantDto> result = (Page<MerchantDto>) merchantBiz.findMerchantListByPage(queryPage, merchantDto);
        setResult(result);
        return AJAX_RETURN_TYPE;
    }

    @Override
    public String delete() {
       
        return null;
    }

    @Override
    public String export() {
        
        return null;
    }

    // 根据custCode获取会员名称
    public String findCustName() {
        logger.info("entering action::MerchantManagementAction.findCustName() ---- custCode = " + custCode);
        CorCust cust = custBiz.getCustByCustCode(custCode);
        
        if (cust == null||!CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID.equals(cust.getStatus())) {
            message = "";
        } else {
            MerchantDto md = new MerchantDto();
            md.setCustCode(cust.getCustCode());        
            merchantDto = merchantBiz.getMerchant(md);
            if(merchantDto==null){
                custName = cust.getLocalName();
                message = custName;              
            }else{
                message = "err";
            }
           
        }
        return AJAX_RETURN_TYPE;
    }
    
    public String getCategoryInfo(){
        // 行业子类型
        List<OptionObjectPair> categoryTypeInfos=merchantBiz.getCategoryInfo(categoryCode,mccCode);
        if(StringUtils.isNotEmpty(mccCode)){
            for (OptionObjectPair entry : categoryTypeInfos) {
                message=entry.getKey().toString();
            }
            
        }else{
            message = JSONArray.fromObject(categoryTypeInfos).toString(); 
        }
        
        return AJAX_RETURN_TYPE;
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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantDto getMerchantDto() {
        return merchantDto;
    }

    public void setMerchantDto(MerchantDto merchantDto) {
        this.merchantDto = merchantDto;
    }

    public CorMertBankAcnt getCorMertBankAcnt() {
        return corMertBankAcnt;
    }

    public void setCorMertBankAcnt(CorMertBankAcnt corMertBankAcnt) {
        this.corMertBankAcnt = corMertBankAcnt;
    }

    public IMerchatBiz getMerchantBiz() {
        return merchantBiz;
    }

    public void setMerchantBiz(IMerchatBiz merchantBiz) {
        this.merchantBiz = merchantBiz;
    }

    public ICustBiz getCustBiz() {
        return custBiz;
    }

    public void setCustBiz(ICustBiz custBiz) {
        this.custBiz = custBiz;
    }

    public ICorMertBankAcntBiz getCorMertBankAcntBiz() {
        return corMertBankAcntBiz;
    }

    public void setCorMertBankAcntBiz(ICorMertBankAcntBiz corMertBankAcntBiz) {
        this.corMertBankAcntBiz = corMertBankAcntBiz;
    }

    public IMerchantChgBiz getMerchantChgBiz() {
        return merchantChgBiz;
    }

    public void setMerchantChgBiz(IMerchantChgBiz merchantChgBiz) {
        this.merchantChgBiz = merchantChgBiz;
    }

    public IFilePersistenceService getFilePersistenceService() {
        return filePersistenceService;
    }

    public void setFilePersistenceService(IFilePersistenceService filePersistenceService) {
        this.filePersistenceService = filePersistenceService;
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

    public String getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(String reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReviewFailureReasons() {
        return reviewFailureReasons;
    }

    public void setReviewFailureReasons(String reviewFailureReasons) {
        this.reviewFailureReasons = reviewFailureReasons;
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

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public boolean isModify() {
        return isModify;
    }

    public void setModify(boolean isModify) {
        this.isModify = isModify;
    }

    public String getCheckSelect() {
        return checkSelect;
    }

    public void setCheckSelect(String checkSelect) {
        this.checkSelect = checkSelect;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
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

    public static List<OptionObjectPair> getCountryList() {
        return countryList;
    }

    public static void setCountryList(List<OptionObjectPair> countryList) {
        MerchantManagementAction.countryList = countryList;
    }

    public static List<OptionObjectPair> getIndustryList() {
        return industryList;
    }

    public static void setIndustryList(List<OptionObjectPair> industryList) {
        MerchantManagementAction.industryList = industryList;
    }

    public static String getIndustryRender() {
        return industryRender;
    }

    public static void setIndustryRender(String industryRender) {
        MerchantManagementAction.industryRender = industryRender;
    }

    public static List<OptionObjectPair> getStatusTypeList() {
        return statusTypeList;
    }

    public static void setStatusTypeList(List<OptionObjectPair> statusTypeList) {
        MerchantManagementAction.statusTypeList = statusTypeList;
    }

    public static String getStatusTypeRender() {
        return statusTypeRender;
    }

    public static void setStatusTypeRender(String statusTypeRender) {
        MerchantManagementAction.statusTypeRender = statusTypeRender;
    }

    public static List<OptionObjectPair> getRiskLevelList() {
        return riskLevelList;
    }

    public static void setRiskLevelList(List<OptionObjectPair> riskLevelList) {
        MerchantManagementAction.riskLevelList = riskLevelList;
    }

    public static String getRiskLevelRender() {
        return riskLevelRender;
    }

    public static void setRiskLevelRender(String riskLevelRender) {
        MerchantManagementAction.riskLevelRender = riskLevelRender;
    }

    public static List<OptionObjectPair> getIsCheckinList() {
        return isCheckinList;
    }

    public static void setIsCheckinList(List<OptionObjectPair> isCheckinList) {
        MerchantManagementAction.isCheckinList = isCheckinList;
    }

    public static String getIsCheckinRender() {
        return isCheckinRender;
    }

    public static void setIsCheckinRender(String isCheckinRender) {
        MerchantManagementAction.isCheckinRender = isCheckinRender;
    }

    public static List<OptionObjectPair> getIsCheckoutList() {
        return isCheckoutList;
    }

    public static void setIsCheckoutList(List<OptionObjectPair> isCheckoutList) {
        MerchantManagementAction.isCheckoutList = isCheckoutList;
    }

    public static String getIsCheckoutRender() {
        return isCheckoutRender;
    }

    public static void setIsCheckoutRender(String isCheckoutRender) {
        MerchantManagementAction.isCheckoutRender = isCheckoutRender;
    }

    public static List<OptionObjectPair> getStatusOperaList() {
        return statusOperaList;
    }

    public static void setStatusOperaList(List<OptionObjectPair> statusOperaList) {
        MerchantManagementAction.statusOperaList = statusOperaList;
    }

    public static List<OptionObjectPair> getNewStatusOperaList() {
        return newStatusOperaList;
    }

    public static void setNewStatusOperaList(List<OptionObjectPair> newStatusOperaList) {
        MerchantManagementAction.newStatusOperaList = newStatusOperaList;
    }

    public static List<OptionObjectPair> getMertStatusList() {
        return mertStatusList;
    }

    public static void setMertStatusList(List<OptionObjectPair> mertStatusList) {
        MerchantManagementAction.mertStatusList = mertStatusList;
    }

    public static String getMertStatusRender() {
        return mertStatusRender;
    }

    public static void setMertStatusRender(String mertStatusRender) {
        MerchantManagementAction.mertStatusRender = mertStatusRender;
    }

    public static List<OptionObjectPair> getCategoryTypeList() {
        return categoryTypeList;
    }

    public static void setCategoryTypeList(List<OptionObjectPair> categoryTypeList) {
        MerchantManagementAction.categoryTypeList = categoryTypeList;
    }

    public static String getCategoryTypeRender() {
        return categoryTypeRender;
    }

    public static void setCategoryTypeRender(String categoryTypeRender) {
        MerchantManagementAction.categoryTypeRender = categoryTypeRender;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

   

        
}
