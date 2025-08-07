# 🌦️ What to Wear - Weather-based Clothing Suggestion App

## 🚀 Overview
**What to Wear** is an AI-powered application that helps users decide what to wear based on real-time weather data. This project uses **Spring Boot** with **Spring AI** to fetch weather forecasts and generate personalized clothing suggestions for different parts of the day (morning, afternoon, and evening). The application integrates with the **OpenWeather API** to retrieve weather data and uses **AI** models like **OpenAI** or **Llama** to offer recommendations tailored to specific forecast conditions.

## 🔧 Tech Stack
- **Spring Boot**: Backend framework for building the core logic.
- **Spring AI**: Integrates AI to provide intelligent clothing suggestions.
- **OpenWeather API**: Provides real-time 5-day/3-hour weather forecasts.
- **RestTemplate**: For handling HTTP requests to fetch weather data.
- **Ollama**: For hosting and managing the **Llama 3.2** model.
- **Java 17**: Core language used for development.
- **Maven**: Dependency and project management.

## ✨ Features
- **Real-time weather forecasts**: Fetches weather data for any location on the globe.
- **AI-powered clothing suggestions**: Generates recommendations based on weather conditions like temperature, rain, and cloud coverage.
- **Day segmentation**: Splits the day into morning, afternoon, and evening, with clothing suggestions for each part of the day.
- **Support for Multiple AI Models**: Integrate either **OpenAI** or **Llama** models for generating recommendations.
- **Customizable prompts**: Modify AI prompts to fine-tune suggestions based on specific user preferences.

## 🛠️ Installation

### Prerequisites
- **Java 17** or higher
- **Maven**
- **OpenWeather API key**: You’ll need an API key from OpenWeather to fetch weather data. You can sign up for free and obtain an API key from [OpenWeather](https://home.openweathermap.org/users/sign_up).
- **AI Model**: Either **OpenAI** API Key or **Llama 3.2** hosted using **Ollama**.

### Clone the Repository
```bash
git clone https://github.com/your-username/what-to-wear.git
cd what-to-wear
```

### Configure API Keys
Add your **OpenWeather API Key** and configure either **OpenAI** or **Llama** settings in the `application.properties` file:

#### For OpenAI:
```properties
# OpenWeather API
openweather.api.key=your_openweather_api_key

# OpenAI API
ai.api.url=https://api.openai.com/v1/completions  # Example for OpenAI API
ai.api.key=your_openai_api_key
```

#### For Llama using Ollama:
```properties
# OpenWeather API
openweather.api.key=your_openweather_api_key

# Llama API
ai.api.url=http://localhost:11434/completions  # Assuming Llama is running on this port
```

### Build the Project
To build the project using Maven:
```bash
mvn clean install
```

### Run the Application
Once built, you can run the application:
```bash
mvn spring-boot:run
```

## 🔍 Usage

### Endpoint: `/whatToWear`

You can use the `/whatToWear` endpoint to get weather-based clothing recommendations. The endpoint requires latitude and longitude as query parameters to fetch the relevant weather data.

#### Example Request:
```
GET http://localhost:8080/whatToWear?lat=40.7128&lon=-74.0060
```

#### Example Response:
```json
{
  "morning": "Wear a light jacket, it will be cool with some clouds in the morning.",
  "afternoon": "Expect some rain in the afternoon. A raincoat is recommended.",
  "evening": "The temperature will drop further in the evening. Consider a warm sweater or jacket."
}
```

## ⚙️ Project Structure

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── dinesh
│   │   │           └── whattowear
│   │   │               ├── business           # Contains business logic (ClothingSuggestion)
│   │   │               ├── controller         # REST controller (MainController)
│   │   │               ├── model              # Models (WeatherForecast, WeatherResponse, etc.)
│   │   │               ├── service            # Weather formatting and API services
│   │   │               └── WhatToWearApp.java # Main Spring Boot application
│   └── resources
│       └── application.properties             # Configuration for OpenWeather and AI API keys
```

## 🌦️ How It Works

1. **Fetch Weather Data**: The application retrieves weather data from the **OpenWeather API** using the `/whatToWear` endpoint.
   
2. **Format Weather Data**: The weather forecast is split into three parts of the day—morning, afternoon, and evening.

3. **AI Clothing Suggestions**: The formatted weather data is passed to the AI model (**OpenAI** or **Llama**) to generate clothing suggestions tailored to the weather conditions.

4. **Response**: The AI-generated suggestions are returned to the user, advising on what to wear during each part of the day based on temperature, precipitation, cloud cover, and other weather factors.

## 🌟 Features to Implement
- User preferences for clothing styles (e.g., casual, formal).
- Integration with more detailed weather data (e.g., wind chill, UV index).
- Multi-language support for clothing suggestions.

## 📂 Example OpenWeather Response

Here is an example of the JSON response fetched from the OpenWeather API for a 5-day/3-hour forecast:

```json
{
  "list": [
    {
      "main": {
        "temp": 296.76,
        "feels_like": 296.98,
        "temp_min": 296.76,
        "temp_max": 297.87,
        "pressure": 1015,
        "humidity": 69
      },
      "weather": [
        {
          "id": 500,
          "main": "Rain",
          "description": "light rain",
          "icon": "10d"
        }
      ],
      "clouds": {
        "all": 100
      },
      "wind": {
        "speed": 0.62,
        "deg": 349,
        "gust": 1.18
      },
      "dt_txt": "2022-08-30 15:00:00"
    }
  ]
}
```

## 🛡️ License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributions
Feel free to open issues or submit pull requests. Any feedback and contributions are welcome!

---

### 📞 Contact
If you have any questions or suggestions, feel free to reach out!

