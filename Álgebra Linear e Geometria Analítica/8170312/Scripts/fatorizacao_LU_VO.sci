function [x]=fatorizacao_LU(L,U,b)
    
    [n,m]=size(L);
    
    x(n,1)=0;
    
    for i=1:n-1
       
        for j=i+1:n
            
            m=L(j,i);
            
            b(j)=b(j)-b(i)*m;

        end
    end
     
    for i=n:-1:1
        
        soma=0;
        
        for j=i+1:n
            
            soma=soma+U(i,j)*x(j,1);
            
        end
        
        x(i,1)=(b(i)-soma)/U(i,i);
        
    end
    
endfunction
