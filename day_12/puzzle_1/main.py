import puzzle

moons = puzzle.load_moons("input")
puzzle.simulate_steps(moons, 1000)
total_energy = 0
puzzle.print_moons_data(moons)
for moon in moons:
    total_energy += moon.get_total_energy()
print("Total energy: ", total_energy)
