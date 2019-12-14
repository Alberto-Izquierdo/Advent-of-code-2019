import puzzle


def test_apply_gravity_to_moons():
    moons = [puzzle.Moon([1, 1, 1]), puzzle.Moon([-1, -1, -1])]
    puzzle.apply_gravity(moons)
    assert moons[0].gravity == [-1, -1, -1]
    assert moons[1].gravity == [1, 1, 1]
    for moon in moons:
        moon.apply_gravity()
    assert moons[0].gravity == [0, 0, 0]
    assert moons[1].gravity == [0, 0, 0]
    assert moons[0].velocity == [-1, -1, -1]
    assert moons[1].velocity == [1, 1, 1]
    for moon in moons:
        moon.apply_velocity()
    assert moons[0].position == [0, 0, 0]
    assert moons[1].position == [0, 0, 0]


def test_get_total_energy():
    moons = [puzzle.Moon([2, 2, 2]), puzzle.Moon([-2, -2, -2])]
    for moon in moons:
        moon.velocity = [x / abs(x) for x in moon.position]
    assert moons[0].get_total_energy() == 18
    assert moons[1].get_total_energy() == 18

    moons2 = [
        puzzle.Moon([8, 12, 9]),
        puzzle.Moon([13, 16, 3]),
        puzzle.Moon([29, 11, 1]),
        puzzle.Moon([16, 13, 23]),
    ]
    moons2[0].velocity = [7, 3, 0]
    moons2[1].velocity = [3, 11, 5]
    moons2[2].velocity = [3, 7, 4]
    moons2[3].velocity = [7, 1, 1]


def test_simulate_steps():
    moons = [puzzle.Moon([1, 1, 1]), puzzle.Moon([-1, -1, -1])]
    puzzle.simulate_steps(moons, 1)
    for moon in moons:
        assert moon.position == [0, 0, 0]
