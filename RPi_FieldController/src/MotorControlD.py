#! /usr/bin/env python

# Simple demo of of the PCA9685 PWM servo/LED controller library.
# This will move channel 0 from min to max position repeatedly.
# Author: Tony DiCola
# License: Public Domain
from __future__ import division
import time

# Import the PCA9685 module.
import Adafruit_PCA9685

import socket
import threading
import RPi.GPIO as GPIO
import time

# TCP server setting
bind_ip = '0.0.0.0'
bind_port = 9999
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((bind_ip, bind_port))
server.listen(5)  # max backlog of connections

print 'Listening on {}:{}'.format(bind_ip, bind_port)

# GPIO setting
STATE_LED_GPIO = 18
BOOTDONE_LED_GPIO = 23
GPIO.setmode(GPIO.BCM)
GPIO.setup(STATE_LED_GPIO, GPIO.OUT)
GPIO.setup(BOOTDONE_LED_GPIO, GPIO.OUT)
GPIO.output(STATE_LED_GPIO, False)
GPIO.output(BOOTDONE_LED_GPIO, False)

# Uncomment to enable debug output.
# import logging
# logging.basicConfig(level=logging.DEBUG)

# Initialise the PCA9685 using the default address (0x40).
pwm = Adafruit_PCA9685.PCA9685()

# Alternatively specify a different address and/or bus:
# pwm = Adafruit_PCA9685.PCA9685(address=0x41, busnum=2)

# Configure min and max servo pulse lengths
servo_min = 150  # Min pulse length out of 4096
servo_max = 600  # Max pulse length out of 4096

# Helper function to make setting a servo pulse width simpler.
def set_servo_pulse(channel, pulse):
    pulse_length = 1000000  # 1,000,000 us per second
    pulse_length //= 60  # 60 Hz
    print('{0}us per period'.format(pulse_length))
    pulse_length //= 4096  # 12 bits of resolution
    print('{0}us per bit'.format(pulse_length))
    pulse *= 1000
    pulse //= pulse_length
    pwm.set_pwm(channel, 0, pulse)

# Set frequency to 60hz, good for servos.
pwm.set_pwm_freq(60)

print('Moving servo on channel 0, press Ctrl-C to quit...')

def handle_client_connection(client_socket):
    request = client_socket.recv(1024)
    print 'Received <{}>'.format(request)
    client_socket.send('ACK!')
    client_socket.close()
    arr = request.split(' ')
    print("len:", len(arr))
    if(len(arr) == 3 and arr[0] == 'motor'):
        print arr[1]
        print arr[2]
        # db(0~100) -> pwm(150~600)
        angle = int(arr[2]) * 4.5 + 150
        pwm.set_pwm(int(arr[1]), 0, int(angle))
        
    if(len(arr) == 2 and arr[0] == 'stateled'):
        if(arr[1] == 'on' or arr[1] == '1'):
            GPIO.output(STATE_LED_GPIO, True)
        if(arr[1] == 'off' or arr[1] == '0'):
            GPIO.output(STATE_LED_GPIO, False)

    if(len(arr) == 2 and arr[0] == 'bootdoneled'):
        if(arr[1] == 'on' or arr[1] == '1'):
            GPIO.output(BOOTDONE_LED_GPIO, True)
        if(arr[1] == 'off' or arr[1] == '0'):
            GPIO.output(BOOTDONE_LED_GPIO, False)

    if(len(arr) == 1 and arr[0] == 'all_zero'):
        pass
    
    


while True:
    client_sock, address = server.accept()
    print 'Accepted connection from {}:{}'.format(address[0], address[1])
    client_handler = threading.Thread(
        target=handle_client_connection,
        args=(client_sock,)  # without comma you'd get a... TypeError: handle_client_connection() argument after * must be a sequence, not _socketobject
    )
    client_handler.start()
