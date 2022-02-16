#include <stdio.h>

struct list *list_new();
void list_insert(struct list *list, int value);
void list_print(struct list *list);
bool list_empty(struct list *list);
int list_find(struct list *ll, int value);
void list_remove(struct list *ll, int value);
int list_length(struct list *ll);
int list_nth(struct list *ll, int n);
void list_mudarValue(struct list *ll, int n, int value);
void list_linePrint(struct list *ll);