# RELACION 1
## EJERCICIO 2 
#### Construir un programa concurrente que modele el grafo de sincronización de la Figura 1, utilizando cobegin\coend.
![Imagen](\imagenes\Captura.PNG){width=300 height=400}
Solucion:
```
S1
cobegin
    S3
    begin
        S2
        S4
        cobegin
            S5
            S6
        coend
    end
coend
S7
```
## EJERCICIO 3
#### Dado el siguiente segmento de código concurrente, obtener su grafo de precedencia.
```
s0;
cobegin
    s1;
    begin
        s2;
        cobegin
            s3; s4;
        coend;
        s5;
    end;
    s6;
coend;
s7;
```
Solucion:

![Imagen](\imagenes\Captura2.PNG){width=300 height=400}

## EJERCICIO 5
#### Utilizar las ecuaciones de Bernstein para determinar cúales de las siguientes intrucciones admiten ejecución concurrente:

Ecuaciones de Bernstein:
L1 ∩ E2 = ∅
E1 ∩ L2 = ∅
E1 ∩ E2 = ∅
```
S1: cuad:=x*x;
S2: m1:=a*cuad;
S3: m2:=b*x;
S4: z:=m1+m2;
S5: y:=z+c;`
```
Solucion:
S1 puede con S3, S4 y S5
S2 puede con S3 y S5
S3 puede con S2 y S5
S4 puede con S1
S5 puede con S1, S2 y S3