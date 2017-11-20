<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/metas.jsp"%>
 

<div class="layout">
    <div class="block m-b">
        <div class="block_title">
            <h3>常见问题</h3>
        </div>
 
    <div  class="block_container">
	 <ul>
     <li><h3>1.接口数据</h3></li>
     <li><span>Q：为什么查不到任何数据？  </span></li>					
	 <li><span>A：首先查询时间都是默认为当天的，可以将开始时间调整为数日之前；其次时间类型请选择报文接收时间   </span></li>
	 <li><span>Q：为什么查询接口数据的明细，显示的都是程序代码？</span></li>					
	 <li><span>A：明细中显示的是报文，是正常的报文格式</span></li>					
	 <li><span>Q：为什么查询接口数据的明细，没有任何信息？	</span></li>					
	 <li><span>A：没有信息的数据是以前的脏数据，目前基本上已清除干净</span></li>
	  
	 <li><h3>2.登陆问题</h3></li>
     <li><span>Q：为什么系统经常无法登录，验证码报错？  </span></li>					
	 <li><span>A：环境内存配置太小，请等待片刻，重新尝试登录</span></li>
	
	 
	 <li><h3>3.交易流水</h3></li>
     <li><span>Q：为什么查询不到失败的交易流水？  </span></li>					
	 <li><span>A：只有成功的数据才会进入交易流水，失败的数据可以在渠道业务跟踪中查询到</span></li>
	 
	 
	  
	 <li><h3>4退款</h3></li>
     <li><span>Q：为什么退款复核通过后，总账、明细账金额没有减去退款金额？	</span></li>					
	 <li><span>A：退款流程只发生在充值卡非绑定银行卡的情况，改笔金额不会记录，故总账、明细账不会发生变化</span></li>
	 
	 <li><h3>5.充值</h3></li>
     <li><span>Q：入金金额与实际金额不相符走什么流程？自动审核失败，这一环节如何自动实现？	</span></li>					
	 <li><span>A：这个环节是手动实现的，人工审核失败，在审核失败后，支付平台会调通知接口，通知交易平台该比入金失败，及失败原因是与实际入账金额不符，要求交易平台重新发送一笔与实际金额相符的入金申请</span></li>
	 
	 <li><h3>6.清结算</h3></li>
     <li><span>Q：清结算成功后， 增加清结算报文中的记录明细账在哪？</span></li>					
	 <li><span>A：明细账查询</span></li>
	 
      
    </ul>
  
<br/>
 
 

<a class="easyui-linkbutton l-btn" href="#"  onclick="window.history.go(-1)"><span class="l-btn-left"><span class="l-btn-text icon-undo">返回</span></span></a>
 
 </div>
</div></div>