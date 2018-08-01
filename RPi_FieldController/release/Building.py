# 건물 개체를 생성하기 위하여 자바 인터페이스를
#  구현한 파이썬 모듈
import BuildingType

class Building(BuildingType):
    def __init__(self, name, address, id):
        self.name = name
        self.address = address
        self.id = id

    def getBuildingName(self):
        return self.name

    def getBuildingAddress(self):
        return self.address

    def getBuldingId(self):
        return self.id