#include <stdio.h>
#include "hashEstudantes.h"
#include "hashPaises.h"

FILE *fileOpen(char nome[]) //Abre o ficheiro, com o nome "nome", se não existir cria-o, e devolve-o
{
	FILE *ficheiro;
	ficheiro = fopen(nome, "rb+");

	if (ficheiro == NULL)
	{
		ficheiro = fopen(nome, "wb+");
	}
	return ficheiro;
}

void printPais(struct pais *pais, char *paisID) //Realizará o output do pais
{
	if (pais == NULL) //Se não existirem dados sobre o pais
	{
		printf("+ sem dados sobre %s\n", paisID);
	}
	else //Se o pais existir, realizar o output dos dados correspondentes ao pais
	{
		unsigned int totalEstudantes = pais->estudantes_ativos + pais->abandonou + pais->terminou;
		printf("+ %s - correntes: %u, diplomados: %u, abandonaram: %u, total: %u\n", paisID, pais->estudantes_ativos, pais->terminou, pais->abandonou, totalEstudantes);
	}
}

void printEstudante(struct estudante *estudante) //Realizará o output do estudante
{
	if (estudante->terminou) //Se o estudante terminou
	{
		printf("+ estudante %s terminou\n", estudante->id);
	}
	else //Se o estudante não terminou, significa que abandonou
	{
		printf("+ estudante %s abandonou\n", estudante->id);
	}
}

