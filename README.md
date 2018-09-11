# Marvel Heroes 💪🏻

Práctica Android Avanzado

La práctica se ha hecho con la app demo de Marvel Heroes como base (​https://github.com/costular/marvel-super-heroes​) y se han aplicado ciertos cambios.
Tareas
A continuación, se detallan las tareas realizadas para el desarrollo de la práctica.
Aplicar la arquitectura MVVM
La aplicación estaba basada en MVP y se ha implementado la nueva arquitectura ​MVVM ​con el uso de ​ViewModel.

Añadir base de datos
La aplicación ahora funciona con la API REST sencilla que consumismo y con una BBDD local. He añadido una base de datos con ​Room​ para añadir una caché. Y se han gestionado todos los cambios pertinentes en el ​Repository​.
Acutalmente se pueden insertar, borrar y updatear los diferentes registros
Favoritos
Para el apartado de favoritos, he añadido una opción en el detalle que nos permite poner comentarios sobre el personaje favorito.
Estos comentarios son guardados en la BBDD.
En la pantalla principal de resumen aparecere el comentario insertado en el detalle.
