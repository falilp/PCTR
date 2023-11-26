# RELACION 2
## EJERCICIO 1
#### Considerar el siguiente fragmento de programa para dos procesos A y B:
```
Proceso A                  Proceso B
    for a:=1 to 10            for b:= 1 to 10
        x:=x+1;                   x:=x+1;
```
#### Suponer que la variable x está inicializada a cero, que ambos procesos se ejecutan una sola vez y que las variables a y b no son compartidas. Los dos procesos pueden ejecutarse a cualquier velocidad. ¿Cuáles son los posibles valores resultantes para x? Para cada valor, indique la secuencia de entrelazado que lleva a él. Nota: suponga que x debe ser cargada en un registro para incrementarse.

Solucion:
El valor final de x puede variar entre 10 y 20, dependiendo del entrelazado de las instrucciones de ambos procesos. Si ambos procesos se ejecutan de forma intercalada, es decir, que cada proceso ejecuta una instrucción antes de que el otro proceso ejecute su siguiente instrucción, el valor final de x será 10. Si el proceso A ejecuta primero todas sus instrucciones y luego el proceso B, el valor final de x será 20. En cualquier otro caso, el valor final de x estará entre 10 y 20.

## EJERCICIO 2
#### En vez de la instrucción Test and Set, algunos ordenadores proveen de una instrucción atómica que incrementa en 1 el valor de una variable Lock:
```
Function {ATOMICA} TestAndInc (Var Lock: Integer):Integer;
    Begin
        TestAndInc:=Lock;
        Lock:=Lock+1;
    End;
```
#### Escribir protocolos de entrada y salida para proteger una sección crítica utilizando dicha solución.

Solución:
```
Function Entrada():
    Begin
        while( Lock != 0 ) do
            
        end while
    End

Function Salida(): 
    Begin
        Lock:=0;
    End

Lock:=0;
Entrada();
TestAndInc();
Salida();
```
## EJERCICIO 3
#### Escribir un programa concurrente para multiplicar matrices de n×n con n procesos concurrentes. Suponga que los elementos de las matrices se pueden leer simultáneamente. Extienda el programa para realizar concurrentemente el cálculo del producto de ambas diagonales de una matriz.
```
Function diagonalMatrices(var matriz: integer[n][n], var tipo: integer):integer;
    begin
        sol:=0;
        if(tipo == 0) for(integer i=0; i<n; i++)sol+=matriz[i][i];
        else for(integer i=0; i<n; i++)sol+=matriz[n-i][i];
        return sol;
    end
endFunction

main
    producto = diagonalMatrices(matriz,0) * diagonalMatrices(matriz,1);
```
## EJERCICIO 4
#### Escribir un programa para calcular el producto escalar de dos vectores de n componentes. Puede utilizar tantos procesos concurrentes como sea necesario
```
2 vectores
n ventanas = dividir el vector en n partes iguales
0-10
11-20
21-30
```
## EJERCICIO 5
#### ¿Qué diferencia fundamental existe entre un bucle de espera activa y una variable interruptor que da turnos para la sincronización de procesos?
El bucle de espera activa, esta esperando a que se libere su condicion para poder ejecutar/entrar en la seccion critica mientras que hacer instrucciones y el que tiene una variable esta esperando a que se cambien su valor para poder acceder a la seccion critica.

espera ocupada ======= espera activa

## EJERCICIO 6
#### ¿Utiliza la espera ocupada el Algoritmo de Dekker, si el segundo proceso está en su sección crítica, Turno=1 y el primer proceso está intentando entrar en su sección crítica?
Si
## EJERCICIO 7
#### Probar que el Algoritmo de Dekker hace imposible que un proceso espere indefinidamente a entrar en su secci ́on crítica, con la suposicián de que siempre que un proceso entra en la seccián crítica eventualmente la abandonará.