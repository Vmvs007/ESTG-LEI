//Matriz A e B
//a0 a1 a2 a3 b0 b1 b2 b3 c0 c1 c2 c3
A=[100,0,0,0,10,0 0 0 1 0 0 0;
0 100 0 0 0 10 0 0 0 1 0 0;
0 400 0 0 0 20 0 0 0 1 0 0;
0 0 400 0 0 0 20 0 0 0 1 0;
0 0 900 0 0 0 30 0 0 0 1 0;
0 0  0 900 0 0 0 30 0 0 0 1;
1 0  0  0 1 0 0  0  1 0 0 0;
0 0 0 1600 0 0 0 40 0 0 0 1;
20 -20 0 0 1 -1 0 0 0 0 0 0;
0  40 -40 0 0 1 -1 0 0 0 0 0;
0  0  60 -60 0 0 1 -1 0 0 0 0;
1  0  0 0 0 0 0 0 0 0 0 0]

B=[30;30;-10;-10;20;20;1;40;0;0;0;0]

// resolver o sistema
rref([A B])

//Solução
//a0 a1 a2 a3 b0 b1 b2 b3 c0 c1 c2 c3
//a0=0.         
//a1=  - 0.7222222  
//a2=    1.4222222  
//a3=  - 1.5222222  
    3.2222222  
    17.666667  
  - 68.111111  
    108.55556  
  - 2.2222222  
  - 74.444444  
    783.33333  
  - 1866.6667