int main(void)
{
	FILE *file_paises = fileOpen("_paises.bin");			//Abertura dos ficheiroos
	FILE *file_estudantes = fileOpen("_estudantes.bin");

	struct hashTable_paises *hashPaises = new_hash_paises();

	struct pais *paisTemp = malloc(sizeof(struct pais));

	while (fread(paisTemp, sizeof(struct pais), 1, file_paises) != 0) // carregar paises do disco
	{
		struct pais *pais = new_pais(paisTemp->id);
		pais->terminou = paisTemp->terminou;
		pais->abandonou = paisTemp->abandonou;
		pais->estudantes_ativos = paisTemp->estudantes_ativos;
		hash_inserePais(hashPaises, pais);
	}
	free(paisTemp);

	char entrada;

	while (scanf("%c", &entrada))
	{
		if (entrada == 'I') //Introduzir um estudante
		{
			char estudanteID[ID_ESTUDANTE_SIZE];
			char paisID[ID_PAIS_SIZE];

			scanf("%s %s", estudanteID, paisID);

			unsigned int pos = procPosEstudanteDISK(estudanteID, file_estudantes);	
			struct estudante *estudanteTemp = getEstudanteDISK(pos, file_estudantes);	//Obter estudante na "pos", do disco

			if (strcmp(estudanteTemp->id, estudanteID) == 0 && estudanteTemp->existe)	//Se o estudante já existir
			{
				printf("+ estudante %s existe\n", estudanteID);
			}
			else //Se o estudante não existir criar o Estudante, e procurar o pais associado
			{
				struct estudante *novoEstudanteTemp = new_estudante(estudanteID, paisID);
				struct pais *paisTemp = hash_procPais(hashPaises, paisID);

				if (paisTemp != NULL) //Se o pais existir, incrementar o número de estudantes ativos
				{
					paisTemp->estudantes_ativos++;
				}
				else //Se o pais não existir criar o pais, e adicioná-lo à hashPaises
				{
					struct pais *novoPaisTemp = new_pais(paisID);
					novoPaisTemp->estudantes_ativos++;
					hash_inserePais(hashPaises, novoPaisTemp);
				}
				insereEstudanteDISK(novoEstudanteTemp, file_estudantes, pos); //Inserir o estudante criado

				free(novoEstudanteTemp);
			}
			free(estudanteTemp);
		}
		else if (entrada == 'R') //Remover um estudante Ativo
		{
			char estudanteID[ID_ESTUDANTE_SIZE];
			scanf("%s", estudanteID);

			unsigned int pos = procPosEstudanteDISK(estudanteID, file_estudantes);
			struct estudante *estudanteTemp = getEstudanteDISK(pos, file_estudantes);	//Obter estudante na "pos", do disco

			if (strcmp(estudanteTemp->id, estudanteID) == 0 && estudanteTemp->existe) //Verificar se o estudante existe
			{
				if (estudanteTemp->ativo) //Se existir, verificar se está ativo
				{
					estudanteTemp->existe = false;
					estudanteTemp->ativo = false;

					struct pais *pais = hash_procPais(hashPaises, estudanteTemp->pais);
					pais->estudantes_ativos--;
					unsigned int totalEstudantes = pais->estudantes_ativos + pais->abandonou + pais->terminou;

					if (totalEstudantes == 0) //Verificar se o pais, deixou de ter estudantes, para o remover se este for o caso
					{
						hash_removePais(hashPaises, pais);
						free(pais);
					}
					insereEstudanteDISK(estudanteTemp, file_estudantes, pos);	//Inserir o estudante atualizado no DISK
				}
				else	//Se o estudante não estiver ativo signifca que terminou ou abandonou
				{ 
					printEstudante(estudanteTemp);
				}
			}
			else // estudante nao existe
			{
				printf("+ estudante %s inexistente\n", estudanteID);
			}
			free(estudanteTemp);
		}
		else if (entrada == 'T')	//Estudante terminou o curso
		{
			char estudanteID[ID_ESTUDANTE_SIZE];
			scanf("%s", estudanteID);

			unsigned int pos = procPosEstudanteDISK(estudanteID, file_estudantes);
			struct estudante *estudanteTemp = getEstudanteDISK(pos, file_estudantes);

			if (strcmp(estudanteTemp->id, estudanteID) == 0 && estudanteTemp->existe)	//Se o estudante existe
			{
				if (estudanteTemp->ativo)	//Se o estudante está ativo
				{
					estudanteTemp->ativo = false;
					estudanteTemp->terminou = true;

					struct pais *paisTemp = hash_procPais(hashPaises, estudanteTemp->pais);
					paisTemp->terminou++;
					paisTemp->estudantes_ativos--;
					
					insereEstudanteDISK(estudanteTemp, file_estudantes, pos);	//Inserir o estudante atualizado no DISK
				}
				else	//Caso já tenha abandonado ou terminado o curso
				{
					printEstudante(estudanteTemp);
				}
			}
			else	//Se o estudante não existir
			{
				printf("+ estudante %s inexistente\n", estudanteID);
			}
			free(estudanteTemp);
		}
		else if (entrada == 'A')	//Estudante abandona o curso
		{
			char estudanteID[ID_ESTUDANTE_SIZE];
			scanf("%s", estudanteID);

			unsigned int pos = procPosEstudanteDISK(estudanteID, file_estudantes);
			struct estudante *estudanteTemp = getEstudanteDISK(pos, file_estudantes);	//Obter estudante na "pos", do disco

			if (strcmp(estudanteTemp->id, estudanteID) == 0 && estudanteTemp->existe)	//Se o estudante existe
			{
				if (estudanteTemp->ativo)	//Se o estudante está ativo
				{
					estudanteTemp->ativo = false;

					struct pais *paisTemp = hash_procPais(hashPaises, estudanteTemp->pais);
					paisTemp->abandonou++;
					paisTemp->estudantes_ativos--;

					insereEstudanteDISK(estudanteTemp, file_estudantes, pos);	//Atualiza o estudante em disco
				}
				else	//Se já tiver abandonado ou terminado o curso
				{
					printEstudante(estudanteTemp);
				}
			}
			else	//Se o estudante não existir
			{
				printf("+ estudante %s inexistente\n", estudanteID);
			}
			free(estudanteTemp);
		}
		else if (entrada == 'P')	//Obter dados do pais
		{
			char paisID[ID_PAIS_SIZE];
			scanf("%s", paisID);

			struct pais *paisTemp = hash_procPais(hashPaises, paisID); //procura o pais na tabela de Hash paises
			printPais(paisTemp, paisID);	//mostra os dados do pais
		}
		else if (entrada == 'X')	//Terminar a execução do programa
		{
			int indicador_paises = 0;
			for (int i = 0; i < SIZE_HASH_PAISES; i++)	//Carregar todos os paises da hashPaises, para o disco
			{
				if (hashPaises->tabela[i] != NULL)
				{
					fseek(file_paises, indicador_paises * sizeof(struct pais), SEEK_SET);
					fwrite(hashPaises->tabela[i], sizeof(struct pais), 1, file_paises);
					indicador_paises++;
					free(hashPaises->tabela[i]);
				}
			}
			free(hashPaises);		
			break;
		}
	}
	fclose(file_paises);
	fclose(file_estudantes);
	return 0;
}


