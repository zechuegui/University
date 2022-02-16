#include <stdio.h>
#include <stdbool.h>

struct queue *queue_new();
bool queue_isEmpty(struct queue *q);
void enqueue(struct queue *q, int element);
int dequeue(struct queue *q);
int queue_front(struct queue *q);
void queue_print(struct queue *q);
int queue_size(struct queue *q);
void queue_decrementarPrimeiro(struct queue *q);
bool queue_find(struct queue *q, int element);