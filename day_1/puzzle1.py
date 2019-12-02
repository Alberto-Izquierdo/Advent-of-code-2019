import math

def getFuelRequired(mass):
    result = math.floor(mass / 3) - 2
    if result > 0:
        result += getFuelRequired(result)
    else:
        result = 0
    return result

input_file = open("input", "r")
result = 0
for value in input_file:
    result += getFuelRequired(int(value))
input_file.close()
print(result)
