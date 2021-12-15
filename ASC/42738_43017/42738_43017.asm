#Universidade de Évora
#Engenharia Informática  2018/2019
#42738 Dinis Matos
#43017 José Santos

.data

Imagem:  .asciiz "/home/dinis/Desktop/Asc/lena.gray"
Imagem_media: .asciiz "/home/dinis/Desktop/Asc/lena2.gray"
Imagem_mediana: .asciiz "/home/dinis/Desktop/Asc/lena3.gray"
Memoria1:	.space 262144		#512*512 - tamanho
Memoria2:	.space 262144 		#512*512 - tamanho

.text





main:
	
	la $a0, Imagem			# Argumentos da função read_gray_image
	jal read_gray_image		# Função para ler a imagem gray
	nop
	
	la $a0, Memoria1		# Argumentos da função mean_filter
	la $a1, Memoria2		#    //
	la $a2, 512			#    //
	jal mean_filter			# Função para aplicar o filtro da média
	nop
		
	la $a2, 262144			# Argumentos da função write_gray_image
	la $a1, Memoria2		#    //
	la $a0, Imagem_media		#    //
	jal write_gray_image		# Função para escrever a imagem com o filtro da média 
	nop
	
	la $a0, Memoria1		# Argumentos da função median_filter
	la $a1, Memoria2		#    //
	la $a2, 512			#    //
	jal median_filter		# Função para aplicar o filtro da mediana
	nop


	la $a0, Imagem_mediana		# Argumentos da função write_gray_image
	la $a1, Memoria2		#    //
	la $a2, 262144			#    //
	jal write_gray_image		# Função para escrever a imagem com o filtro da mediana
	nop
	
	
	
	li $v0, 10			# Fim do programa
	syscall
	
	
	
	
	
#########################################
# read_gray_image			#
#					#
# Argumento:				#
# a0 - Nome do ficheiro			#
#					#
# Retorno:				#
# v0 - Endereço da imagem		#
#########################################


read_gray_image:			# Ler imagem para os buffers (Memoria1 e Memoria2)
					
	li $a1, 0			# Abre para ler
	li $a2, 0			# Le apenas
	li $v0, 13			# Abre
	syscall 			# v0 = file descriptor
	
	move $t0, $v0			# Guarda o file descriptor (v0) em t0 
	la $a0, 0($t0)			# a0 = file descriptor (t0)
	la $a1, Memoria1		# a1 = Memoria1 é o buffer que vai conter a imagem original
	li $a2, 262144 			# a2 = Tamanho do número de bytes que vai ler (neste caso será 512x512 bytes)
	li $v0, 14 			# Lê
	syscall		
	
	la $a1, Memoria2		# a1 = Memoria2 é o buffer que vai conter a imagem filtrada
	li $a2, 262144 			# a2 = Tamanho do número de bytes que vai ler (neste caso será 512x512 bytes)
	li $v0, 14 			# Lê
	syscall			
	
	li $v0, 16	 		# Fecha a imagem
	syscall				
	
	la $v0, 0($t0)        		# Volta a colocar o file descriptor em $v0
	jr $ra				# Fim da função
	nop				

	
	
	
	
#########################################
# write_gray_image			#
#					#
# Argumentos:				#
# a0 - Nome do ficheiro			#
# a1 - Buffer da imagem			#
# a2 - Tamanho do Buffer		#
#					#
# Retorno:				#
# v0 - Endereço da imagem		#
#########################################


write_gray_image:
	
	subi $sp, $sp,8			# Guarda espaço na pilha
	sw $a1, 0($sp)			# Guarda a1 
	sw $a2, 4($sp)			# Guarda a2
	 				
	li $a1, 1 			# Abre para escrever
	li $a2, 0 			# -- ignorado --
	li $v0, 13 			# Abre
	syscall 			# v0 = file descriptor
	
	move $t0, $v0			# Guarda o file descriptor (v0) em t0
	la $a0, 0($t0)			# a0 = file descriptor (t0)
	lw $a1, 0($sp)			# a1 = Buffer address (retiramos da pilha)
	lw $a2, 4($sp)			# a2 = Tamanho (retiramos da pilha)
	addi $sp, $sp, 8				
	li $v0, 15 			# Escreve
	syscall				

	li $v0, 16			# Fecha
	syscall				
	
	la $v0, 0($t0)			# Volta a colocar o file descriptor em $v0
	jr $ra				# Fim da função
	nop

	
	
	
	
