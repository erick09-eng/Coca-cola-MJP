# Pruebas Software Coca-Cola B2B

Proyecto Maven en Java que implementa los requisitos verificables del documento "Definicion y niveles de las pruebas de software - Coca-Cola B2B".

## Que hace el programa

El programa simula el funcionamiento de una plataforma digital B2B para distribuidores autorizados de Coca-Cola. Implementa cinco requisitos de negocio:

- RF-01: Calcula el subtotal de un pedido. Aplica un descuento del 5 por ciento cuando la cantidad es estrictamente mayor a 50 unidades, redondeando a dos decimales.
- RF-02: Valida que la cantidad solicitada no supere el stock disponible. Si supera el stock, lanza una excepcion StockInsuficienteException.
- RF-03: Genera una orden de reposicion en estado pendiente cuando el inventario actual es menor o igual al stock minimo configurado.
- RF-04: Calcula la tarifa de envio. Cobra una tarifa base de 15000 mas 2000 por cada caja adicional si el pedido excede las 10 cajas.
- RF-05: Actualiza el estado de un pedido de Pendiente a En Ruta cuando el despacho esta confirmado.

El programa funciona por consola (terminal). No tiene interfaz grafica.

## Requisitos previos

Para compilar, ejecutar las pruebas y correr el programa se necesita tener instalado en la computadora:

- Java Development Kit (JDK) version 17 o superior.
- Apache Maven.

Sin estas dos herramientas instaladas, el proyecto no se puede ejecutar.

## Estructura del proyecto (Maven)

```
pom.xml
src/main/java/co/cocacola/b2b/
src/test/java/co/cocacola/b2b/
README.md
```

## Como ejecutar las pruebas unitarias

Desde la raiz del proyecto:

```
mvn test
```

Se ejecutan 11 pruebas unitarias con JUnit 5 que cubren los cinco requisitos.

## Como ejecutar el programa por consola

Desde la raiz del proyecto:

```
mvn exec:java
```

Al ejecutarlo, se muestra un menu con las siguientes opciones:

1. Calcular subtotal con descuento (RF-01): pide la cantidad de cajas y el precio unitario, y muestra el subtotal con descuento si aplica.
2. Validar stock disponible (RF-02): pide la cantidad solicitada y el stock actual, y dice si hay stock suficiente o lanza la excepcion de stock insuficiente.
3. Generar orden de reposicion (RF-03): pide el inventario actual, el stock minimo y el id del producto, y dice si se genera una orden de reposicion en estado pendiente.
4. Calcular tarifa de envio (RF-04): pide la cantidad de cajas y muestra el costo de envio segun la regla de recargo.
5. Actualizar estado del pedido (RF-05): pide el id del pedido y si el despacho esta confirmado, y muestra si el estado queda en Pendiente o En Ruta.
6. Prueba rapida del programa: ejecuta automaticamente todos los requisitos con datos ya definidos dentro del programa (sin que el usuario ingrese nada) y muestra los resultados.
7. Salir: antes de cerrar, pregunta si se desea ejecutar una prueba rapida.

## Como probar el programa

- Para probar individualmente cada requisito, ejecute `mvn exec:java` y elija la opcion correspondiente del menu, ingresando los valores que se le pidan por consola.
- Para ver todos los requisitos funcionando a la vez sin ingresar datos, elija la opcion "Prueba rapida del programa" o responda "s" cuando al salir le pregunten si quiere una prueba rapida.
- Para ejecutar las pruebas automaticas, use `mvn test`.

El programa esta basado en los requisitos del documento analizado y cumple con cada uno de los cinco requisitos verificables (RF-01 a RF-05) descritos alli.
