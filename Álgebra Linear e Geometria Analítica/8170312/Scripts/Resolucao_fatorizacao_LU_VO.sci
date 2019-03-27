clear()
// Definição da matriz A e do vetor b
// Exercício 57
A=[1 1 -1;2 1 3; 3 2 -1];
b=[1;2;0];  // ou b=[1 2 0]'
Det=det(A);   // determinante 
Car=rank(A); // ou característica
disp(Det,'det(A)=')
disp(Car,'r(A)=')

rref([A b])  // método da condensação de Gauss 

// Faturização LU

exec gauss_VO.sci;

exec fatorizacao_LU_VO.sci;

[L,U]=gauss(A);

[x]=fatorizacao_LU(L,U,b);

disp(x,'x=',L,'L=',U,'U=');

