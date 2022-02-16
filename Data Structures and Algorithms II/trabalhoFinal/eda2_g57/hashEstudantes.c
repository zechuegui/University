#include "hashEstudantes.h"

struct estudante *new_estudante(char *estudanteID, char *pais)
{
	struct estudante *estudante = malloc(sizeof(struct estudante));

	if (estudante != NULL)
	{
		strcpy(estudante->id, estudanteID);
		strcpy(estudante->pais, pais);
		estudante->ativo = true;
		estudante->terminou = false;
		estudante->existe = true;
	}
	return estudante;
};

unsigned int hashCodeEstudante(char *estudanteID)	// algoritmo de hash djb2 (http://www.cse.yorku.ca/~oz/hash.html)
{
	unsigned int hash = 5381;
	int c;

	while ((c = *estudanteID++))
	{
		hash = ((hash << 5) + hash) + c;
	}
	return hash % SIZE_HASH_ESTUDANTES;
}

unsigned int doubleHashingEstudante(char *estudanteID)
{
	unsigned int code = hashCodeEstudante(estudanteID);

	code = doubleHashingNumberEstudantes - (code % doubleHashingNumberEstudantes);
	return code;
}

void insereEstudanteDISK(struct estudante *estudante, FILE *file, unsigned int pos)	//Insere estudante no disco, na "pos"
{	
	fseek(file, pos * sizeof(struct estudante), SEEK_SET);
	fwrite(estudante, sizeof(struct estudante), 1, file);
}

struct estudante *getEstudanteDISK(unsigned int pos, FILE *file)	//Obtem uma struct estudante da "pos" do disco
{
	struct estudante *estudante = malloc(sizeof(struct estudante));

	fseek(file, pos * sizeof(struct estudante), SEEK_SET);
	fread(estudante, sizeof(struct estudante), 1, file);
	return estudante;
}

unsigned int procPosEstudanteDISK(char *estudanteID, FILE *file) //Procura a posição de um estudante no disco	
{
	unsigned int code = hashCodeEstudante(estudanteID);
	unsigned int i = 1;
	struct estudante *estudante = malloc(sizeof(struct estudante));

	fseek(file, code * sizeof(struct estudante), SEEK_SET);

	while(fread(estudante, sizeof(struct estudante), 1, file)!= 0 && strcmp(estudante->id,"\0") != 0) //Enquando existirem estudantes
	{
		if (strcmp(estudante->id,estudanteID)==0)	//Se o estudante já existir, devolve a sua posição no disco
		{	
			free(estudante);
			return code;
		}

		code = (code + i * doubleHashingEstudante(estudanteID)) % SIZE_HASH_ESTUDANTES;

		fseek(file, code * sizeof(struct estudante), SEEK_SET);
		i++;
	}

	//Se o estudante não existia em disco, devolve a posição onde este será inserido
	free(estudante);
	return code;

}