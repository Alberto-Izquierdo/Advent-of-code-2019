import math


def load_list(path):
    list_loaded = []
    with open(path, "r") as my_file:
        line = my_file.readline()
        for char in line:
            if char != "\n":
                list_loaded.append(int(char))
    return list_loaded


def get_solution(input, number_of_phases):
    base_repeating_pattern = [0, 1, 0, -1]
    digits = len(input)
    for current_phase in range(1, number_of_phases + 1):
        solution = []
        for j in range(digits):
            repeating_pattern = []
            for i in range(digits):
                repeating_pattern.append(
                    base_repeating_pattern[int((i + 1) / (j + 1) % 4)]
                )
            partial_solution = 0
            for i in range(digits):
                partial_solution += input[i] * repeating_pattern[i]
            solution.append(abs(partial_solution) % 10)
        input = []
        for i in range(digits):
            input.append(solution[i])
    return solution


def print_eight_first_characters(solution_list):
    solution = 0
    for i in range(8):
        solution += (10 ** (7 - i)) * solution_list[i]
    print(solution)
