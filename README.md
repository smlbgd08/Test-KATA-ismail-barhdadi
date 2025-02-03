
# **Test KATA - Ismail Barhdadi**

## **Description du projet**

Ce projet est une application Spring Boot qui permet de gérer une liste de produits. Elle utilise une base de données MySQL pour stocker les informations relatives aux produits. Cette application expose des **API REST** pour la création, la modification, la suppression et la récupération des produits.

## **Technologies utilisées**

- **Backend**: Spring Boot
- **Base de données**: MySQL
- **ORM**: Spring Data JPA
- **API**: RESTful
- **Tests**: JUnit 5

## **Pré-requis**

- Java 17 ou version supérieure
- MySQL 5.7 ou version supérieure
- Maven

**Démarrer l'application :**
Lance l'application Spring Boot avec Maven :
   ```bash
   mvn spring-boot:run
   ```

## **Structure de l'application**

- **Model**: Entité Product
- **Repository**: Interface ProductRepository pour les interactions avec la base de données.
- **Service**: Logique métier pour la gestion des produits.
- **Controller**: API REST pour la gestion des produits.
- **config**: Configuration JWT et CORS.

## **Exposition des API REST**

1. **GET /products** - Récupère tous les produits
    - **Réponse**: Liste de tous les produits.

   2. **POST /products** - Crée un nouveau produit
       - **Request Body**:
         ```json
         {
      "code": "P123",
      "name": "Produit Test",
      "description": "Description du produit test",
      "image": "http://example.com/image.jpg",
      "category": "Électronique",
      "price": 199.99,
      "quantity": 10,
      "internalReference": "REF123",
      "shellId": 1,
      "inventoryStatus": "INSTOCK",
      "rating": 5,
      "createdAt": "2024-01-31T10:00:00Z",
      "updatedAt": "2024-01-31T10:00:00Z"
      }

         ```
       - **Réponse**: Produit créé.

3. **PUT /products/{id}** - Modifie un produit existant
    - **Request Body**:
      ```json
      {
       "code": "P123",
      "name": "Produit Test",
      "description": "Description du produit test",
      "image": "http://example.com/image.jpg",
      "category": "Électronique",
      "price": 199.99,
      "quantity": 10,
      "internalReference": "REF123",
      "shellId": 1,
      "inventoryStatus": "INSTOCK",
      "rating": 5,
      "createdAt": "2024-01-31T10:00:00Z",
      "updatedAt": "2024-01-31T10:00:00Z"
      }
      ```
    - **Réponse**: Produit mis à jour.

4. **DELETE /products/{id}** - Supprime un produit
    - **Réponse**: Message de succès ou d'erreur.


**DAuthentification et Sécurisation via JWT**D
Ce projet utilise JSON Web Token (JWT) pour gérer l'authentification des utilisateurs et sécuriser l'accès à certaines ressources de l'application. Voici un résumé des principales fonctionnalités de cette implémentation :

Création d'un compte utilisateur et connexion :

L'utilisateur peut créer un compte en envoyant ses informations (nom, prénom, email, mot de passe) via l'endpoint /register.
Lors de la connexion via l'endpoint /login, si les informations sont correctes, un token JWT est généré et renvoyé. Ce token sert à authentifier l'utilisateur pour toute demande future.
Accès Restreint aux Actions Administratives :

Seul l'utilisateur avec l'email admin@admin.com est autorisé à effectuer des actions telles que l'ajout, la modification ou la suppression de produits. L'authentification se fait en vérifiant le token JWT et en extrayant l'email de l'utilisateur.
Gestion du Panier d'Achat :

L'utilisateur peut gérer un panier d'achat contenant des produits. Il peut ajouter, supprimer et consulter les produits dans son panier.
Gestion de la Liste d'Envies :

Les utilisateurs peuvent également gérer une liste d'envies, où ils peuvent ajouter des produits qu'ils souhaitent acheter à l'avenir.
Sécurisation via JWT
Lors de la connexion, un token JWT est généré et signé avec une clé secrète.
À chaque requête nécessitant une authentification, le token est envoyé dans les en-têtes de la requête (avec le préfixe Bearer), et il est validé pour vérifier l'identité de l'utilisateur.
Seuls les utilisateurs authentifiés peuvent accéder aux fonctionnalités sécurisées de l'application.
Cette approche simple et efficace permet de sécuriser l'application tout en offrant une gestion facile des utilisateurs et des produits.

