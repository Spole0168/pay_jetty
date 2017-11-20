#!/bin/bash

LOGS_DIR=../logs
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/customer.log

LIB_DIR=/opt/core_lib
PROTOCOL_DIR=/opt/service/ibs/protocol
#LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`
LIB_JARS="$LIB_DIR/*:$PROTOCOL_DIR/*"

JAVA_OPTS=" -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true "

JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xmx2g -Xms2g -Xmn256m -XX:PermSize=128m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "
else
    JAVA_MEM_OPTS=" -server -Xms1g -Xmx2g -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

echo "$JAVA_HOME/bin/java $JAVA_OPTS $JAVA_MEM_OPTS  -classpath ../ibs/service/ibs-core-customer-service.jar:../ibs/web/ibs-core-common.jar:$LIB_JARS com.ibs.core.module.customer.CustomerServiceProvider> $STDOUT_FILE 2>&1 &"
$JAVA_HOME/bin/java $JAVA_OPTS $JAVA_MEM_OPTS  -classpath ../ibs/service/ibs-core-customer-service.jar:../ibs/web/ibs-core-common.jar:$LIB_JARS com.ibs.core.module.customer.CustomerServiceProvider> $STDOUT_FILE 2>&1 &


echo "OK!"
PIDS=`ps -f | grep java | grep "CustomerServiceProvider" | awk '{print $2}'`
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"
