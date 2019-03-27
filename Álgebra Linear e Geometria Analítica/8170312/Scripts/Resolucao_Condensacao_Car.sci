clear()
// Definição da matriz A e do vetor b
// Exercício 58.3
A=[3 5 1 0;1 1 2 1;2 0 1 -1;0 2 3 3;3 1 3 0];
b=[-1;1;4;-2;5];  
//Det=det(A);   // determinante só de matrizes quadradas 
Car=rank(A); // ou característica de A
Car2=rank([A b]); // característica de A aumentada

//disp(Det,'det(A)=')
disp(Car,'r(A)=')
disp(Car2,'r(Ab)=')

//S=rref([A b])  // método da condensação de Gauss 
//S_1=inv(A)*b      // método da matriz inversa

//[N D]=rat(S)  // quando a matriz do sistema tem números fracionários
