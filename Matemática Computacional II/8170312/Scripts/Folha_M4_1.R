# Matematica Computacional II - 2018/2019
# Proposta de resolução do exercicios 4 e 7
# Eliana nov/2018

#Exercicio 4
dados = c(10, 26, 53, 33, 24, 31 ,
          42, 14, 21, 10, 46 ,11 ,23 ,35 ,
          43 ,51 ,33 ,22 ,46 ,25 ,15, 32 ,
          14 ,50 ,33 ,22 ,18 ,55 ,12, 17, 45 ,51) 
mean(dados)     # media
sd(dados)       # desvio padrao
quantile(dados) # quartis
boxplot(dados)  #diagrama de extremos e quartis
# uma das medidas que caracteriza a dispersao dos dados é o desvio padrao neste caso 14.38269
# por observacao do boxplot e dos quartis verificamos:
#  - que metade (50%) sao inferiores ou iguais a 28.5 e os restantes sao superiores ou iguais a 28.5
#  - 25% dos dados sao <=17.75 e os restantes sao >=
#  - 50% dos dados tem entre 17.75 e 43.50, o que corresponde a uma amplitude de 25.75
#  - ....


# Exercicio 7
CidadeA=c(82, 110,94, 115,138,102,138)
CidadeB=c(84, 101,94, 105, 99, 90, 84)
CidadeC=c(100,131,110,138,111,132,120)

dados=data.frame(PrecoA=CidadeA,
                 PrecoB=CidadeB,
                 PrecoC=CidadeC)
View(dados)  

head(dados)  
str(dados)  
vartodas=names(dados) # vector com todos os nomes das var.  
vartodas[3]           # o nome da terceira var.
dim(dados)            #numero de observacoes e var.
  
summary(dados)

mean(dados$PrecoA)  

# DESVIO PADRAO
sd(dados$PrecoA)  
sd(dados$PrecoB)  
sd(dados$PrecoC)  

#VARIANCIA
var(dados$PrecoA)  
var(dados$PrecoB)  
var(dados$PrecoC)  

# Coef de variacao
sd(dados$PrecoA)/mean(dados$PrecoA)  
sd(dados$PrecoB)/mean(dados$PrecoB)  
sd(dados$PrecoC)/mean(dados$PrecoC)


boxplot(dados)
