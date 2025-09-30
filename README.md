# Gestor Productos

Aplicacion de gestion de productos y ordenes hecha en el contexto del curso Java Spring 2025.

**Objetivo**: Diseñar una aplicación en Java que permita registrar, mostrar y gestionar productos, así como crear pedidos que involucren varios productos. El sistema deberá hacer uso de variables, operadores, colecciones (listas), POO (clases, objetos, encapsulamiento, herencia, polimorfismo), excepciones y organización de código en paquetes/módulos.

La aplicacion consta de las siguientes entidades:

- Orden: `id, lista de productos` y su respectivo `Service`
- Producto: `id, nombre, precio, stock` y su respectivo `Service`

El programa presentará un menú con opciones en su main, por ejemplo:

```
Agregar producto
Listar productos
Buscar/Actualizar producto
Eliminar producto
Crear un pedido
Listar pedidos (opcionales)
Salir
```