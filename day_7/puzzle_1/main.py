import puzzle

original_program = puzzle.load_program_from_file("input")
program = original_program[:]
my_result = []
previous_result = 0

for i_0 in range(0, 5):
    my_set = {i_0}
    for i_1 in range(0, 5):
        if i_1 in my_set:
            continue
        my_set.add(i_1)
        for i_2 in range(0, 5):
            if i_2 in my_set:
                continue
            my_set.add(i_2)
            for i_3 in range(0, 5):
                if i_3 in my_set:
                    continue
                my_set.add(i_3)
                for i_4 in range(0, 5):
                    if i_4 in my_set:
                        continue
                    result = puzzle.execute_program(program, [i_4, puzzle.execute_program(program, [i_3, puzzle.execute_program(program, [i_2, puzzle.execute_program(program, [i_1, puzzle.execute_program(program, [i_0, 0])])])])])
                    if result > previous_result:
                        previous_result = result
                        my_result = [i_0, i_1, i_2, i_3, i_4]
                my_set.remove(i_3)
            my_set.remove(i_2)
        my_set.remove(i_1)
print(previous_result)
print(my_result)
