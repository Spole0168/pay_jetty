@echo off & setlocal enabledelayedexpansion

set LIB_JARS="../ibs/web/ibs-core-common.jar;../ibs/protocol/*;../dubbo/*;../share/*"

java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -classpath ../ibs/service/ibs-core-certsmgr-service.jar;../ibs/web/ibs-core-common.jar;../ibs/web/ibs-core-basicdata.jar;%LIB_JARS% com.ibs.core.module.certsmgr.CertsMgrServiceProvider

pause