from operator import add


class Moon:
    def __init__(self, initial_position):
        self.position = initial_position
        self.velocity = [0, 0, 0]
        self.gravity = [0, 0, 0]

    def apply_gravity(self):
        self.velocity = list(map(add, self.velocity, self.gravity))
        self.gravity = [0, 0, 0]

    def apply_velocity(self):
        self.position = list(map(add, self.position, self.velocity))

    def get_total_energy(self):
        potential_energy = sum([abs(x) for x in self.position])
        kinetic_energy = sum([abs(x) for x in self.velocity])
        return potential_energy * kinetic_energy


def simulate_steps(moons, number_of_steps):
    for i in range(number_of_steps):
        apply_gravity(moons)
        for moon in moons:
            moon.apply_gravity()
            moon.apply_velocity()


def apply_gravity(moons):
    for i in range(len(moons) - 1):
        for j in range(i + 1, len(moons)):
            pos1 = moons[i].position
            pos2 = moons[j].position
            difference = [pos2[0] - pos1[0], pos2[1] - pos1[1], pos2[2] - pos1[2]]
            gravity_to_apply = [
                0 if difference[0] == 0 else difference[0] / abs(difference[0]),
                0 if difference[1] == 0 else difference[1] / abs(difference[1]),
                0 if difference[2] == 0 else difference[2] / abs(difference[2]),
            ]
            moons[i].gravity = list(map(add, moons[i].gravity, gravity_to_apply))
            gravity_to_apply = [x * -1 for x in gravity_to_apply]
            moons[j].gravity = list(map(add, moons[j].gravity, gravity_to_apply))


def load_moons(path):
    moons = []
    with open(path, "r") as my_file:
        line = my_file.readline()
        while line:
            line = line[1:-1]
            data = line.split(" ")
            moon_position = []
            for value in range(len(data)):
                moon_position.append((int)(data[value][2:-1]))
            moons.append(Moon(moon_position))
            line = my_file.readline()
    return moons


def print_moons_data(moons_positions):
    for i in range(len(moons_positions)):
        print(
            "Positions = x: {}, y: {}, z: {}".format(
                moons_positions[i].position[0],
                moons_positions[i].position[1],
                moons_positions[i].position[2],
            )
        )
        print(
            "Velocities = x: {}, y: {}, z: {}".format(
                moons_positions[i].velocity[0],
                moons_positions[i].velocity[1],
                moons_positions[i].velocity[2],
            )
        )
