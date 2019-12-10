#include "puzzle.h"
#include <setjmp.h>
#include <stdarg.h>
#include <stddef.h>

#include <cmocka.h>

void test_get_number_of_appearances(void **state) {
  {
    char array[] = "0123456789";
    assert_int_equal(get_number_of_appearances('0', array, 10), 1);
  }
  {
    char array[] = "1111";
    assert_int_equal(get_number_of_appearances('1', array, 4), 4);
  }
  {
    char array[] = "1010101010";
    assert_int_equal(get_number_of_appearances('1', array, 10), 5);
  }
  {
    char array[] = "0101010101";
    assert_int_equal(get_number_of_appearances('1', array, 10), 5);
  }
}

int main() {
  const struct CMUnitTest tests[] = {
      cmocka_unit_test(test_get_number_of_appearances),
  };
  return cmocka_run_group_tests(tests, NULL, NULL);
}
