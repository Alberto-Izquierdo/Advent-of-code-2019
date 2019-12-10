#include "puzzle.h"

#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void get_solution(int image_width, int image_height) {
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
    return;
  }

  int total_size = get_array_size(buffer, SIZE);
  int number_of_characters_per_frame =
      get_number_of_characters_per_frame(image_width, image_height);

  print_final_image(buffer, total_size, number_of_characters_per_frame,
                    image_width);
}

void print_final_image(char *image, int image_size, int frame_size,
                       int image_width) {
  int i, j;
  for (i = 0; i < frame_size; ++i) {
    if (i != 0 && i % image_width == 0) {
      printf("\n");
    }
    for (j = i; j < image_size; j += frame_size) {
      if (image[j] != '2') {
        if (image[j] == '1') {
          printf("%c", '0');
        } else {
          printf(" ");
        }
        break;
      }
    }
  }
  printf("\n");
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
