# Game Management Backend API

A RESTful backend API for managing games, users, genres, and user-game relationships with scores. Built with Spring Boot and MySQL.

## 📋 Project Overview

This API provides a complete backend solution for a game management system where:
- Users can be registered and managed
- Games can be cataloged with ratings and associated genres
- Users can have scores for games they've played
- Genres help categorize and organize games

## 🛠️ Technology Stack

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Data JPA**
- **Spring Web (REST)**
- **MySQL Database**
- **Maven** (Build tool)

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.6** or higher
- **MySQL 8.0** or compatible database server
- **Git** (for cloning the repository)

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd 295_LB_Backend
```

### 2. Database Setup

1. **Start MySQL Server**
   ```bash
   # On Windows
   net start mysql
   
   # On macOS/Linux
   sudo systemctl start mysql
   # or
   brew services start mysql
   ```

2. **Create Database**
   The application will automatically create the `games` database and tables on startup due to the configuration `spring.jpa.hibernate.ddl-auto=update`.

3. **Database Configuration**
   
   Update `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=JDBC:mysql://127.0.0.1:3306/games?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Zurich
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

### 3. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## 🗃️ Database Schema

### Tables

1. **user** - Stores user information
   - `user_id` (Primary Key)
   - `username`

2. **game** - Stores game information
   - `game_id` (Primary Key)
   - `title`
   - `description`
   - `rating`

3. **genre** - Stores game genres
   - `genre_id` (Primary Key)
   - `name`
   - `description`

4. **game_genre** - Junction table for many-to-many relationship between games and genres
   - `game_id` (Foreign Key to game)
   - `genre_id` (Foreign Key to genre)
   - Composite Primary Key: (game_id, genre_id)

5. **user_game** - Junction table for many-to-many relationship between users and games with additional score data
   - `user_id` (Foreign Key to user)
   - `game_id` (Foreign Key to game)
   - `score`
   - Composite Primary Key: (user_id, game_id)

## 📡 API Endpoints

### Games (`/api/game`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/game` | Get all games |
| GET | `/api/game/{id}` | Get game by ID |
| POST | `/api/game` | Create new game |
| PUT | `/api/game/{id}` | Update game (full update) |
| PATCH | `/api/game/{id}` | Update game (partial update) |
| DELETE | `/api/game/{id}` | Delete game |

**Example: Create a Game**
```bash
curl -X POST http://localhost:8080/api/game \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Legend of Zelda",
    "description": "Action-adventure game",
    "rating": 95
  }'
```

**Example: Get All Games**
```bash
curl http://localhost:8080/api/game
```

### Users (`/api/user`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/user` | Get all users |
| GET | `/api/user/{id}` | Get user by ID |
| POST | `/api/user` | Create new user |
| PUT | `/api/user/{id}` | Update user (full update) |
| PATCH | `/api/user/{id}` | Update user (partial update) |
| DELETE | `/api/user/{id}` | Delete user |

**Example: Create a User**
```bash
curl -X POST http://localhost:8080/api/user \
  -H "Content-Type: application/json" \
  -d '{
    "username": "gamer123"
  }'
```

### Genres (`/api/genre`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/genre` | Get all genres |
| GET | `/api/genre/{id}` | Get genre by ID |
| POST | `/api/genre` | Create new genre |
| PUT | `/api/genre/{id}` | Update genre (full update) |
| PATCH | `/api/genre/{id}` | Update genre (partial update) |
| DELETE | `/api/genre/{id}` | Delete genre |

**Example: Create a Genre**
```bash
curl -X POST http://localhost:8080/api/genre \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Action",
    "description": "Fast-paced games with combat"
  }'
```

### User-Game Relationships (`/api/user-game`)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/user-game` | Get all user-game relationships |
| GET | `/api/user-game/{userId}/{gameId}` | Get specific user-game relationship |
| POST | `/api/user-game` | Create new user-game relationship with score |
| PUT | `/api/user-game/{userId}/{gameId}` | Update user-game relationship (score) |
| PATCH | `/api/user-game/{userId}/{gameId}` | Update user-game relationship (score) |
| DELETE | `/api/user-game/{userId}/{gameId}` | Delete user-game relationship |

**Example: Create User-Game Relationship**
```bash
curl -X POST http://localhost:8080/api/user-game \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "gameId": 1,
    "score": 85
  }'
```

**Example: Get User's Games with Scores**
```bash
curl http://localhost:8080/api/user-game
```

## 🏗️ Project Structure

```
src/main/java/wiss/m295_lb/_95_lb_backend/
├── Application.java                    # Main application class
├── controller/                         # REST controllers
│   ├── GameController.java            # Game API endpoints
│   ├── UserController.java            # User API endpoints
│   ├── GenreController.java           # Genre API endpoints
│   └── connecting_table/              # Junction table controllers
│       └── UserGameController.java    # User-Game relationship endpoints
├── model/                             # JPA entities
│   ├── Game.java                      # Game entity
│   ├── User.java                      # User entity
│   ├── Genre.java                     # Genre entity
│   └── connecting_table/              # Junction table entities
│       ├── UserGame.java              # User-Game relationship entity
│       └── key/                       # Composite key classes
│           └── UserGameKey.java       # Composite key for UserGame
└── repository/                        # JPA repositories
    ├── GameRepository.java            # Game data access
    ├── UserRepository.java            # User data access
    ├── GenreRepository.java           # Genre data access
    └── connecting_table/              # Junction table repositories
        └── UserGameRepository.java    # User-Game relationship data access
```

## 📝 Usage Examples

### Complete Workflow Example

1. **Create a User**
```bash
curl -X POST http://localhost:8080/api/user \
  -H "Content-Type: application/json" \
  -d '{"username": "alice"}'
```

2. **Create a Game**
```bash
curl -X POST http://localhost:8080/api/game \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Shadow Strike",
    "description": "A stealth-based action game",
    "rating": 85
  }'
```

3. **Create a Genre**
```bash
curl -X POST http://localhost:8080/api/genre \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Action",
    "description": "Fast-paced games with combat"
  }'
```

4. **Associate Game with Genre**
```bash
# This would be done through the game update endpoint with genre associations
```

5. **Record User's Game Score**
```bash
curl -X POST http://localhost:8080/api/user-game \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "gameId": 1,
    "score": 90
  }'
```

## 🔧 Configuration

The application is configured through `src/main/resources/application.properties`:

```properties
# Application name
spring.application.name=295_LB_Backend

# Database connection
spring.datasource.url=JDBC:mysql://127.0.0.1:3306/games?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Zurich
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the terms specified in the project's license file.

## 🆘 Support

For support, email leo.kuhn.zh@gmail.com or create an issue in the repository.