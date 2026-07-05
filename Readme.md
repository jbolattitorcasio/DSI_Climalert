# DSI - Climalert

Sistema de monitoreo climático y envío automático de alertas desarrollado con Spring Boot.

---

# Índice

1. [Información del alumno](#información-del-alumno)
2. [Consigna](#consigna)
5. [Variables de entorno](#variables-de-entorno)

---

# Información del alumno

- **Alumno:** Juan Cruz Bolatti Torcasio
- **Usuario Github:** @jbolattitorcasio
- **Materia:** Diseño de Sistemas de Información

---

# Consigna

Nos han encargado el diseño y desarrollo de Climalert, un Sistema de monitoreo climático y envío
automático de alertas.
Climalert funcionará como un servicio autónomo, sin interfaz gráfica, cuya responsabilidad es conectarse
periódicamente a un proveedor meteorológico externo, procesar los datos recibidos y notificar por correo
electrónico a las entidades correspondientes cuando se detecten condiciones climáticas peligrosas o
inusuales. Para esta primera iteración solamente consideraremos como “alerta” a una temperatura mayor a
35° y una humedad superior a 60%.

El Sistema debe desarrollarse utilizando Spring Boot y deberán tenerse en cuenta los siguientes puntos:
1. Integración con proveedor externo de clima
   - El sistema deberá integrarse vía REST con WeatherAPI mediante su endpoint /current.json.
   - La ubicación consultada será fija (por ejemplo: CABA).
   - Cada 5 minutos, el Sistema deberá obtener los datos climáticos actuales y almacenarlos
   localmente para registro histórico y análisis posterior.

2. Procesamiento de alertas meteorológicas
   - Cada 1 minuto, el Sistema deberá analizar la última información disponible del clima.
   - Si se detectan condiciones críticas deberá generarse una alerta (ver siguiente punto).
3. Notificación por correo electrónico
   - Al generarse una alerta, el Sistema deberá enviar un correo a los siguientes destinatarios:
     - admin@clima.com
     - emergencias@clima.com
     - meteorologia@clima.com
   - El correo deberá incluir el detalle completo del clima.

---

# Variables de entorno

Para ejecutar correctamente el proyecto es necesario definir las siguientes variables de entorno.

| Variable | Descripción |
|----------|-------------|
| `WEATHER_API_KEY` | API Key de WeatherAPI. |
| `MAILTRAP_USER` | Usuario SMTP de Mailtrap. |
| `MAILTRAP_PASSWORD` | Contraseña SMTP de Mailtrap. |

## Obtener la API de WeatherAPI

1. Crear una cuenta en https://www.weatherapi.com/.
2. Copiar la API Key.
3. Configurarla en `WEATHER_API_KEY`.

## Obtener las credenciales de Mailtrap

1. Crear una cuenta en https://mailtrap.io/.
2. Crear un Inbox (o utilizar uno existente).
3. Ir a **SMTP Settings**.
4. Copiar el **Username**.
5. Copiar el **Password**.

## Configuración en IntelliJ IDEA

En la configuración de ejecución agregar:

```text
WEATHER_API_KEY=tu_api_key
MAILTRAP_USER=tu_usuario
MAILTRAP_PASSWORD=tu_password
```


El proyecto utiliza automáticamente estas variables mediante:

```properties
weather.api.key=${WEATHER_API_KEY}
spring.mail.username=${MAILTRAP_USER}
spring.mail.password=${MAILTRAP_PASSWORD}
```

---
