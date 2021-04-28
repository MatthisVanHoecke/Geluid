from gpiozero import LED
from gpiozero.pins.pigpio import PiGPIOFactory
from time import sleep

factory = PiGPIOFactory(host='192.168.0.229')
led1 = LED(18, pin_factory=factory)
led2 = LED(23, pin_factory=factory)
led3 = LED(24, pin_factory=factory)

led1.on()
sleep(0.05)
led2.on()
sleep(0.05)
led3.on()
sleep(0.5)
led1.off()
sleep(0.05)
led2.off()
sleep(0.05)
led3.off()
