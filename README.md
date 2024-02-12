# AE2.ManejoDeConectoresJDBC

![L1](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/3c5f8a29-8c6c-4cac-a134-bbfc3d5ce2d9)

## AE-2. Manejo de conectores de BBDD (JDBC)
## Asignatura : Acceso a Datos
## Grupo : 8
## Integrantes : 
Cristian Quiceno Laverde
Gabriela Prieto Herrera
Libertad Gamarra La Rosa
## Repositorio Git: https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC.git
## Metodología:
Iniciamos el proyecto definiendo requisitos y diseñando el modelo de datos, estableciendo clases de
entidad y relaciones. En este proceso colaborativo, cada uno de nosotros se centró en desarrollar
clases y DAO específicos, asumiendo todas las responsabilidades, desde la lógica de negocio hasta la
interfaz de usuario. Se ha adoptado el patrón MVC para organizar claramente nuestro código. A lo
largo del desarrollo, nos brindamos apoyo mutuo para encontrar soluciones y ajustar el código,
siempre priorizando la claridad, eficiencia y modularidad.
Cada integrante del equipo asumió la tarea de desarrollar la aplicación de manera individual,
completando todas las tareas asignadas. Subimos cada versión a GitHub, donde evaluamos y
comparamos para seleccionar la más destacada. La elección se basó en criterios como la estructura
del código, eficiencia y claridad, asegurando que el código elegido representará lo mejor de nuestro
esfuerzo colectivo.
## Enunciado:
Objetivos
Aprender a manejar JDBC mediante una pequeña aplicación de gestión de coches
Pautas de elaboración
Requerimiento 1
Se desea hacer un CRUD completo de la entidad ‘Coche’, pero esta vez no se trabajará con ningún
fichero, se trabajará con una BBDD. Es muy importante usar el patrón DAO visto en clase. Los
parámetros de conexión a la BBDD deben estar hechos en un fichero de propiedades.
El coche tendrá los siguientes atributos: id, marca, modelo, año de fabricación y km.
El menú mostrado será de la siguiente forma:
● Añadir nuevo coche (El ID lo incrementará automáticamente la base de datos)
● Borrar coche por ID
● Consulta coche por ID
● Modificar coche por ID (pedirá todos los valores y modificará dichos valores a partir del ID
del coche)
● Listado de coches
● Terminar el programa
Requerimiento 2
Se pide añadir la siguiente funcionalidad.
Los coches, tendrán asociados N pasajeros en él (habrá que crear la tabla pasajeros y hacer la
relación pertinente). Los pasajeros tendrán los siguientes atributos, id, nombre, edad y peso. Se
añadirá la opción “gestión de pasajeros” al programa principal, dicha opción nos mostrará un submenú
como el que sigue
● Crear nuevo pasajero
● Borrar pasajero por id
● Consulta pasajero por id
● Listar todos los pasajeros
● Añadir pasajero a coche, el programa nos pedirá un id de un pasajero y el id de un coche,
y lo añadirá al coche a nivel de base de datos. Sería una buena opción mostrar todos los
coches disponibles.
● Eliminar un pasajero de un coche, el programa nos pedirá un id de un pasajero y lo
eliminará del coche a nivel de base de datos. Sería una buena opción mostrar todos los
coches y sus pasajeros asociados.
● Listar todos los pasajeros de un coche, el programa pedirá el id de un coche, y nos
mostrará todos los pasajeros asociados a él.
Requerimiento 3
La aplicación no debe permitir que la marca y el modelo estén vacíos. Esta parte la debe de gestionar
la capa gestora y seguir el modelo de tres capas visto en clase.

##  Contenido:

![L2](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/f19371a2-b6d3-4f95-bbb1-76582f25074b)

Requerimiento 1
1) Definimos la entidad 'Coche':
● Se ha creado una descripción de la clase Coche con los atributos específicos como id,
marca, modelo, año de fabricación y kilómetros.

![L3](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/55d7b319-49d9-4e49-b408-3fc329d99695)

2) Implementamos el patrón DAO:
● Se ha establecido una interfaz llamada DAOCoche con métodos como agregarCoche,
borrarCoche, consultarCochePorId, modificarCoche y listarCoches.

![L4](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/ae7c5852-c6e3-4bc3-828b-21e289180772)

● Se ha desarrollado la clase DAOCocheMysql que se encargó de interactuar con la
base de datos, implementando los métodos de la interfaz.

![L5](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/8b12aad8-27e7-45f0-ba62-e347b2550608)

3) Conexión a la base de datos:
● Se ha definido un archivo de propiedades que almacena los parámetros necesarios
para la conexión a la base de datos.
En este punto, hemos tenido problemas para conectar a la base de datos a través del fichero
propiedades, ya que dentro del fichero no reconoce el driver, al no poder solventar el error en
ninguna de las versiones, hemos decidido realizar los cambios correspondientes para
solventar el error de conexión, manteniendo la clase LecturaPropierties y sus atributos, pero se
han añadido los parámetros para la lectura de la base de datos a través de variables locales
de la clase, añadidas directamente al DriverManager.

