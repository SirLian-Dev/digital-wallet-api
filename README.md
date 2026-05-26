# 💰 Digital Wallet API - Core Backend

Este proyecto es una API REST robusta para gestionar una billetera digital, desarrollada con **Java** y **Spring Boot**. Diseñada bajo una arquitectura de capas, asegurando la integridad de las transacciones financieras y la escalabilidad del código.

## 🛠️ Tecnologías Utilizadas
- **Java 17+**
- **Spring Boot 3** (Spring Data JPA, Spring Web, Validation)
- **PostgreSQL** (Base de datos relacional)
- **Lombok** (Reducción de código Repetitivo(Boilerplate))
- **Git** (Control de versiones)

## 🌟 Funcionalidades Principales
- **Gestión de Usuarios:** Registro con validación de datos y creación automática de cuenta bancaria única.
- **Transacciones Seguras:** Implementación de `@Transactional` para garantizar que las transferencias entre cuentas sean atómicas (o se completan ambas partes o no se hace nada).
- **Lógica Financiera:** Uso de `BigDecimal` para evitar errores de precisión decimal.
- **Historial de Auditoría:** Registro automático de cada movimiento con fecha, tipo y cuenta destino.
- **Manejo Global de Errores:** Respuestas estandarizadas en formato JSON para excepciones de negocio.

## 🚀 Instalación y Configuración
1. Clonar el repositorio.
2. Configurar las credenciales de PostgreSQL en `src/main/resources/application.properties`.
3. Crear base de datos como `wallet_db`
4. Ejecutar `./mvnw spring-boot:run` (o desde tu IDE).

## 📁 Estructura del Proyecto
- `controller`: Puntos de entrada de la API.
- `service`: Lógica de negocio y reglas financieras.
- `model`: Entidades de base de datos.
- `repository`: Interfaces para acceso a datos (JPA).
- `dto`: Objetos de transferencia de datos.
- `exception`: Manejo centralizado de errores.