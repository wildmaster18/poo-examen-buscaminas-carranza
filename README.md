# Buscaminas – Examen Práctico POO

Juego de consola que implementa el clásico *Buscaminas* de 10 × 10 casillas y 10 minas aplicando los principios de:

* **MVC** - separación Modelo / Vista / Controlador  
* **POO** - encapsulamiento, herencia, polimorfismo  
* **KISS** - interfaz y lógica simplificadas  
* **TDD** - desarrollo guiado por pruebas (JUnit 5)

---

## Estructura del proyecto

src/

├─ main

│ └─ java

│ └─ ups/buscaminas

│ ├─ modelo/… ← lógica de juego

│ ├─ vista/ConsolaVista.java

│ ├─ controlador/JuegoControlador.java

│ └─ persistencia/GameIO.java

└─ test

└─ java/ups/buscaminas/test ← 5 pruebas JUnit 5


---

## Instalación y ejecución

> Requiere **JDK 8** o superior

# Ejecutar el juego

Carpeta: ups.buscaminas

Ejecutable: Main.java

## Comandos en el juego

| Entrada | Acción                                  |
| ------- | --------------------------------------- |
| `A5`    | Descubre la celda A-5                   |
| `M B7`  | Marca / desmarca la celda B-7           |
| `G`     | Guarda la partida (`~/buscaminas.save`) |
| `C`     | Carga la partida guardada               |
| `Q`     | Sale del juego                          |

---

## Pruebas unitarias

| Test                  | Caso cubierto                                   |
| --------------------- | ----------------------------------------------- |
| `TableroTest`         | Generación exacta de 10 minas                   |
| `JuegoDerrotaTest`    | Derrota al pisar mina                           |
| `JuegoVictoriaTest`   | Victoria al descubrir todas las celdas seguras  |
| `MarcadoContadorTest` | Contador de minas restantes al marcar/desmarcar |
| `PersistenciaTest`    | Guardar y cargar restauran el estado            |

---

## Principios aplicados

KISS – entrada mínima (A5, M B7), persistencia binaria estándar.

DRY – mensaje de error único (AYUDA_ERROR) reutilizado en todo el controlador.

TDD – todos los requisitos implementados partiendo de pruebas rojas.

MVC – separación clara de responsabilidades.

---

## Autor

Mateo Sebastian Carranza Ortiz
