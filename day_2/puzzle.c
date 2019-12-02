#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
  FILE *input_file;
  int SIZE = 1024;
  char buffer[SIZE];
  size_t nread;

  // Read file
  input_file = fopen("input", "r");
  if (input_file) {
    nread = fread(buffer, 1, sizeof buffer, input_file);
    fclose(input_file);
  }

  // Count the number of integers to create the array
  int index;
  int array_size = 1;
  int buffer_size = strlen(buffer);
  for (index = 0; index < buffer_size; ++index) {
    if (buffer[index] == ',') {
      ++array_size;
    }
  }
  int array[array_size];

  // Convert the strings into integers
  int starting_position_number = 0;
  int current_number = 0;
  char number[10];
  for (index = 0; index < buffer_size; ++index) {
    if (buffer[index] == ',') {
      strncpy(number, buffer + starting_position_number,
              index - (starting_position_number));
      starting_position_number = ++index;
      array[current_number++] = atoi(number);
      memset(number, 0, 10);
    }
  }
  strncpy(number, buffer + starting_position_number,
          buffer_size - (starting_position_number));
  array[current_number++] = atoi(number);

  // Replace values with the ones previous to damage
  array[1] = 12;
  array[2] = 2;

  // Calculate the new values of the arrays
  for (index = 0; index < array_size; index += 4) {
    switch (array[index]) {
    case 1:
      array[array[index + 3]] =
          array[array[index + 1]] + array[array[index + 2]];
      break;
    case 2:
      array[array[index + 3]] =
          array[array[index + 1]] * array[array[index + 2]];
      break;
    case 99:
      printf("%d\n", array[0]);
      return 0;
    }
  }

  return 0;
}
