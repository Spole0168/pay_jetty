#!/bin/bash

echo "start basicdata ..."
./start-basicdata.sh

echo "sleep 5s"
sleep 5

echo "start certs ..."
./start-certs.sh

echo "sleep 5s"
sleep 5

echo "start account ..."
./start-account.sh

echo "sleep 5s"
sleep 5

echo "start product ..."
./start-product.sh

echo "sleep 5s"
sleep 5

echo "start risk ..."
./start-risk.sh

echo "sleep 5s"
sleep 5

echo "start customer ..."
./start-customer.sh

echo "sleep 5s"
sleep 5

echo "start fundchannel ..."
./start-fundchannel.sh

echo "sleep 5s"
sleep 5

echo "start payment ..."
./start-payment.sh

echo "sleep 3s"
sleep 3

echo "start certsmgr ..."
./start-certsmgr.sh

echo "sleep 5s"
sleep 5

echo "start callback ..."
./start-callback.sh

echo "all service started!"
