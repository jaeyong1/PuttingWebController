from com.jyp.rpi.gpio import ExternalCtrl

class PuttingI2C(ExternalCtrl):
    def __init__(self):
        print("[python] PuttingI2C constructor")

    def InitExternalDevice(self):
        print("[python] InitExternalDevice")
        return 0

    def setMotorValue(self, channel, percentage):
        print("[python] setMotorValue")
        
        return 0

    def setStateLED(self, state):
        print("[python] setStateLED")
        return 0
    
    def isError(self):
        print("[python] isNoProblem")
        return False
    
    def setBootDoneLedOn(self):
        return
    
    def setBootDoneLedOff(self):
        return
