from com.jyp.rpi.gpio import ExternalCtrl

class PuttingDummy(ExternalCtrl):
    def __init__(self):
        print("[python] PuttingDummy constructor")

    def InitExternalDevice(self):
        print("[python] InitExternalDevice")
        return 0

    def setMotorValue(self, channel, percentage):
        print("[python] setMotorValue CH:{0}, Value:{1}".format(channel, percentage))
        
        return 0

    def setStateLED(self, state):
        if (state == 1):
          print("[python] STATE_LED - ON")
        elif (state == 0):
          print("[python] STATE_LED - OFF")
        else:
          print("[python] STATE_LED UNKNOWN!! : " , state)
        return 0
    
    def isError(self):
        print("[python] isNoProblem")
        return False
    
    def setBootDoneLedOn(self):
        print("[python] BOOT_DONE_LED - ON")
        return
    
    def setBootDoneLedOff(self):
       print("[python] BOOT_DONE_LED - OFF")
       return
