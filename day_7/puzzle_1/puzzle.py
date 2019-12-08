import math
import sys

def get_element_from_number(number, index):
    return math.floor((number / 10 ** index) % 10)

def load_program_from_file(path):
    array = []
    with open(path, "r") as my_file:
        array = list(map(int, my_file.read().split(",")))
    return array

def execute_program(program, input_3):
    index = 0
    input_index = 0
    while index < len(program):
        operator = program[index] % 100
        if operator == 99:
            return program[0]
        else:
            first_parameter_mode = get_element_from_number(program[index], 2)
            first_parameter = program[index + 1]
            if first_parameter_mode == 0:
                first_parameter = program[program[index + 1]]
            if operator == 3:
                program[program[index + 1]] = input_3[input_index]
                input_index += 1
                index += 2
            elif operator == 4:
                return first_parameter
                index += 2
            else:
                second_parameter_mode = get_element_from_number(program[index], 3)
                second_parameter = program[index + 2]
                if second_parameter_mode == 0:
                    second_parameter = program[program[index + 2]]
                third_parameter = index + 3
                if operator == 1:
                    program[program[third_parameter]] = first_parameter + second_parameter
                    index += 4
                elif operator == 2:
                    program[program[third_parameter]] = first_parameter * second_parameter
                    index += 4
                elif operator == 5:
                    if first_parameter != 0:
                        index = second_parameter
                    else:
                        index += 3
                elif operator == 6:
                    if first_parameter == 0:
                        index = second_parameter
                    else:
                        index += 3
                elif operator == 7:
                    if first_parameter < second_parameter:
                        program[program[index + 3]] = 1
                    else:
                        program[program[index + 3]] = 0
                    index += 4
                elif operator == 8:
                    if first_parameter == second_parameter:
                        program[program[index + 3]] = 1
                    else:
                        program[program[index + 3]] = 0
                    index += 4
                else:
                    return -1
    return program[0]
