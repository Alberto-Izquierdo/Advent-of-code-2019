import puzzle


def test_input():
    assert [4, 8, 2, 2, 6, 1, 5, 8] == puzzle.get_solution([1, 2, 3, 4, 5, 6, 7, 8], 1)
    assert [3, 4, 0, 4, 0, 4, 3, 8] == puzzle.get_solution([1, 2, 3, 4, 5, 6, 7, 8], 2)
    assert [0, 3, 4, 1, 5, 5, 1, 8] == puzzle.get_solution([1, 2, 3, 4, 5, 6, 7, 8], 3)
    assert [0, 1, 0, 2, 9, 4, 9, 8] == puzzle.get_solution([1, 2, 3, 4, 5, 6, 7, 8], 4)


def test_larger_inputs():
    solution_list = puzzle.get_solution(
        [
            8,
            0,
            8,
            7,
            1,
            2,
            2,
            4,
            5,
            8,
            5,
            9,
            1,
            4,
            5,
            4,
            6,
            6,
            1,
            9,
            0,
            8,
            3,
            2,
            1,
            8,
            6,
            4,
            5,
            5,
            9,
            5,
        ],
        100,
    )
    solution = 0
    for i in range(8):
        solution += (10 ** (7 - i)) * solution_list[i]
    assert 24176176 == solution
