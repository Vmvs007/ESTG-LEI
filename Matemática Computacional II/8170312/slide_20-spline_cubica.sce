//introduzir os dados
x=[0.2680 0.5180 1.000];
y=[405.0 633.0 1030.0]

//representar pontos
plot2d(x',y',[-3],"011"," ",[0,0,7,5]);

//estimar as derivadas no suporte
d = splin(x,y); 

//definir o intervalo onde se vai estimar
xp=0.139:0.31:0.518

//definir a spline
[yp, yp1, yp2, yp3] = interp(xp, x, y, d); 

//fazer o gráfico dos pontos a estimar e dos valores estimados
plot2d(xp, yp)

//calcular a aproximação que está na 2º posição
yp(2)
