#!/bin/bash
javac -cp .:$MPJ_HOME/lib/mpj.jar $1.java
mpjrun.sh -np 2 $1