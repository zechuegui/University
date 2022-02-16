#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

#define SIZE_HASH_PAISES 1019
#define ID_PAIS_SIZE 3
#define doubleHashingNumberPaises 1013

struct pais
{
	char id[ID_PAIS_SIZE];
	unsigned int estudantes_ativos;
	unsigned int abandonou;
	unsigned int terminou;
};

struct hashTable_paises
{
	struct pais *tabela[SIZE_HASH_PAISES];
};

struct hashTable_paises *new_hash_paises();
struct pais *new_pais(char *paisID);

unsigned int hashCodePaises(char *paisID);
unsigned int doubleHashingPaises(char *paisID);

bool vazio(struct hashTable_paises *hashPaises, unsigned int pos);

void hash_inserePais(struct hashTable_paises *hashPaises, struct pais *pais);
struct pais *hash_procPais(struct hashTable_paises *hashPaises, char *paisID);
void hash_removePais(struct hashTable_paises *hashPaises, struct pais *pais);