#########################################		
# mean_filter				#				
#					#			
# Argumentos:				#			
# a0 - Buffer da imagem original	#		
# a1 - Buffer da imagem filtrada	#	
# a2 - Tamanho da Imagem		#
#					#			 
# Não tem retorno			#				
#########################################

						
mean_filter:
	subi $sp, $sp, 8		# Guarda o espaço na pilha
	sw $s0, 0($sp)			# Guarda s0
	sw $s1, 4($sp)			# Guarda s1
	
	add $s0, $zero, 511		# s0 será o contador de posições                                   
	addi $a0, $a0, 511		# Incrementa-se 511 a s0, a0 e a1 (a 1ªlinha não é modificada)
	addi $a1, $a1, 511		# a0, s0 e a1 estarão na última coluna (inclui-se o 0)

ciclo:	
	addi $s1, $s0, 511		# s1 será o valor da posição da próxima última coluna
	addi $a0, $a0, 2		# Soma-se a s0, a0 e a1 pois a 1ª e última coluna não são modificadas
	addi $a1, $a1, 2		# a0, s0 e a1 estarão na 2ªcoluna da linha seguinte
	addi $s0, $s0, 2		
	
ciclox:			
	lbu $t0, 0($a0)			# t0 é o núcleo da matriz, guarda-se o valor do pixel que está em a0 em t0  
	add $t2, $zero, $t0		# t2 será a soma dos valores da matriz
	
	lbu $t0, -512($a0)		# t0 é o canto superior esquerdo, guarda-se o pixel em a0 -512 em t0
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, -511($a0)		# t0 é o elemento de cima, guarda-se o pixel em a0 -511 em t0
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, -510($a0)		# t0 é o canto superior direito, guarda-se o pixel em a0 -510 em t0
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, -1($a0)		# t0 é o elemento esquerdo, guarda-se o pixel em a0 -1 em t0
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, 1($a0)			# t0 é o elemento direito, guarda-se o pixel em a0 +1 em t0
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, 510($a0)		# t0 é o canto inferior esquerdo, guarda-se o pixel em a0 +510 em t0 
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, 511($a0)		# t0 é o elemento de baixo, guarda-se o pixel em a0 +511 em t0 
	add $t2, $t2, $t0		# Soma-se a t2
	
	lbu $t0, 512($a0)		# t0 é o canto superior direito, guarda-se o pixel em a0 +512 em t0 
	add $t2, $t2, $t0		# Soma-se a t2
	
	div $t2, $t2, 9			# Faz-se a média, portanto divide-se por 9
	sb $t2, 0($a1)			# Guarda-se a média no centro da matriz
	
	addi $s0, $s0, 1		# Salta-se para a posição seguinte
	addi $a0, $a0, 1		
	addi $a1, $a1, 1
	
	ble $s0, $s1, ciclox		# Acaba o loop se s0 for maior que a ultima coluna da linha.
	nop				
	
	ble $s1, 261634, ciclo	# Acaba o tudo se for s1 for igual a 262143 ((512*512) - 510)
	nop

	lw $s0, 0($sp)			# Tira-se o antigo valor do s0 da pilha e volta-se a guardá-lo em s0
	lw $s1, 4($sp)			# Tira-se o antigo valor do s1 da pilha e volta-se a guardá-lo em s1
	addi $sp, $sp, 8		# Tira-se o espaço da pilha
	jr $ra				# Fim da função
	nop

	
	
	
	
#########################################		
# median_filter				#				
#					#			
# Argumentos:				#			
# a0 - buffer da imagem original	#		
# a1 - buffer da imagem filtrada	#	
# a2 - dimensões da imagem		#
#					#			 
# Não tem retorno			#				
#########################################

