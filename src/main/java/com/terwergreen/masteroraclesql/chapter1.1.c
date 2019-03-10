#include "stdio.h"
#include "stdlib.h"
void main(){
int a;
EXEC  SQL SELECT salary  INTO :a
            FROM hr.employees
            WHERE employee_id=108;
 printf("The salary is %d\n", a);
}