# -----------------------------------
# Matematica Computacional II - 2018/2019
# M4 1.3 Testes de Hipoteses a 1 ou 2 amostras
# Eliana nov/2018
# -----------------------------------
# ate ex 22
# -------------------
# Exercício 14
# -------------------
espessura<-c(30,30,30,30,31,32,32,32,32,33,33,34,34,34,35)
t.test(espessura, 
       alternative="two.sided",
       mu=32.5, 
       conf.level = 0.95)
hist(espessura)
boxplot(espessura)

shapiro.test(espessura)


# -------------------
# Exercício 15
# -------------------
entrega<-c(5,4,4,5,5,5,6,5,4,4,3,4,4,5,5,7,6,5,6,4,6,5,5,6,6,6,4,4,5,5,5,3,6,3,6,5)
t.test(entrega,
       alternative="greater",
       mu=5,
       conf.level = 0.99)

# testar a normalidade
ks.test(entrega,
        "pnorm",
        mean(entrega),
        sd(entrega))
#D = 0.21184, p-value = 0.07902 >1% => não rejeito a normalidade
shapiro.test(entrega)
# W = 0.90652, p-value = 0.005168<1% => rejeito a normalidade

# -------------------
# Exercício 16
# -------------------
peso<-c(2550,2550,2450,2560,2520,2530,2530,2500,2490,2510,2520,2520,2530,2510,2550,2550)

t.test(peso,
       alternative="greater",
       mu=2500,
       conf.level = 0.9)


# -------------------
# Exercício 17
# -------------------
comfio<-c(2300,2000,1800,2000,2400,2200,2000,1800,1900,2100,2200,2400)
semfio<-c(2400,2200,1800,1900,1800,1900,2100,2050,2200,2000,1900,2000)

# Para aplicar teste de Levene
library(reshape2)
library(car)
#Criar data.frame
dados <- as.data.frame(cbind(comfio, semfio))
#Recriar a data frame com 1 única variável de Velocidade e um
# factor Tipo que identifica a que tipo de rato pertence
Mdados <- melt(dados,variable.name = "Tipo",
               value.name = "Velocidade")  
View(Mdados)

var.test(Mdados$Velocidade~Mdados$Tipo)

t.test(Mdados$Velocidade~Mdados$Tipo,
       alternative=("two.sided"),
       conf.level = 0.95,
       var.equal = TRUE,
       paired = FALSE,
       mu=0)



# Aplicar teste de Levene
leveneTest(Velocidade ~ Tipo, Mdados,center=mean)

# Alternativamente, poderemos considerar os centros na mediana
#mais robusto embora o resultado seja semelhante neste caso
leveneTest(Velocidade ~ Tipo, Mdados,center=median)


# Em alternativa ao teste de Levene temos o teste F  (Teste F é menos robusto, mais sensivel à normalidade )
var.test(comfio,semfio,alternative=("two.sided"),conf.level = 0.99)
# ou usando a formulação seguinte
var.test(Mdados$Velocidade~Mdados$Tipo,alternative=("two.sided"),conf.level = 0.99)

# Teste para a igualdade de médias, considerando vars. iguais
t.test(comfio,semfio,alternative=("two.sided"),
       conf.level = 0.99,var.equal = TRUE,
       paired = FALSE,mu=0)



# -------------------
# Exercício 18
# -------------------
esq<-c(140,90,125,130,95,121)
dir<-c(138,89,126,128,92,122)
t.test(esq,dir,alternative=("greater"),conf.level = 0.95,
       paired = TRUE,mu=0)

# -------------------
# Exercício 19
# -------------------
antes<-c(20,35,40,55,60,75,95,100,90,80,45,25)
depois<-c(25,30,45,75,80,100,100,100,100,85,65,30)

t.test(antes,depois,
       alternative= "less",
       conf.level = 0.95,
       paired = TRUE,mu=0)


# -------------------
# Exercício 20
# -------------------
Marca_A<-c(6.3,5.2,6.0,6.1,6.5,5.6,5.8,6.0,5.9,5.8,5.9,6.2)
Marca_B<-c(4.8,6.7,7.1,5.0,6.2,6.1,6.0,5.9,7.0,4.5,5.3,6.2)

# teste de Levene:
library(reshape2)
library(car)

dados <- as.data.frame(cbind(Marca_A, Marca_B))
Mdados <- melt(dados,variable.name = "Marca",value.name = "Duração")
leveneTest(Duração ~ Marca, Mdados,center=mean)

t.test(Marca_A,Marca_B,alternative=("two.sided"),conf.level = 0.9,var.equal = FALSE,paired = FALSE,mu=0)
# Alternativamente
attach(Mdados)
t.test(Duração ~ Marca,alternative=("two.sided"),conf.level = 0.9,var.equal = FALSE,paired = FALSE,mu=0)


# -------------------
# Exercício 21
# -------------------
dados<-read.csv2(file="SistemaOperativo.csv",header=T)
head(dados)
str(dados)
attach(dados)
sucessos<-sum(SistemaOperativo=="windows")
n=length(SistemaOperativo)
p_sample= sucessos/n
p_sample # 0.86 

# b)
prop.test(sucessos,n,p=0.85,alternative = "greater",conf.level = 0.95)



# -------------------
# Exercício 22
# -------------------
dados<-read.csv2(file="RedesSociais.csv",header=F, 
                 skip = 1,
                 col.names =c("Individuo","Sexo","TempoRedes","Rede"))
head(dados)
str(dados)
# $ Individuo : int  1 2 3 4 5 6 7 8 9 10 ...
# $ Sexo      : Factor w/ 2 levels "feminino","masculino": 1 2 1 1 2 1 2 1 2 2 ...
# $ TempoRedes: num  0.8 4.7 2.9 0.7 4 1.5 0.1 2.5 0.1 4.9 ...
# $ Rede      : Factor w/ 4 levels "Facebook","Instagram",..: 1 1 1 1 1 1 3 1 1 1 ...

# Se a importação for feita com o GUI do próprio Rstudio será necessário
# alterar o nome das variáveis a posteriori (para nomes mais curtos)
str(dados)
# 'data.frame':	150 obs. of  4 variables:
# $ Individuo                        : int  1 2 3 4 5 6 7 8 9 10 ...
# $ Sexo                             : Factor w/ 2 levels "feminino","masculino": 1 2 1 1 2 1 2 1 2 2 ...
# $ Tempo.gasto.nas.redes.sociais..h.: num  0.8 4.7 2.9 0.7 4 1.5 0.1 2.5 0.1 4.9 ...
# $ Rede.social.usada                : Factor w/ 4 levels "Facebook","Instagram",..: 1 1 1 1 1 1 3 1 1 1 ...
colnames(dados)[3:4]<-c("TempoRedes","Rede")
names(dados)

attach(dados)
#a) 
t.test(TempoRedes[Sexo=="masculino"],alternative=("two.sided"),conf.level = 0.95)

#b) 
# teste de Levene:
library(car)
leveneTest(TempoRedes~Sexo,center=mean)
t.test(TempoRedes~Sexo, paired=FALSE, var.equal = FALSE,conf.level = 0.95)


# c) 
sucessos<-sum(Rede=="Facebook")
sucessos #90
n<-length(Rede)
n # 150
prop.test(sucessos,n,p=0.5,alternative = "greater",conf.level = 0.95)


