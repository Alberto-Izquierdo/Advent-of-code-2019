import math

def is_current_number_correct(number):
    consecutive_equal_numbers = 0
    previous_number = 0
    for index in range(5, -1, -1):
        exponent = 10 ** index
        current_number = math.floor(number / exponent % 10)
        if current_number < previous_number:
            return False
        elif current_number == previous_number:
            consecutive_equal_numbers += 1
        previous_number = current_number
    if consecutive_equal_numbers > 0:
        return True
    return False


def get_possible_passwords(min_number, max_number):
    result = 0
    for current_number in range(min_number, max_number):
        if is_current_number_correct(current_number):
            result += 1
    return result


print(get_possible_passwords(372037, 905157))
