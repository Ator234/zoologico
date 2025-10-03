# Gestión de Zoológico

Este proyecto consiste en una aplicación de escritorio para que un zoológico lleve un control sobre los cuidados de los distintos animales y el trabajo de sus empleados.  
La aplicación utiliza Jakarta Persistence e Hibernate para la persistencia de datos y Swing (javax) para la interfaz gráfica.

## Entidades principales

### Empleado
- **DNI** (String)  
- **Profesión** (Enum: Veterinario, Mantenimiento, Educador)  
- **Nombre** (String)  
- **Experiencia** (int)  

### Animal
- **NúmeroChip** (int)  
- **Apodo** (String)  
- **Parcela** (int)  
- **Raza** (String)  

### Interacción
- **Id** (int)  
- **DNI** (String)  
- **NúmeroChip** (int)  
- **Motivo** (Enum: controlSalud, Limpieza, Alimentación, Adiestramiento)  
- **Fecha** (Date)  

## Arquitectura

El programa está desarrollado en Java, siguiendo el patrón Modelo-Vista-Controlador (MVC):

- **Modelo:** gestionado con Hibernate para mapear las entidades a la base de datos.  
- **Vista:** construida con Swing, proporcionando paneles diferenciados para animales, empleados, interacciones y consultas.  
- **Controlador:** orquesta la lógica entre la interfaz gráfica y la base de datos.  

## Funcionalidades principales

- **Gestión de animales:** alta, baja, modificación y consulta de los animales del zoológico.  
- **Gestión de empleados:** alta, baja, modificación y consulta del personal.  
- **Gestión de interacciones:** registro de interacciones entre empleados y animales, asociadas a un motivo y fecha.  
- **Consultas avanzadas sobre la base de datos**, entre ellas:  
  - Interacciones de un animal concreto.  
  - Interacciones de un empleado.  
  - Controles de salud de un animal.  
  - Empleados filtrados por profesión.  
  - Interacciones realizadas en una fecha determinada.  
  - Animales que no han recibido alimentación en el día.  

## Tecnologías utilizadas

- Java 25  
- Jakarta Persistence / Hibernate (para la persistencia)  
- Swing (javax.swing) (para la interfaz gráfica)  
- Base de datos relacional (compatible con Hibernate)  

## Estructura del proyecto

- **model:** clases de entidad (Empleado, Animal, Interacción, enums).  
- **dao:** capa de acceso a datos mediante Hibernate.  
- **view:** paneles y ventanas Swing.  
- **controller:** controladores que conectan la vista con el modelo.  
