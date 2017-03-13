from cscore import CameraServer

# TODO: put actual target tracking code here

def main():
    cs = CameraServer.getInstance()
    cs.enableLogging()
    
    cs.startAutomaticCapture()
    cs.waitForever()