median_filter:
	
	addi $sp, $sp, -8		# Guarda espaço na pilha
	sw $s0, 0($sp)			# Guarda s0
	sw $s1, 4($sp)			# Guarda s1
	
	
	add $s0, $zero, 511		# s0 será o contador de posições                                   
	addi $a0, $a0, 511		# Soma-se 511 a s0, a0 e a1 pois como a 1ªlinha não é modificada (será igual)
	addi $a1, $a1, 511		# a0, s0 e a1 estarão na última coluna (incluimos o 0, é por isso que não é 512)

ciclo2:	
	addi $s1, $s0, 511		# s1 será o valor da posição da próxima última coluna
	addi $a0, $a0, 2		# Soma-se 2 a s0, a0 e a1 pois nem a última coluna nem a 1ªcoluna é modificada
	addi $a1, $a1, 2		# a0, s0 e a1 estarão na 2ªcoluna da linha seguinte
	addi $s0, $s0, 2
	
ciclox2:	

	lbu $t0, -512($a0)	  
	lbu $t1, -511($a0)
	lbu $t2, -510($a0)
	lbu $t3, -1($a0)
	lbu $t4, 0($a0)
	lbu $t5, 1($a0)	
	lbu $t6, 510($a0)
	lbu $t7, 511($a0)
	lbu $t8, 512($a0)
	
	addi $s6, $zero, 15
	
salto:
	
	
	
	bgt $t1, $t0, salto1
	nop
	
	xor $t0, $t0, $t1	
	xor $t1, $t0, $t1
	xor $t0, $t0, $t1
	
salto1:

	
	
	bgt $t2, $t1, salto2
	nop
	
	xor $t1, $t1, $t2	
	xor $t2, $t1, $t2
	xor $t1, $t1, $t2
		
	
		
	
salto2:
	
	
	bgt $t3, $t2, salto3
	nop
	
	xor $t2, $t2, $t3	
	xor $t3, $t2, $t3
	xor $t2, $t2, $t3
	
	
salto3:

	
	
	bgt $t4, $t3, salto4
	nop
	
	xor $t3, $t3, $t4	
	xor $t2, $t3, $t4
	xor $t3, $t3, $t4
	
	
	
salto4:

	
	
	bgt $t5, $t4, salto5
	nop
	
	xor $t4, $t4, $t5	
	xor $t5, $t4, $t5
	xor $t4, $t4, $t5

	
salto5:

	
	
	bgt $t6, $t5, salto6
	nop
	
	xor $t5, $t5, $t6	
	xor $t6, $t5, $t6
	xor $t5, $t5, $t6
	
	
	
salto6:

	
	
	bgt $t7, $t6, salto7
	nop
	
	xor $t6, $t6, $t7	
	xor $t7, $t6, $t7
	xor $t6, $t6, $t7
	
	

salto7:

	
	bgt $t8, $t7, salto8
	nop
	
	xor $t7, $t7, $t8	
	xor $t8, $t7, $t8
	xor $t7, $t7, $t8
	

	
salto8:

	subi $s6, $s6, 1
	bne $s6, 0, salto
	nop


	sb $t6, 0($a1)			# Guarda a mediana no núcleo da matriz
	
	addi $s0, $s0, 1		# Salta para a posição seguinte
	addi $a0, $a0, 1		
	addi $a1, $a1, 1
	
	ble $s0, $s1, ciclox2		# Se s0 (a posição seguinte) for menor ou igual a s1 (posição da última coluna da linha), 
	nop				# volta a fazer o loop
	
	ble $s1, 261634, ciclo2		# Se s1 ( posição da última coluna da linha ) for menor ou igual a 262143 ( (512x512) - 510)
	nop
	
	lw $s0, 0($sp)			# Tiramos o antigo valor do s0 da pilha e voltamos a guardá-lo em s0
	lw $s1, 4($sp)			# Tiramos o antigo valor do s1 da pilha e voltamos a guardá-lo em s1
	addi $sp, $sp, 8		# Tiramos o espaço da pilha
	jr $ra				# Fim da função
	nop
	
	
		
