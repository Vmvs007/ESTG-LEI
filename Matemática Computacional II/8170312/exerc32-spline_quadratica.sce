x_nos = [1 2 5 7];
y_nos = [1 2 3 2.5];


A=[(x_nos(2))^2  0 0 x_nos(2) 0 0 1 0 0;
0 (x_nos(2))^2  0 0 x_nos(2) 0 0 1 0;
0 (x_nos(3))^2  0 0 x_nos(3) 0 0 1 0;
0 0 (x_nos(3))^2  0 0 x_nos(3) 0 0 1;
//
(x_nos(1))^2  0 0 x_nos(1) 0 0 1 0 0;
0 0 (x_nos(4))^2  0 0 x_nos(4) 0 0 1;
//
2*x_nos(2) -2*x_nos(2) 0 1 -1 0 0 0 0;
0 2*x_nos(3) -2*x_nos(3) 0 1 -1 0 0 0;
//
2 zeros(1,8)
]

B=[y_nos(2)
y_nos(2)
y_nos(3)
y_nos(3)
//
y_nos(1)
y_nos(4)
0
0
0
]
sol=rref([A B])

[N,D] = rat(sol(:,$))