![L6](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/cc201cd6-52d5-4460-8beb-6148a2514fe2)

4) Clase Controlador.
● Se ha creado la clase controlador que facilita la interacción entre la interfaz de usuario
y la capa de persistencia, permitiendo realizar operaciones CRUD en las entidades de
coches y pasajeros. Además, gestiona la apertura y cierre de conexiones a las bases
de datos.

![L7](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/c5da1cd9-0e5e-477e-b9ca-bed1710bbc5b)

7) Menú principal:
● Se ha diseñado una clase MainMenu que ofrece opciones para realizar operaciones
como añadir, borrar, consultar, modificar y listar coches.

![L8](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/275d8e52-73b7-44df-83b9-819657ce1bbc)

Requerimiento 2
1) Definimos la entidad 'Pasajeros':
● Se ha creado una descripción de la clase Pasajeros con atributos como id, nombre,
edad y peso.

![L9](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/2ed3856c-d6a3-4d0f-9d30-41a79660346d)

2) Se han creado dos clases para implementar el patrón DAO de la clase pasajeros.:
● Se ha creado la interfaz DAOPasajeros para incluir los métodos CRUD relacionados
con los pasajeros.

![L10](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/9120552f-88d3-4c15-aec1-b76c2348c4d6)

● Se ha creado la clase DAOPasajerosMysql para implementar los métodos CRUD de la
interfaz, que gestionarán la conexión y la interacción directamente con la base de
datos SQL.

![L11](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/49578fbf-8050-4969-b335-143b4d495448)
![L12](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/1cce901b-4be3-4e39-886d-e6eb7bfa53c6)

3) Submenú para gestión de pasajeros:
● Se ha creado un submenú que permite realizar acciones como crear, borrar, consultar
y listar pasajeros.

![L13](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/973709a4-982a-4c63-bcbe-e2906123ccde)

● Se han implementado opciones para añadir y eliminar pasajeros de un coche, así
como para listar todos los pasajeros de un coche.

![L14](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/53227ca1-e428-4a13-9b35-334b3bfe54ca)
![L15](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/e827ba01-c6d6-4de9-99c2-e1b115f70df1)

Requerimiento 3
● Se ha evitado continuar con el programa siempre que la marca y el modelo estén vacíos. Para
evitar este comportamiento se ha hecho una triple comprobación.
Primero, en Base de datos, se han establecido los valores de las columnas atributos a NOT NULL.
ALTER TABLE Coche MODIFY marca VARCHAR(255) NOT NULL;
ALTER TABLE Coche MODIFY modelo VARCHAR(255) NOT NULL;
ALTER TABLE Coche MODIFY año INT NOT NULL;
ALTER TABLE Coche MODIFY KILOMETROS DOUBLE NOT NULL;
Así mismo, en la clase Controlador, que gestiona la comunicación entre todas las clases, se ha
añadido una verificación para este comportamiento indebido.

![L16](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/8b92cefe-aab2-4347-afbc-dbea9233bc93)

Por último, se ha realizado otra validación directamente en el menú. De esta forma, si no están
completos estos campos, directamente se insta al usuario a que lo rellene, en forma de bucle hasta
que se rectifique el comportamiento indebido.

![L17](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/e98422ea-f03d-4ccf-93ab-bdf72227ae11)


![L18](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/33dbb471-19f0-4399-9f36-75a53a037d64)


![L19](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/492f1b77-6617-44ee-91ba-c4b68e14fce7)

![L20](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/e6a13da7-3fe8-41b4-8504-7071d959e107)


![L21](https://github.com/GabrielaPrietoH/AE2.ManejoDeConectoresJDBC/assets/124418682/87c660f8-0b3f-4ef9-b4d3-75af062c68d6)


## Conclusión:
En conclusión, el desarrollo de la aplicación de gestión de coches y pasajeros se llevó a cabo de
manera colaborativa y estructurada. Cada integrante del equipo asumió responsabilidades
individuales, abordando desde la lógica de negocio hasta la interfaz de usuario, lo que permitió una
comprensión profunda de todas las capas de la aplicación. La adopción del patrón MVC facilitó la
organización clara del código, contribuyendo a la eficiencia y claridad del proyecto.
La metodología de desarrollo, centrada en la colaboración y apoyo mutuo, demostró ser efectiva para
abordar los desafíos y ajustes necesarios durante el proceso. La elección del mejor código, basada en
criterios como estructura, eficiencia y claridad, refleja el compromiso del equipo con la calidad del
trabajo. En resumen, la tarea no solo fortaleció nuestro entendimiento de Java y sus clases API, sino
también nuestras habilidades de trabajo en equipo y desarrollo de software de manera efectiva y
eficiente
