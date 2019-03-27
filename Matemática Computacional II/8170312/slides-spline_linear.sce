x=[1 10 20 30 40];

y=[1 30 -10 20 40];

//fazer o gráfico dos dados

plot2d(x',y',[-3],"011"," ",[0,-20,41,50]);

//criar a spline

yi=interpln([x;y],1:40);

//representar a spline

plot2d((1:40)',yi',[3],"000");
