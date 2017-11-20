package com.ibs.core.module.customer.action;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.basefunc.service.IEmailService;
import com.ibs.core.module.basefunc.service.impl.EmailServiceImpl;
import com.ibs.core.module.customer.biz.ICorMertUserBiz;
import com.ibs.core.module.customer.biz.IMerchatBiz;
import com.ibs.core.module.customer.domain.CorMertUser;
import com.ibs.core.module.customer.dto.CorMertUserDto;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.core.module.mdmbasedata.service.IDataDictService;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.util.Base64Util;
import com.ibs.portal.framework.util.Md5Encoder;
import com.ibs.portal.framework.util.SelectRenderUtils;

public class CorMertUserManagementAction extends CrudBaseAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private CorMertUserDto corMertUserDto;
    private MerchantDto merchantDto;
    private CorMertUser corMertUser;

    private ICorMertUserBiz corMertUserBiz;
    private IMerchatBiz merchantBiz;
    private IDataDictService dataDictService;

    private ICodeRuleService codeRuleService;
    private IEmailService emailService;
    // 证件类型
    private List<OptionObjectPair> corporateCertTypeList;
    private String corporateCertTypeRender;
    private String id;
    private List<OptionObjectPair> mertUserStatusList;
    private String mertUserStatusRender;

    @Override
    public String create() {
        corMertUserDto = new CorMertUserDto();
        if (null != id && id.length() > 0) {
            corMertUser = corMertUserBiz.getCorMertUserById(id);
            MerchantDto md = new MerchantDto();
            merchantDto = merchantBiz.getMerchant(md);
        }
        return SUCCESS;
    }

    @Override
    public String modify() {
        return null;
    }

    @Override
    public String saveOrUpdate() {
        return null;
    }

    @Override
    public String list() {
        creatDict();
        return SUCCESS;
    }

    @Override
    public String search() {

        logger.info("entering action::CorMertUserManagementAction.search()");
        // 封装查询条件
        corMertUserDto = new CorMertUserDto();
        corMertUserDto.setCustCode(getSearchFields().get("cust_code"));
        corMertUserDto.setCustName(getSearchFields().get("cust_name"));
        corMertUserDto.setMerchantName(getSearchFields().get("merchant_name"));
        corMertUserDto.setMerchantCode(getSearchFields().get("merchant_code"));
        corMertUserDto.setTimeType(getSearchFields().get("timeType"));
        corMertUserDto.setStartDate(getSearchFields().get("createStartTime"));
        corMertUserDto.setEndDate(getSearchFields().get("createEndTime"));

        Page<CorMertUserDto> result = (Page<CorMertUserDto>) corMertUserBiz.findCorMertUserByPage(queryPage,
                corMertUserDto);
        setResult(result);
        return AJAX_RETURN_TYPE;

    }

    public void creatDict() {
        corporateCertTypeList = new ArrayList<OptionObjectPair>();
        mertUserStatusList = new ArrayList<OptionObjectPair>();

        // 证件类型
        corporateCertTypeList = dataDictService.listOptions(IDataDictService.DATA_DICT_TYPE__CORE_CERT_TYPE);
        corporateCertTypeRender = SelectRenderUtils.toRenderString(corporateCertTypeList);

        mertUserStatusList
                .add(new OptionObjectPair(CorConstants.MERT_USER_STATUS_01, CorConstants.MERT_USER_STATUS_01_VALUE));
        mertUserStatusList
                .add(new OptionObjectPair(CorConstants.MERT_USER_STATUS_02, CorConstants.MERT_USER_STATUS_02_VALUE));
        mertUserStatusRender = SelectRenderUtils.toRenderString(mertUserStatusList);
    }

    public String sendUserPwdEmail() throws IOException {
        // 发送逻辑
        String password = codeRuleService.getCorCustLoginPwd();
        corMertUser = corMertUserBiz.getCorMertUserById(id);
        corMertUser.setPassword(new Md5Encoder().encode(password));
        corMertUser.setUpdator(this.getUserName());
        corMertUser.setUpdateTime(new Date());
        corMertUser.setPwdUpdateTime(new Date());
        corMertUserBiz.updateCorMertUser(corMertUser);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String updateTime = sdf.format(new Date());
        String parameter = "userName=" + corMertUser.getCustCode() + "&password=" + password + "&updateTime="
                + updateTime; 
        String encodeUrl = Base64Util.encode(parameter.getBytes("UTF-8"));
        InputStream in = EmailServiceImpl.class.getResourceAsStream("/application.properties");
        Properties properties = new Properties();
        properties.load(in);
        String link = properties.getProperty("reset.password");
        String url = link+ "tempCode=" + encodeUrl;
        String content = "链接有效期为10分钟，请点击尽快修改："+url;

//        String content = "userName:=" + corMertUser.getUserCode() + "password:=" + password;
        emailService.sendEmail(corMertUser.getEmail(), "重置用户密码", content, null);

        return SUCCESS;
    }

    @Override
    public String delete() {

        return null;
    }

    @Override
    public String export() {

        return null;
    }

    public CorMertUserDto getCorMertUserDto() {
        return corMertUserDto;
    }

    public void setCorMertUserDto(CorMertUserDto corMertUserDto) {
        this.corMertUserDto = corMertUserDto;
    }

    public ICorMertUserBiz getCorMertUserBiz() {
        return corMertUserBiz;
    }

    public void setCorMertUserBiz(ICorMertUserBiz corMertUserBiz) {
        this.corMertUserBiz = corMertUserBiz;
    }

    public IDataDictService getDataDictService() {
        return dataDictService;
    }

    public void setDataDictService(IDataDictService dataDictService) {
        this.dataDictService = dataDictService;
    }

    public List<OptionObjectPair> getCorporateCertTypeList() {
        return corporateCertTypeList;
    }

    public void setCorporateCertTypeList(List<OptionObjectPair> corporateCertTypeList) {
        this.corporateCertTypeList = corporateCertTypeList;
    }

    public String getCorporateCertTypeRender() {
        return corporateCertTypeRender;
    }

    public void setCorporateCertTypeRender(String corporateCertTypeRender) {
        this.corporateCertTypeRender = corporateCertTypeRender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CorMertUser getCorMertUser() {
        return corMertUser;
    }

    public void setCorMertUser(CorMertUser corMertUser) {
        this.corMertUser = corMertUser;
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public IEmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(IEmailService emailService) {
        this.emailService = emailService;
    }

    public List<OptionObjectPair> getMertUserStatusList() {
        return mertUserStatusList;
    }

    public void setMertUserStatusList(List<OptionObjectPair> mertUserStatusList) {
        this.mertUserStatusList = mertUserStatusList;
    }

    public String getMertUserStatusRender() {
        return mertUserStatusRender;
    }

    public void setMertUserStatusRender(String mertUserStatusRender) {
        this.mertUserStatusRender = mertUserStatusRender;
    }

    public IMerchatBiz getMerchantBiz() {
        return merchantBiz;
    }

    public void setMerchantBiz(IMerchatBiz merchantBiz) {
        this.merchantBiz = merchantBiz;
    }

    public MerchantDto getMerchantDto() {
        return merchantDto;
    }

    public void setMerchantDto(MerchantDto merchantDto) {
        this.merchantDto = merchantDto;
    }

}
