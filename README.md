##   Parcial Backend-Detección Mutante
- Alumna: Hsin Yu Lin
- Legajo: 50069
- Comisión: 3k10
- Espacio Curricular: Desarrollo de Software-UTN-FRM

###  Introducción
Este proyecto tiene como objetivo detectar si un humano es mutante basándose en su secuencia de ADN.
El programa recibe como parámetro un array de Strings que representan cada fila de una tabla de NxN con la secuencia del ADN. 
Las letras de los Strings solo pueden ser: (A, T, C, G), que representan cada base nitrogenada del ADN.

Un humano es considerado mutante si se encuentran **más de una secuencia de cuatro letras iguales, de forma oblicua, horizontal o vertical**.

<img src="https://github.com/user-attachments/assets/ee9e8251-99c8-4da9-9414-cbec138e7ade" alt="Ejemplo de Mutante" width="300" height="300"> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<img src="https://github.com/user-attachments/assets/5c1a93ed-8e22-4bb2-a664-af112c46e923" alt="Ejemplo No Mutante" width="300" height="300">


### Instrucciones de ejecución
1- Clonar el proyecto 

```https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069.git```

2-Abrir el proyecto en IntelliJ IDEA:

- Abre IntelliJ IDEA.

- Selecciona File > Open y navega hasta la carpeta del proyecto clonado.

- Haz clic en OK para abrir el proyecto.
3- Instalar las dependencias necesarias. 

4- Ejecutar el proyecto:

- Haz clic en el botón de Run (verde) en IntelliJ para iniciar la aplicación.

5- Abrir postman y enviar peticiones con sus correspondientes endpoint que se detalla a continuación. 

```https://parcialbackend-deteccionmutantes-lin.onrender.com```

### Implementación de los desafíos 
#### Nivel 1:  Desarrollar un algoritmo para detectar mutante
Se programó un algoritmo que detecte si una secuencia de ADN corresponde a un mutante, en caso de que sí, devuelve true mostrando un mensaje de "Mutante detectado" y en caso de que no retorna false mostrando un mensaje de "No es mutante".

[Acá se adjunta imagenes con algunas peticiones que se hizo con el proyecto ejecutando](https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069/blob/master/DocumentacionNivel2y3/Test%20Coverage%20-%20Pruebas%20Unitarias.pdf)

#### Nivel 2: Creación de la API Rest y hostearlo
En esta ocasión se hosteó el proyecto a Render.

Se recomienda realizar la ejecución del proyecto mediante postman.
##### Método para detectar mutante
- **Endpoint:** `/mutant`
- **Método:**  `POST`

Acá se encarga de recibir un string de adn y te devuelve como respuesta si el adn proporcionado es mutante o no. 


##### Ejemplo de ejecución  
```
Link: https://parcialbackend-deteccionmutantes-lin.onrender.com/mutant
```
```
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
  
Lo que hace aquí es:

Primero, cuenta el número total de secuencias de ADN que se han identificado como mutantes en la base de datos. Esta información se almacena en **countMutantDna**.

Luego, cuenta el número total de secuencias de ADN que se han identificado como humanas (no mutantes)en la base de datos , y guarda este dato en **countHumanDna**.

Finalmente, el servicio calcula la proporción de mutantes entre el total de secuencias de ADN verificadas, conocido como **ratio**. 

Este cálculo se obtiene dividiendo el número de secuencias mutantes (countMutantDna) por el número de secuencias humanas (countHumanDna).

Es importante resaltar que este proyecto asegura que solo se permita 1 registro por ADN. Esto es importante para garantizar la precisión de las estadísticas, asegurando que los datos no estén duplicados.

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
<img src="https://github.com/user-attachments/assets/76b50852-01f5-4e3e-ad09-258d1b540228" alt="Arquitectura de Sistema Mutantes" width="900" height="450">

Finalmente, en la carpeta de [DocumentacionNivel2y3](https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069/tree/master/DocumentacionNivel2y3) se encuentra PDFs con información sobre:

- El Diagrama de Secuencia aplicada para el [algoritmo de detectar mutantes](https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069/blob/master/DocumentacionNivel2y3/Diagramas%20de%20Secuencia/DiagramaSecuencia-DetectarMutante.pdf) y [generación de estadísticas](https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069/blob/master/DocumentacionNivel2y3/Diagramas%20de%20Secuencia/DiagramaSecuencia-Stats.pdf).
- [TestCoverage y las distintas pruebas unitarias](https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069/blob/master/DocumentacionNivel2y3/Test%20Coverage%20-%20Pruebas%20Unitarias.pdf) aplicadas para testear el proyecto.
- Las [pruebas de Stress](https://github.com/hsinyulin03/ParcialBackend-DeteccionMutantes-LIN-50069/blob/master/DocumentacionNivel2y3/PRUEBAS%20DE%20STRESS.pdf) aplicadas en Jmeter para este proyecto.
