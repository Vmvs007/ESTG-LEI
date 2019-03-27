# -------------------
# Exercicio 30
# -------------------
dados<-read.csv2(file="DesempenhoPCs.csv")
str(dados)
attach(dados)

friedman.test(Resposta,Marca,Utilizador)
# três amostras emparelhadas
# Friedman chi-squared = 16.545, df = 2, p-value = 0.0002554
# existem diferenças significativas entre os grupos

# vejamos dois a dois
boxplot(Resposta~Marca)
# o boxplot sugere que A seja diferente de B e C mas B=C
library(moments)
skewness(Resposta[Marca=="A"])
skewness(Resposta[Marca=="B"])
skewness(Resposta[Marca=="C"])
# A e B: p-value = 0.00551 <5% Logo A diferente de B
wilcox.test(Resposta[Marca=="A"], Resposta[Marca=="B"],
            paired = TRUE)
# A e C: p-value = 0.001903 <5% Logo A diferente de C
wilcox.test(Resposta[Marca=="A"], Resposta[Marca=="C"],
            paired = TRUE)
# B e C: p-value = 0.3307 > 5% Logo nao existem dif significativas entre B e C
wilcox.test(Resposta[Marca=="B"], Resposta[Marca=="C"],
            paired = TRUE)

# -------------------
# Exercicio 31
# -------------------
# amostras independentes
# podemos usar anova ou kruskal wallis
# anova -- var iguais e dist normais
idade15_30<-c(4,5,3,2,5,4,4,5)
idade31_45<-c(3,4,3,3,5,3,4,4)
idade46_60<-c(2,3,4,2,3,5,4,3,2)

boxplot(idade15_30,idade31_45,idade46_60)

aval<-c(idade15_30,idade31_45,idade46_60)
grupos<-factor(c(rep("15-30",8),rep("31-45",8),rep("46-60",9)))

shapiro.test(idade15_30)
shapiro.test(idade31_45) # rejeitamos a normalidade a 5%
shapiro.test(idade46_60)
library(car)
leveneTest(aval,grupos) # homegeneidade das variancias
# Como regeitamos a normalidade para idade31_45 vamos usar o teste nao parametrico
# i.e. Kruskal Wallis
kruskal.test(list(idade15_30,idade31_45,idade46_60))
# p-value = 0.1845 >5%--- nao existem diferencas significativas

# Alternativamente
dados<-data.frame(respostas=c(idade15_30,
                              idade31_45,idade46_60),
                  faixa=c(rep("15 - 30 anos",8),
                          rep("31 - 45 anos",8),
                          rep("46 - 60 anos",9)))
attach(dados)
kruskal.test(respostas ~ faixa)

# -------------------
# Exercício 32 cint de ex. 22
# -------------------
dados<-read.csv2(file="RedesSociais.csv",header=F, 
                 skip = 1,
                 col.names =c("Individuo","Sexo","TempoRedes","Rede"))
head(dados)
str(dados)
dados$TempoRedes=as.numeric(dados$TempoRedes)

summary(dados$Rede)
# Facebook Instagram  Linkedin   Twitter 
#   90        13        34        13 

boxplot(dados$TempoRedes~dados$Rede)

redes = unique(dados$Rede) 
# Facebook  Linkedin  Twitter   Instagram
summary(dados$TempoRedes[dados$Rede==redes[1]]) # Facebook
summary(dados$TempoRedes[dados$Rede==redes[2]]) # Linkedin
summary(dados$TempoRedes[dados$Rede==redes[3]]) # Twitter
summary(dados$TempoRedes[dados$Rede==redes[4]]) # Instagram

shapiro.test(dados$TempoRedes[dados$Rede==redes[1]])
shapiro.test(dados$TempoRedes[dados$Rede==redes[2]]) # rejeitamos a normalidade
shapiro.test(dados$TempoRedes[dados$Rede==redes[3]])
shapiro.test(dados$TempoRedes[dados$Rede==redes[4]])

library(car)
leveneTest(dados$TempoRedes~dados$Rede)
# nao rejeitamos a igualdade das variancias pq p- value = 0.3515

anova(lm(dados$TempoRedes~dados$Rede))
# p-value 0.8188 >1%  nao rejeitamos H_0: medias sao todas iguais
# nao existem diferencas significativas entre os tempos medios
