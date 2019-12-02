import math

def getFuelRequired(mass):
    return math.floor(mass / 3) - 2

input_file = open("input", "r")
result = 0
for value in input_file:
    result += getFuelRequired(int(value))
input_file.close()
print(result)
