@echo off & setlocal enabledelayedexpansion

set LIB_JARS="../ibs/web/ibs-core-common.jar;../ibs/protocol/*;../dubbo/*;../share/*"

java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ../ibs/service/ibs-core-fundchannel-service.jar;%LIB_JARS% com.ibs.core.module.fundchannel.FundChannelServiceProvider

pause