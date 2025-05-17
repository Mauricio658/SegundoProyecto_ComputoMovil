# 🐉 Kidex - Dragon Ball Character Explorer

Kidex es una aplicación Android que permite explorar personajes del universo de Dragon Ball mediante el consumo de una API REST pública. El objetivo principal es ofrecer una interfaz visualmente atractiva y funcional para consultar información básica y detallada de los personajes, incluyendo sus transformaciones.

## 📱 Características

- Consumo del API REST: [https://dragonball-api.com](https://dragonball-api.com)
- Visualización de un listado de personajes con:
  - Imagen
  - Nombre
  - Afiliación
- Vista detallada al seleccionar un personaje:
  - Imagen del personaje
  - Nombre
  - Ki actual y máximo
  - Raza
  - Género
  - Descripción
  - Afiliación
  - Transformaciones (nombre, imagen y ki)

## 🔗 API utilizada

- Obtener personajes:
  - https://dragonball-api.com/api/characters?limit=58
- Obtener detalles de un personaje (reemplazando `{id}` con el ID del personaje):
  - https://dragonball-api.com/api/characters/{id}
## 🛠️ Tecnologías utilizadas

- Kotlin
- Android Studio
- Retrofit2 (consumo de API REST)
- Glide (carga de imágenes y GIFs animados)
- ViewBinding
- RecyclerView

## 🎨 Diseño

- Pantalla de carga con logo y mensaje personalizado
- Interfaz intuitiva basada en `ConstraintLayout`
- Soporte para navegación entre fragmentos
- Diseño adaptable a diferentes tamaños de pantalla


## 🚀 Cómo ejecutar

1. Clona este repositorio:
 ```bash
 git clone https://github.com/tuusuario/kidex.git

