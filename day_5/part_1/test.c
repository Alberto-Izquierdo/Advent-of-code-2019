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
  {
    int array[] = {1, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 101);
  }
  {
    int array[] = {2, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 198);
  }
  {
    int array[] = {1101, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 6);
  }
  {
    int array[] = {1102, 4, 2, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 8);
  }
  {
    int array[] = {1001, 4, 5, 0, 99};
    assert_int_equal(get_solution_from_array(array, 5), 104);
  }
}

int main() {
  const struct CMUnitTest tests[] = {
      cmocka_unit_test(test_get_n_position),
      cmocka_unit_test(test_get_solution),
  };
  return cmocka_run_group_tests(tests, NULL, NULL);
}
