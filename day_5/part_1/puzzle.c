#include "puzzle.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int get_n_position(int original_number, int position) {
  return (original_number / (int)(pow(10.0, (double)(position)))) % 10;
}

int get_solution() {
  FILE *input_file;
  int SIZE = 1024;
  char buffer[SIZE];
  size_t nread;

  // Read file
  input_file = fopen("input", "r");
  if (input_file) {
    nread = fread(buffer, 1, sizeof buffer, input_file);
    fclose(input_file);
  } else {
    return 0;
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
  int original_array[array_size];

  // Convert the strings into integers
  int starting_position_number = 0;
  int current_number = 0;
  char number[10];
  for (index = 0; index < buffer_size; ++index) {
    if (buffer[index] == ',') {
      strncpy(number, buffer + starting_position_number,
              index - (starting_position_number));
      starting_position_number = ++index;
      original_array[current_number++] = atoi(number);
      memset(number, 0, 10);
    }
  }
  strncpy(number, buffer + starting_position_number,
          buffer_size - (starting_position_number));
  original_array[current_number++] = atoi(number);

  return get_solution_from_array(original_array, array_size);
}

int get_solution_from_array(int *original_array, int array_size) {
  int index;
  for (index = 0; index < array_size; index += 4) {
    int instruction = original_array[index];
    int operation = instruction % 100;
    int mode_first_parameter = get_n_position(instruction, 2);
    int mode_second_parameter = get_n_position(instruction, 3);
    if (operation == 99) {
      return original_array[0];
    } else {
      int first_parameter = mode_first_parameter == 0
                                ? original_array[original_array[index + 1]]
                                : original_array[index + 1];
      int second_parameter = mode_second_parameter == 0
                                 ? original_array[original_array[index + 2]]
                                 : original_array[index + 2];
      int third_parameter = original_array[index + 3];
      if (operation == 1) {
        original_array[third_parameter] = first_parameter + second_parameter;
      } else if (operation == 2) {
        original_array[third_parameter] = first_parameter * second_parameter;
      } else if (operation == 3) {
        scanf("%d", original_array + original_array[index + 1]);
        index -= 2;
      } else if (operation == 4) {
        printf("%d\n", first_parameter);
        index -= 2;
      }
    }
  }
}
