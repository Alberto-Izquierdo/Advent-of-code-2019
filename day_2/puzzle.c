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
  int array[array_size];

  // Guess the new values by replacing and testing
  int i, j;
  for (i = 0; i < 100; ++i) {
    for (j = 0; j < 100; ++j) {
      memcpy(array, original_array, array_size * sizeof(int));
      array[1] = i;
      array[2] = j;

      // Calculate the new values of the arrays
      for (index = 0; index < array_size; index += 4) {
        int instruction = array[index];
        int address1 = array[index + 1];
        int address2 = array[index + 2];
        int result_address = array[index + 3];
        if (address1 > array_size || address2 > array_size ||
            result_address > array_size) {
          break;
        }
        if (instruction == 1) {
          array[result_address] = array[address1] + array[address2];
        } else if (instruction == 2) {
          array[result_address] = array[address1] * array[address2];
        } else if (instruction == 99) {
          if (array[0] == 19690720) {
            if (i < 10) {
              printf("0");
            }
            printf("%d", i);
            if (j < 10) {
              printf("0");
            }
            printf("%d\n", j);
            return 0;
          }
          break;
        } else {
          break;
        }
      }
    }
  }

  return 0;
}
