#include "hashPaises.h"

struct pais *new_pais(char *paisID)
{
	struct pais *pais = malloc(sizeof(struct pais));

	if (pais != NULL)
	{
		strcpy(pais->id, paisID);
		pais->estudantes_ativos = 0;
		pais->abandonou = 0;
		pais->terminou = 0;
	}
	return pais;
};

struct hashTable_paises *new_hash_paises()
{
	struct hashTable_paises *new = malloc(sizeof(struct hashTable_paises));

	if (new != NULL)
	{
		for (int i = 0; i < SIZE_HASH_PAISES; i++)
		{
			new->tabela[i] = NULL;
		}
	}
	return new;
}

unsigned int hashCodePaises(char *paisID) // algoritmo de hash djb2 (http://www.cse.yorku.ca/~oz/hash.html)
{
	unsigned int hash = 5381;
	int c;
	while ((c = *paisID++))
	{
		hash = ((hash << 5) + hash) + c;
	}
	return hash % SIZE_HASH_PAISES;
}

unsigned int doubleHashingPaises(char *paisID)
{
	unsigned int code = hashCodePaises(paisID);

	code = doubleHashingNumberPaises - (code % doubleHashingNumberPaises);
	return code;
}


bool vazio(struct hashTable_paises *hashPaises, unsigned int code)	//Verifica se uma posição na hash está vazia
{
	if (hashPaises->tabela[code] == NULL)
	{
		return true;
	}
	return false;
}

struct pais *hash_procPais(struct hashTable_paises *hashPaises, char *paisID)	//Procura o pais na hashTable
{
	unsigned int code = hashCodePaises(paisID);
	int i = 1;

	while (!vazio(hashPaises, code)) //Enquanto a posição nao estiver vazia
	{
		if (strcmp(hashPaises->tabela[code]->id, paisID) == 0) //Se os ID's forem iguais
		{
			return hashPaises->tabela[code];
		}
		code = (code + i * doubleHashingPaises(paisID)) % SIZE_HASH_PAISES;
		i++;
	}
	//Se chegar a uma posição vazia sem ter encontrado o pais, return NULL
	return NULL;
}

void hash_inserePais(struct hashTable_paises *hashPaises, struct pais *pais) //Insere o pais na hashTable
{
	unsigned int code = hashCodePaises(pais->id);
	int i = 1;

	while (!vazio(hashPaises, code)) //Enquanto a posição nao estiver vazia
	{
		code = (code + i * doubleHashingPaises(pais->id)) % SIZE_HASH_PAISES;
		i++;
	}
	//Significa que chegou a uma posição NULL, logo colocar lá o pais
	hashPaises->tabela[code] = pais;
}

void hash_removePais(struct hashTable_paises *hashPaises, struct pais *pais) //Remove um pais da hashTable
{
	unsigned int code = hashCodePaises(pais->id);
	int i = 1;
	while (!vazio(hashPaises, code)) //Se a posição nao estiver vazia
	{
		if (strcmp(hashPaises->tabela[code]->id, pais->id) == 0) //Se os ID's forem iguais
		{
			hashPaises->tabela[code] = NULL;
			return;
		}
		code = (code + i * doubleHashingPaises(pais->id)) % SIZE_HASH_PAISES;
		i++;
	}
}
