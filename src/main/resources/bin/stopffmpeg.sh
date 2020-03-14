#!/bin/sh

#根据进程名杀死进程

echo "This is a $call"

if [ $# -lt 2 ]

then
   echo "缺少参数：procedure_name和ip"
   exit 1

fi
echo "Kill the $1 process"

PROCESS=`ps -ef|grep $1|grep $2|grep -v grep|grep -v PPID|awk '{ print $2}'`

for i in $PROCESS

do
   echo "Kill the $1 process [ $i ]"

done