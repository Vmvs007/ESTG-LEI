function [Tabela,P_x_bar] = Pol_Newton_DD(x,y,x_bar)
    // * + * + * + * + * + * + * + * + * + * + * +
    // IN
    // (x,y) suporte de interpolacao - valores dados!!!!
    // x_bar ponto onde vou interpolar
    // * + * + * + * + * + * + * + * + * + * + * +
    // OUT
    // Tabela - tabela de diferencas divididas
    // P_x_bar - aproximacao usando o pol de Newton no ponto x_bar
    // * + * + * + * + * + * + * + * + * + * + * +
// Criado por: Eliana Costa e Silva elianacostasilva@gmail.com
// Modificado em 17/10/2015
// - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - +  - + -
    P_x_bar = 0;    
    x=matrix(x,length(x),1);
    y=matrix(y,length(y),1);
    nlinhas = length(x);
    ncol = nlinhas +1;
    Tabela = zeros(nlinhas,ncol);
    if length(x) ~=length(y) then
        disp('ATENCAO: x e y tem dimensoes diferentes.')
        disp('Verifique o conjunto suporte que introduziu!')
        return
    end
    Tabela(:,1) = x;
    Tabela(:,2) = y;
    for ii =1:nlinhas-1 // percorre as colunas
        for jj = 1:nlinhas-ii //percorre as linhas
            Tabela(jj,ii+2) = (Tabela(jj+1,ii+1)-Tabela(jj,ii+1))/(x(jj+ii)-x(jj));
        end        
    end  
    // aproximacao
    P_x_bar = Tabela(1,2);
    aux=1;
    for ii =1:nlinhas-1
        aux = aux*(x_bar-x(ii));
        P_x_bar = P_x_bar + Tabela(1,ii+2)*aux;
    end
  
    
endfunction
