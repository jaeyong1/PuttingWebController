#!/bin/sh

echo "-----------------------------"
echo "  Download Adafruit to Control Servo Motor"
echo "-----------------------------"
echo $(cd ~;git clone https://github.com/adafruit/Adafruit_Python_PCA9685.git)
echo ""
echo ""
echo "-----------------------------"
echo "  Install Adafruit"
echo "-----------------------------"
echo $(cd ~/Adafruit_Python_PCA9685;sudo python setup.py install)
