# Movie Library

A full-stack web application for browsing movies and managing your personal favorites list, powered by The Movie Database (TMDB) API.

## 📖 About

Movie Library is a Spring Boot application that allows users to discover popular movies, view detailed information about each movie, and maintain a personalized favorites collection. The application integrates with TMDB API to fetch real-time movie data and provides a secure, user-friendly interface for movie enthusiasts.

## ✨ Features

- **Browse Movies**: Explore popular movies with paginated results
- **Movie Details**: View comprehensive information about individual movies including ratings, overview, and more
- **Search Functionality**: Find specific movies quickly
- **User Authentication**: Secure signup and login system with encrypted passwords
- **Favorites Management**: 
  - Add movies to your personal favorites list
  - Remove movies from favorites
  - View all your favorite movies in one place
- **Responsive UI**: Clean, user-friendly interface built with Thymeleaf templates

## 🛠️ Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **PostgreSQL** - Relational database
- **Lombok** - Reducing boilerplate code
- **Maven** - Dependency management and build tool

### Frontend
- **Thymeleaf** - Server-side template engine
- **HTML/TailwindCSS**

### External APIs
- **TMDB API** - Movie data source

## 🔒 Security

The application uses Spring Security with:
- BCrypt password encryption
- Form-based authentication
- Protected endpoints for authenticated users
- Custom user details service

## 🎯 Usage

1. **Sign Up**: Create a new account with username, email, and password
2. **Login**: Access your account using your credentials
3. **Browse**: View popular movies on the library page
4. **View Details**: Click on any movie to see detailed information
5. **Add to Favorites**: Click the favorite button on any movie detail page
6. **Manage Favorites**: Visit the favorites page to see your collection and remove movies

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is created as part of Britenet training program.

## 🙏 Acknowledgments

- Movie data provided by [The Movie Database (TMDB)](https://www.themoviedb.org/)
- Built with Spring Boot framework

