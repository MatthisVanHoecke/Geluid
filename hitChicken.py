from gpiozero import LED
from gpiozero import Buzzer
from gpiozero.pins.pigpio import PiGPIOFactory
from time import sleep

factory = PiGPIOFactory(host='10.30.100.93')
buzzer= Buzzer(18, pin_factory=factory)
led2 = LED(23, pin_factory=factory)
led3 = LED(24, pin_factory=factory)

buzzer.on()
sleep(0.05)
buzzer.off()
sleep(0.05)
buzzer.on()
sleep(0.05)
buzzer.off()
sleep(0.05)
buzzer.on()
sleep(0.05)
buzzer.off()

sleep(0.5)

buzzer.on()
sleep(0.5)
buzzer.off()
sleep(0.1)
buzzer.on()
sleep(0.5)
buzzer.off()
sleep(0.1)
buzzer.on()
sleep(0.5)
buzzer.off()

sleep(0.5)

buzzer.on()
sleep(0.05)
buzzer.off()
sleep(0.05)
buzzer.on()
sleep(0.05)
buzzer.off()
sleep(0.05)
buzzer.on()
sleep(0.05)
buzzer.off()



led2.on()
sleep(0.05)
led3.on()
sleep(0.5)
led2.off()
sleep(0.05)
led3.off()
