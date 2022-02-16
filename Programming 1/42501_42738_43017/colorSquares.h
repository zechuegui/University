#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

int jogada(int tabuleiro[20][20], int sz, int x, int y);
void mostrar (int tabuleiro [20][20], int sz);
void mostrarAuto (int tabuleiro [20][20], int sz, FILE *fjogo);
int marcar (int tabuleiro [20][20], int sz, int x, int y);
int pontuacao(int numquadrados);
void gravidade (int tabuleiro[20][20], int sz);
void coluna (int tabuleiro[20][20], int sz);