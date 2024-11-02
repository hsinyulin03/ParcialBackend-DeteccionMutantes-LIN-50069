##   Parcial Backend-Deteccion Mutante
- Alumna: Hsin Yu Lin
- Legajo: 50069
- Comisión:3k10
- Espacio Curricular: Desarrollo de Software-UTN-FRM

###  Introducción
Este proyecto tiene como objetivo detectar si un humano es mutante basándose en su secuencia de ADN.
El programa recibe como parámetro un array de Strings que representan cada fila de una tabla de NxN con la secuencia del ADN. 
Las letras de los Strings solo pueden ser: (A, T, C, G), que representan cada base nitrogenada del ADN.

Un humano es considerado mutante si se encuentran **más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical**.

<img src="https://github.com/user-attachments/assets/ee9e8251-99c8-4da9-9414-cbec138e7ade" alt="Ejemplo de Mutante" width="300" height="300"> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/5c1a93ed-8e22-4bb2-a664-af112c46e923" alt="Ejemplo No Mutante" width="300" height="300">

### Implementación de los desafíos
#### Nivel 1:  Desarrollar un algoritmo para detectar mutante
Se programó un algoritmo que detecte si una secuencia de ADN corresponde a un mutante, en caso de que sí devuelve true mostrando un mensaje de "Mutante detectado" y en caso de que no retorna false mostrando un mensaje de "No es mutante".

#### Nivel 2: Creación de la API Rest y hostearlo
En esta ocasión se hosteó el proyecto a Render.
Se recomienda realizar la ejecución del proyecto mediante postman.
##### Método para detectar mutante
- **Endpoint:** `/mutant`
- **Método:**  `POST`
Acá se encarga de recibir un string de adn y te devuelve como respuesta si el adn proporcionado es mutante o no. 
Ejemplo:


##### Ejemplo de ejecución  
```
Link: https://parcialbackend-deteccionmutantes-lin.onrender.com/mutant
```
```
Array de String de ADN
{ 
 “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] 
}
```

Respuestas: 

   - Retornar **HTTP 200 OK** si se detecta un mutante. 
   - Retornar **HTTP 403 Forbidden**si no es mutante. 
   - Retornar **HTTP 400 BAD_REQUEST** en caso de que se produzca una excepción por introducir un ADN no válido o por alguna razón extra.

#### Nivel 3:  Base de Datos y Estadísticas
- **Endpoint:** `/stats`
- **Método:**  `GET`
Primero, cuenta el número total de secuencias de ADN que se han identificado como mutantes en la base de datos. Esta información se almacena en **countMutantDna**.

Luego, cuenta el número total de secuencias de ADN que se han identificado como humanas (no mutantes)en la base de datos , y guarda este dato en **countHumanDna**.

Finalmente, el servicio calcula la proporción de mutantes entre el total de secuencias de ADN verificadas, conocido como **ratio**. 
Este cálculo se obtiene dividiendo el número de secuencias mutantes (countMutantDna) por el número de secuencias humanas (countHumanDna).

##### Ejemplo de ejecución  
```
Link: https://parcialbackend-deteccionmutantes-lin.onrender.com/stats
```
```
{ 
"countMutantDna":3,
"countHumanDna":2,
"ratio":1.5
}
```

Para este proyecto, se ha optado por utilizar la base de datos H2 como motor de persistencia.

#### Arquitectura del Sistema
<img src="https://github.com/user-attachments/assets/1e69f3e1-2827-4bd0-995d-d5dd2690cd5b" alt="Arquitectura de Sistema Mutantes" width="900" height="450">

Finalmente, en la carpeta de DocumentacionNivel2y3 se encuentra PDFs con información sobre:

- Las pruebas de Stress aplicadas para este proyecto.
- El Diagrama de Secuencia aplicada para el algoritmo de detectar mutantes y generación de estadísticas.
- TestCoverage y las distintas pruebas unitarias aplicadas para testear el proyecto.
