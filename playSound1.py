from playsound import playsound
import os

dir_path = os.path.dirname(os.path.realpath(__file__))
dir_path = dir_path.replace(os.sep, '/')
playsound(dir_path+'/beep.mp3')