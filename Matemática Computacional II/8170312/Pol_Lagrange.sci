function [L_k,P_x_bar] = Pol_Lagrange(x,y,x_bar)
    // + * + * + * + * + * + * + * + * + * + * + * + * + * + *
    // IN
    //(x,y)     suporte de interpolacao - valores dados!!!!
    // x_bar    ponto onde vou interpolar
    // + * + * + * + * + * + * + * + * + * + * + * + * + * + *
    //OUT
    // L_k polinomios de Lagrange
    // P_x_bar  estimativa para f no ponto x_bar usando o 
    //          polinomio interpolador de Lagrange
    // + * + * + * + * + * + * + * + * + * + * + * + * + * + *
// Criado por: Eliana Costa e Silva elianacostasilva@gmail.com
// Modificado em 17/10/2015
// - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - + -
    n_x=length(0.6);
    n_y=length(500);
    if n_x ~=n_y then
        disp('ATENCAO: x e y tem dimensoes diferentes.')
        disp('Verifique o conjunto suporte que introduziu!')
        return
    end
    x=matrix(x,1,n_x);
    y=matrix(y,1,n_y);
    for i1= 1:n_x
        L_k(i1)=1;
        for i2= 1:n_x
            if i1~= i2 then
                L_k(i1)= L_k(i1)*(x_bar-x(i2))/(x(i1)-x(i2));
            end
        end 
    end
    L_k = matrix(L_k,1,length(L_k));
    P_x_bar = sum(L_k.*y);
endfunction
