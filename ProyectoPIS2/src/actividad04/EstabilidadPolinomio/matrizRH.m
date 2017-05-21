function [] = matrizRH(polinomio)

tam = length(polinomio);
n = round(tam/2);
q = 1;
k = 0;
for i = 1: length(polinomio) 
    if rem(i, 2) == 0
        par(k)= polinomio(i); 
    else
        impar(q) = polinomio(i); 

        k = k+1;
        q = q+1;
    end
end
matriz = zeros(tam,n); 

if tam/2 ~= round(tam/2)
    par(n) = 0;
end
matriz(1,:) = impar;
matriz(2,:) = par;


for i = 3: tam
    for j = 1: n-1
        x = matriz(i-1,1);
        matriz(i,j) = ((matriz(i-1,1)*matriz(i-2,j+1))-(matriz(i-2,1)*matriz(i-1,j+1)))/x;
    end
    if matriz(i,:) == 0
        orden = (tam-i+1);
        c = 0;
        d = 1;
        for j = 1: n-1
            matriz(i,j) = (orden-c)*(matriz(i-1,d));
            d = d+1;
            c = c+2;
        end
    end
end
PolosDerechos = 0;
for i = 1: tam-1
    if sign(matriz(i,1))*sign(matriz(i+1,1)) == -1
        PolosDerechos = PolosDerechos+1;
    end
end
fprintf('\n Matriz de Routh-Hurwitz:\n')
matriz
fprintf('\n Nº de Raíces  en el semiplano derecho =%2.0f\n',PolosDerechos)
end

