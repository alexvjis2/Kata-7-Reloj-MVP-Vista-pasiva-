# Revisión 11/07/2019

Existe un posible problema de diseño.

La interfaz Clock define el siguiente contrado:

- Todo reloj debe poder establecerse a una hora (setTime).
- Todo reloj debe poder devolver la hora que representa (getTime).
- Todo reloj debe poder incrementarse en un segundo (tick).

El 3º método es objeto de discusión. Cómo se incrementa el reloj debería ser tarea de las implementaciones concretas. Incluso
queriendo que sea parte de una interfaz que aporte el contrato para relojes externamente incrementables, debería aplicarse el Principio de Segregación de Interfaz. Separando el tercer método
en una nueva interfaz que herede de esta (IncrementableClock o algo así).

En este proyecto además se explota ese 3º método de forma que implica otro posible error de diseño: el presentador es quien 
incrementa cada segundo el reloj, no se incrementa solo. ¿Debería ser el presentador el encargado cada segundo de incrementar 
el reloj? En mi opinión: ni en broma.
