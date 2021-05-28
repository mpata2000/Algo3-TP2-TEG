# Docker

Esta guia presenta los pasos necesarios para configurar el ambiente de desarrollo utilizando Docker.

## Pre-requisitos

Lista de herramientas necesarias:

1. [docker](https://docs.docker.com/get-docker/)
1. [docker-compose](https://docs.docker.com/compose/install/)

## Inicio

```bash
$ docker/up.sh   # En Linux/Mac
$ docker\up.cmd  # En Windows
```

Una vez dentro del contenedor, podemos ejecutar los siguientes comandos (es un contenedor Linux):

### 1. Pruebas

```bash
$ scripts/build.sh
```

Este comando crea el reporte de cobertura para CI y el reporte HTML, que se puede encontrar en `target/site/jacoco/index.html`.

### 2. Empaquetado

```bash
$ scripts/package.sh
```

Esto genera el empaquetado en `target/tp2-0.0.1.jar`.

### 3. Ejecución de la aplicación

Luego (desde afuera del contenedor) podemos ejecutar la aplicación con:

```bash
$ java -jar target/tp2-0.0.1.jar
```

## Finalización

Para finalizar el contenedor (desde otra terminal fuera del contenedor):

```bash
$ docker/down.sh   # En Linux/Mac
$ docker\down.cmd  # En Windows
```

