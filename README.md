# Application d'authentification avec Spring Boot et React

Ce projet est une application d'authentification complète avec un backend Spring Boot et un frontend React. L'application permet aux utilisateurs de s'inscrire, de se connecter et d'accéder à des zones protégées.

## Structure du projet

Le projet est organisé en deux parties principales :

```
assignment/
├── backend/          # API Spring Boot
├── frontend/         # Application React
└── README.md         # Ce fichier
```

## Backend (Spring Boot)

Le backend est une API RESTful construite avec Spring Boot qui gère l'authentification et la sécurité.

### Technologies utilisées

- Java 17
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Tokens)
- PostgreSQL
- Lombok
- ModelMapper

### Configuration

La configuration de la base de données se trouve dans le fichier `application.properties` :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/test1
spring.datasource.username=postgres
spring.datasource.password=aymane@123
```

Le serveur fonctionne sur le port 1337 : `http://localhost:1337`

### Endpoints API

- **POST /api/auth/register** : Inscription d'un nouvel utilisateur
  - Corps de la requête : `{ "email": "...", "password": "...", "name": "..." }`
  - Réponse : `{ "message": "user created successfully" }`

- **POST /api/auth/login** : Connexion d'un utilisateur
  - Corps de la requête : `{ "email": "...", "password": "..." }`
  - Réponse : `{ "email": "...", "token": "..." }`

### Démarrage du backend

```bash
cd backend
./mvnw spring-boot:run
```

## Frontend (React)

Le frontend est une application React qui communique avec l'API backend.

### Technologies utilisées

- React 19
- React Router 7
- Tailwind CSS
- TypeScript

### Fonctionnalités

- Page d'inscription
- Page de connexion
- Protection des routes (redirection vers la page de connexion)
- Page d'accueil sécurisée

### Configuration

Le frontend communique avec le backend à l'adresse `http://localhost:1337`. Cette URL est codée en dur dans les fichiers de composants.

### Démarrage du frontend

```bash
cd frontend
npm install
npm run dev
```

L'application sera accessible à l'adresse `http://localhost:5173`

## Sécurité

- L'authentification utilise JWT (JSON Web Tokens)
- Les mots de passe sont hachés avec BCrypt
- Les routes API sont protégées par Spring Security
- Le CORS est configuré pour permettre les requêtes du frontend

## Utilisateur par défaut

Un utilisateur de test est créé automatiquement au démarrage de l'application :

- Email : `test@example.com`
- Mot de passe : `password123`

## Développement

### Backend

Pour ajouter de nouvelles fonctionnalités au backend :

1. Créez des entités dans `backend/src/main/java/com/hahn/assignmenet/Entities/`
2. Créez des DTOs dans `backend/src/main/java/com/hahn/assignmenet/Dtos/`
3. Créez des repositories dans `backend/src/main/java/com/hahn/assignmenet/Repositories/`
4. Créez des services dans `backend/src/main/java/com/hahn/assignmenet/Services/`
5. Créez des contrôleurs dans `backend/src/main/java/com/hahn/assignmenet/Controllers/`

### Frontend

Pour ajouter de nouvelles fonctionnalités au frontend :

1. Créez des composants dans `frontend/src/components/`
2. Ajoutez des routes dans `frontend/src/App.tsx`
3. Utilisez `localStorage` pour stocker le token JWT

## Gestion des erreurs

Le backend utilise un gestionnaire d'exceptions global (`GlobalExceptionHandler`) pour gérer les erreurs et renvoyer des réponses d'erreur cohérentes.

## Tests

Des tests fonctionnels pour les services du backend se trouvent dans `backend/src/test/java/com/hahn/assignmenet/Services/Implementations/`.
