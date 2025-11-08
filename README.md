Conversor de Monedas Alura-ONE
Descripción

Este proyecto es un conversor de monedas desarrollado en Java, que permite realizar conversiones rápidas, consultar códigos de monedas y hacer conversiones personalizadas.
El programa utiliza la API de ExchangeRate para obtener las tasas de cambio en tiempo real y permite guardar cada conversión personalizada en un archivo JSON con timestamp para futura referencia.

Características principales

Conversión rápida de 6 monedas más utilizadas a partir del USD.

Consulta de todos los códigos de monedas soportadas con su respectiva bandera.

Conversión personalizada entre cualquier par de monedas soportadas.

Guardado de conversiones personalizadas en archivos JSON dentro de la carpeta consultas.

Interfaz de consola con colores y estilos para mejorar la legibilidad.

Uso de clases utilitarias para manejo de consola, menú y visualización de banderas.

Tecnologías utilizadas

Java 17+

API REST de ExchangeRate

Gson (para parseo de JSON)

Mapas y Records de Java 17

Códigos ANSI para colores y estilos en consola

Estructura del proyecto
src/
│
├─ model/
│   ├─ ExchangeRateResponse.java      # Representa la respuesta de la API para conversiones
│   └─ SupportedCodesResponse.java    # Representa la lista de códigos de monedas soportadas
│
├─ service/
│   └─ ExchangeRateService.java       # Encapsula las llamadas HTTP a la API
│
├─ utils/
│   ├─ ConsoleUtils.java              # Códigos de colores, estilos y métodos de formateo de consola
│   ├─ ExchangeRateUtils.java         # Map de banderas y método para mostrar códigos de monedas
│   ├─ JsonFileWriter.java            # Guarda conversiones personalizadas en archivos JSON
│   └─ MenuUtils.java                 # Construye el menú de la consola y líneas divisorias
│
└─ Principal.java                     # Clase principal con flujo de ejecución del programa

Instalación y ejecución

Clonar el repositorio:

git clone https://github.com/tu_usuario/conversor-monedas.git


Entrar a la carpeta del proyecto:

cd conversor-monedas


Compilar el proyecto:

javac -d bin src/**/*.java


Ejecutar el proyecto:

java -cp bin Principal


Al ejecutar, verás un menú de consola interactivo con opciones de conversión rápida, consulta de códigos y conversión personalizada.

Uso
Caso 1 – Conversiones rápidas

Muestra las conversiones de USD a 6 monedas: ARS, BRL, CAD, CHF, CLP, EUR.

Caso 2 – Ver códigos de monedas

Muestra todos los códigos de monedas soportadas junto con su bandera.

Caso 3 – Conversión personalizada

Ingresar moneda base (ej: USD)

Ingresar moneda destino (ej: ARS)

Se muestra la conversión y se guarda en consultas/ como JSON con timestamp.

Se puede ingresar un monto para calcular su equivalente en la moneda destino.

Carpeta consultas

Cada conversión personalizada se guarda automáticamente en:

consultas/yyyy-MM-dd_HH_mm_ss_USD_to_ARS.json


Contiene toda la información de la conversión obtenida de la API.

Permite llevar un historial de conversiones.

Ejemplo de salida en consola
| 📊 1 - Muestra rápida de 6 conversiones
| 💱 2 - Ver Códigos de cambios disponibles
| 🧮 3 - Realizar cambio personalizado
| 👋 4 - Salir


Mensajes de éxito en verde, errores en rojo, información en azul.

Conversiones con símbolos y formato de consola legible.

Notas de desarrollo

Las variables static final se usan para constantes compartidas (ej: ICONOS, FLAGS).

El Map FLAGS mapea cada código de moneda a su emoji de bandera.

ExchangeRateService maneja la comunicación con la API y convierte JSON a objetos Java usando Gson.

JsonFileWriter guarda los resultados con fecha y hora, permitiendo organizar los archivos automáticamente.

Licencia

MIT License – Libre uso y modificación.