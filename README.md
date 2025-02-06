# 🛠 API de Gestion des Utilisateurs et Avis

📌 **Projet Backend en Spring Boot permettant la gestion des utilisateurs et des avis avec authentification et notifications par e-mail.**  

---

## 📖 Description  
Cette API permet aux utilisateurs de s'inscrire, de laisser des avis et de recevoir des notifications par e-mail. Elle est construite en **Spring Boot** et utilise **Spring Security** pour gérer l'authentification et **Spring Mail** pour l'envoi de notifications.

---

## 🚀 Fonctionnalités  
✅ Inscription des utilisateurs avec validation des données  
✅ Gestion et stockage des avis en base de données PostgreSQL  
✅ Sécurisation des endpoints avec **Spring Security**  
✅ Envoi d'e-mails de confirmation d'inscription avec **Spring Mail**  
✅ Documentation et tests avec **Postman**  

---

## 🛠 Technologies utilisées  
- **Langage** : Java 17  
- **Framework** : Spring Boot (Spring MVC, Spring Security, Spring Data JPA, Spring Mail)  
- **Base de données** : PostgreSQL  
- **Outils** : IntelliJ IDEA, Postman, Git  

---

## ⚙ Installation et Configuration  

### 1️⃣ Cloner le projet  
```sh
git clone https://github.com/TedHub2003/SocialBook.git
cd votre-repo
```

### 2️⃣ Configurer la base de données  
Dans **application.properties** :  
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/AvisUsers
spring.datasource.username=postgres
spring.datasource.password=teddybd
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Configurer l'envoi d'e-mails  
Ajoutez ces paramètres dans **application.properties** pour utiliser un serveur SMTP (exemple avec Gmail) :  
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=votre-email@gmail.com
spring.mail.password=votre-mot-de-passe
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 4️⃣ Lancer l'application  
Exécutez l’application avec :  
```sh
mvn spring-boot:run
```

---

## 📌 Endpoints de l'API  

| Méthode | Endpoint | Description |
|---------|---------|-------------|
| **POST** | `/utilisateur/Inscription` | Inscription d'un nouvel utilisateur |
| **POST** | `/avis/Create` | Création d'un avis utilisateur |
| **GET** | `/avis/{id}` | Récupération d’un avis spécifique |
| **GET** | `/avis/all` | Récupération de tous les avis |
| **DELETE** | `/avis/{id}` | Suppression d’un avis |

---

## 🛠 Tests et Documentation  
- Les endpoints peuvent être testés avec **Postman**  
- Pour exécuter les tests unitaires :  
```sh
mvn test
```

---


