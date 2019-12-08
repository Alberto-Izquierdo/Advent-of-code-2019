import puzzle

def test_get_element_from_number():
    assert puzzle.get_element_from_number(153, 0) == 3
    assert puzzle.get_element_from_number(153, 1) == 5
    assert puzzle.get_element_from_number(153, 2) == 1
    assert puzzle.get_element_from_number(153, 3) == 0

def test_load_program_from_file():
    original_array = [1,40,30,50]
    array = puzzle.load_program_from_file("test_input")
    assert len(original_array) == len(array)
    for i in range(0, len(original_array)):
        assert array[i] == original_array[i]

def test_execute_program():
    assert 99 == puzzle.execute_program([99])

def test_addition():
    assert 100 == puzzle.execute_program([1, 0, 4, 0, 99])
    assert 40 == puzzle.execute_program([1101, 23, 17, 0, 99])

def test_multiplication():
    assert 198 == puzzle.execute_program([2, 0, 4, 0, 99])
    assert 46 == puzzle.execute_program([1102, 23, 2, 0, 99])

def test_jump_if_true():
    assert 5 == puzzle.execute_program([5, 1, 3, 4, 99])

def test_jump_if_false():
    assert 6 == puzzle.execute_program([6, 3, 4, 0, 99])

def test_less_than():
    assert 1 == puzzle.execute_program([7, 2, 0, 0, 99])
    assert 0 == puzzle.execute_program([7, 0, 1, 0, 99])
    assert 1 == puzzle.execute_program([1107, 0, 3, 0, 99])
    assert 0 == puzzle.execute_program([1107, 3, 3, 0, 99])

def test_equals():
    assert 1 == puzzle.execute_program([8, 2, 2, 0, 99])
    assert 0 == puzzle.execute_program([8, 2, 3, 0, 99])
    assert 1 == puzzle.execute_program([1108, 100, 100, 0, 99])
    assert 0 == puzzle.execute_program([1108, 101, 100, 0, 99])
