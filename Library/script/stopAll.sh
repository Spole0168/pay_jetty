#!/bin/bash

echo "stop basicdata ..."
PIDS=`ps -ef | grep java | grep "BasicDataServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop account ..."
PIDS=`ps -ef | grep java | grep "AccountServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop certs ..."
PIDS=`ps -ef | grep java | grep "CertsServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop certsmgr ..."
PIDS=`ps -ef | grep java | grep "CertsMgrServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop customer ..."
PIDS=`ps -ef | grep java | grep "CustomerServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop fundchannel ..."
PIDS=`ps -ef | grep java | grep "FundChannelServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop product ..."
PIDS=`ps -ef | grep java | grep "ProductServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop risk ..."
PIDS=`ps -ef | grep java | grep "RiskServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop payment ..."
PIDS=`ps -ef | grep java | grep "PaymentServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "stop callback ..."
PIDS=`ps -ef | grep java | grep "CallBackServiceProvider" | awk '{print $2}'`
kill -9 $PIDS

echo "all service stopped!"
