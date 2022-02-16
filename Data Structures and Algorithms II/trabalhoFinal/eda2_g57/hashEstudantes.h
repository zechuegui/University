#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define SIZE_HASH_ESTUDANTES 18000041
#define ID_ESTUDANTE_SIZE 7
#define ID_PAIS_SIZE 3
#define doubleHashingNumberEstudantes 17999867

struct estudante
{
	char id[ID_ESTUDANTE_SIZE];
	char pais[ID_PAIS_SIZE];
	bool ativo;
	bool terminou;
	bool existe;
};

struct estudante *new_estudante(char *estudanteID, char *pais);

unsigned int hashCodeEstudante(char *estudanteID);
unsigned int doubleHashingEstudante(char *estudanteID);

void insereEstudanteDISK(struct estudante *estudante, FILE *file, unsigned int pos);
unsigned int procPosEstudanteDISK(char *estudanteID, FILE *file);
struct estudante *getEstudanteDISK(unsigned int pos, FILE *file);

