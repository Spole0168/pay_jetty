@echo off & setlocal enabledelayedexpansion

set LIB_JARS="../ibs/web/ibs-core-common.jar;../ibs/protocol/*;../dubbo/*;../share/*;../mq/*"

java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ../ibs/service/ibs-core-payment-service.jar;../ibs/web/ibs-core-account.jar;../ibs/web/ibs-core-common.jar;../ibs/web/ibs-core-risk.jar;../ibs/web/ibs-core-basicdata.jar;../ibs/web/ibs-core-fundchannel.jar;%LIB_JARS% com.ibs.core.module.payment.PaymentServiceProvider

pause