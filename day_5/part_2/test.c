#include "puzzle.h"
#include <setjmp.h>
#include <stdarg.h>
#include <stddef.h>

#include <cmocka.h>

void test_get_n_position(void **state) {
  assert_int_equal(get_n_position(153, 0), 3);
  assert_int_equal(get_n_position(153, 1), 5);
  assert_int_equal(get_n_position(153, 2), 1);
  assert_int_equal(get_n_position(1, 5), 0);
}

void test_get_solution(void **state) {
  {
    int array[] = {99};
    assert_int_equal(get_solution_from_array(array, 1), 99);
  }
}

void test_addition(void **state) {
  {
    int array[] = {1, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 101);
  }
}

void test_multiplication(void **state) {
  {
    int array[] = {2, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 198);
  }
}

void test_addition_with_parameter_mode(void **state) {
  {
    int array[] = {1101, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 6);
  }
  {
    int array[] = {1001, 4, 5, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 104);
  }
}

void test_multiplication_with_parameter_mode(void **state) {
  {
    int array[] = {1102, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 8);
  }
}

void test_jump_if_true(void **state) {
  {
    int array[] = {11105, 1, 1, 0, 0, 99};
    assert_int_equal(get_solution_from_array(array, 6), 11106);
  }
  {
    int array[] = {11105, 0, 1, 1, 3, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 8), 2);
  }
}

void test_jump_if_false(void **state) {
  {
    int array[] = {11106, 0, 2, 3, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 7), 6);
  }
  {
    int array[] = {11106, 1, 1, 1101, 3, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 8), 5);
  }
}

void test_less_than(void **state) {
  {
    int array[] = {1107, 5, 7, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 1);
  }
  {
    int array[] = {1107, 9, 7, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 0);
  }
}

void test_equals(void **state) {
  {
    int array[] = {1108, 5, 5, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 1);
  }
  {
    int array[] = {1108, 9, 7, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 0);
  }
}

int main() {
  const struct CMUnitTest tests[] = {
      cmocka_unit_test(test_get_n_position),
      cmocka_unit_test(test_get_solution),
      cmocka_unit_test(test_addition),
      cmocka_unit_test(test_multiplication),
      cmocka_unit_test(test_addition_with_parameter_mode),
      cmocka_unit_test(test_multiplication_with_parameter_mode),
      cmocka_unit_test(test_jump_if_true),
      cmocka_unit_test(test_jump_if_false),
      cmocka_unit_test(test_less_than),
      cmocka_unit_test(test_equals),
  };
  return cmocka_run_group_tests(tests, NULL, NULL);
}
