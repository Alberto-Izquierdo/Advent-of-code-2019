#include "puzzle.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int get_solution(int image_width, int image_height) {
  FILE *input_file;
  int SIZE = 999999;
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

  int total_size = get_array_size(buffer, SIZE);
  int number_of_characters_per_frame =
      get_number_of_characters_per_frame(image_width, image_height);
  int i;
  int max_number_of_0s = 99999999;
  int beginning_of_chosen_frame = -1;
  for (i = 0; i < total_size; i += number_of_characters_per_frame) {
    int number_of_0s = get_number_of_appearances(
        '0', buffer + i, number_of_characters_per_frame);
    if (number_of_0s < max_number_of_0s) {
      max_number_of_0s = number_of_0s;
      beginning_of_chosen_frame = i;
    }
  }

  int number_of_1s = get_number_of_appearances(
      '1', buffer + beginning_of_chosen_frame, number_of_characters_per_frame);
  int number_of_2s = get_number_of_appearances(
      '2', buffer + beginning_of_chosen_frame, number_of_characters_per_frame);

  return number_of_1s * number_of_2s;
}

int get_array_size(char *array, int max_size) {
  int result;
  for (result = 0; result < max_size; ++result) {
    if (array[result] == '\0') {
      return --result;
    }
  }
  return max_size;
}

int get_number_of_appearances(char character, char *array, int size) {
  int i;
  int result = 0;
  for (i = 0; i < size; ++i) {
    if (array[i] == character) {
      ++result;
    }
  }
  return result;
}

int get_number_of_characters_per_frame(int image_width, int image_height) {
  return image_width * image_height;
}
