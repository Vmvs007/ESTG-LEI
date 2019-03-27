# -----------------------------------
# Matematica Computacional II - 2018/2019
# M4 1.2 Intervalos de Confianca
# Eliana nov/2018
# -----------------------------------
#   
# -------------------
# Exercício 8
# -------------------
potencia<-c(8.9,9.1,9.2,9.1,8.4,9.5,9.0,9.6,9.3,9.3,8.9,9.7,8.7,9.4,8.5,8.9,8.4,9.5,9.3,8.8,9.4,8.9,9.3,9.0,9.2,9.1,9.8,9.6,9.3,9.2,9.1,9.6,9.8,9.5,10.0,9.3)

length(potencia)
mean(potencia)
hist(potencia)
t.test(potencia,conf.level = 0.95)
# IC=[9.081252,9.340970] 

# -------------------
# Exercício 9
# -------------------
tempo<-c(39,41,42,41,34,45,40,46,43,43,39,47,37,44,35,39,34,45,43,38,44,39,43,40,42,41,48,46,43,42,41,46,48,45,50,43)
t.test(tempo,conf.level = 0.95)
# IC=[40.81252,43.40970] 

# -------------------
# Exercício 10
# -------------------
appA<-c(11,12,10,15,13,11,12,14)
appB<-c(12,9,11,13,9,10,11,12)
# amostras independentes
mean(appA)
mean(appB)
mean(appA)-mean(appB)
boxplot(appA,appB)
# 
t.test(appA,appB,conf.level = 0.89,
       var.equal = TRUE,paired = FALSE)
#IC=[-0.9572937,3.7072937]

# De forma alternativa, usando data frame e fórmula na função t.test
dados<-data.frame(tempo=c(appA,appB),
                  app=as.factor(c(rep("appA",8),rep("appB",8))))
View(dados)
t.test(dados$tempo~dados$app,
       conf.level=0.99,
       var.equal=TRUE,
       paired = FALSE)

boxplot(dados$tempo~dados$app)
boxplot(dados$tempo)
boxplot(dados) # assim nao!!!!!!!!!!!
summary(dados)
# -------------------
# Exercício 11
# -------------------
RAM1<-c(3.1,0.5,3.2,1.5,6.7,4.3,2.2,6.1,1.1,2.8,5.3,2.7)
RAM2<-c(3.4,0.7,3,2,7,4.5,2.8,6.3,1.5,3.9,5.8,3)

var(RAM1)
var(RAM2)
boxplot(RAM1,RAM2)

var.test(RAM1,RAM2,conf.level = 0.99)
#99% conf as var sao iguais pq 1 in  [0.1918933, 5.4303616]

t.test(RAM1,RAM2,
       conf.level = 0.99,
       paired = TRUE,
       var.equal = TRUE)
#IC=[-0.64322811,-0.09010523]


# -------------------
# Exercício 12
# -------------------
TempA<-c(67,70,66,68,68,72,65,65,66,68,69,67)
TempB<-c(69,65,65,70,71,71,69,70,71,66,68,69)
t.test(TempA,TempB,
       conf.level = 0.99,
       var.equal = TRUE,
       paired = FALSE)
#IC=[-3.556092,1.389425]


# -------------------
# Exercício 13
# -------------------
com_soft<-c(61,12,56,75,13,30,58,63,29,66,25,40)
sem_soft<-c(48,13,33,55,22,28,54,14,9,46,30,36)
summary(com_soft)
summary(sem_soft)

boxplot(com_soft,sem_soft)

sd(com_soft)
sd(sem_soft)

var.test(com_soft,sem_soft)

t.test(com_soft,sem_soft,
       conf.level = 0.95,
       var.equal = TRUE,
       paired = TRUE)
#IC=[1.513997,21.819336]
