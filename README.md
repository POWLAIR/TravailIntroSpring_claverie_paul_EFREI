# Projet Sapin Décoratif

Ce projet est une application de gestion de sapins décoratifs, permettant d'ajouter des décorations, de vendre des sapins, et de gérer les clients et leurs commandes. Il utilise **Spring Boot** pour le backend et une base de données MySQL pour le stockage des données.

---

## Fonctionnalités principales

### 1. Gestion des Sapins
- **Créer un sapin** : Ajouter un nouveau sapin dans la base de données.
- **Ajouter une décoration** : Associer une décoration à un sapin, uniquement si celui-ci n'est pas vendu.
- **Vendre un sapin** : Marquer un sapin comme vendu, générer un bon de commande associé, et calculer le coût total et le poids total des décorations.

### 2. Gestion des Clients
- **Ajouter un client** : Créer un client avec un sponsor prédéfini (Paul Claverie).
- **Consulter un client** : Récupérer les informations d'un client spécifique.
- **Lister tous les clients** : Voir tous les clients enregistrés.

### 3. Gestion des Commandes
- **Récupérer un bon de commande** : Voir les détails d'un bon de commande par son ID.

---

## Architecture du Projet

### Technologies utilisées
- **Spring Boot** : Framework principal pour le backend.
- **MySQL** : Base de données relationnelle pour stocker les sapins, décorations, clients et commandes.
- **Hibernate (JPA)** : Gestion des entités et des relations dans la base de données.

### Entités principales
1. **Sapin** : Représente un sapin décoratif.
   - Peut contenir plusieurs décorations.
   - Peut être vendu ou non.

2. **Decoration** : Représente une décoration ajoutable à un sapin.
   - Prix, poids et types possibles.

3. **Client** : Représente une personne qui peut acheter des sapins.
   - Points de fidélité ajoutés après chaque achat.

4. **BonDeCommande** : Représente une commande pour un sapin vendu.
   - Contient l'adresse de livraison, le coût total et le poids total.

---

## Installation et Exécution

### Prérequis
- **Java 17** ou version supérieure.
- **Maven** (ou Gradle) pour la gestion des dépendances.
- **MySQL** installé et configuré.

### Étapes d'installation
1. Clonez le projet :
   ```bash
   git clone <url_du_projet>
   cd <dossier_du_projet>
   ```

2. Configurez votre base de données dans `application.properties` :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/nom_de_la_base
   spring.datasource.username=nom_utilisateur
   spring.datasource.password=mot_de_passe
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Lancez le projet :
   ```bash
   mvn spring-boot:run
   ```

4. Accédez à l'API via `http://localhost:8080`.

---

## Routes principales de l'API

### Gestion des Sapins
1. **Créer un sapin** :  
   - `POST /sapin/create`  
   - Retourne : Nombre total de sapins en base.

2. **Obtenir un sapin** :  
   - `GET /sapin/get?id={id}`  
   - Retourne : Le sapin correspondant ou `null` si non trouvé.

3. **Ajouter une décoration** :  
   - `POST /sapin/addDecoration?sapinId={idSapin}&decorationId={idDecoration}`  
   - Retourne : `true` si la décoration a été ajoutée, `false` sinon.

4. **Vendre un sapin** :  
   - `POST /sapin/vente?sapinId={idSapin}&clientId={idClient}`  
   - Retourne : Le bon de commande généré ou `null` si le sapin est déjà vendu.

5. **Obtenir un bon de commande** :  
   - `GET /sapin/commande/get?id={idCommande}`  
   - Retourne : Le bon de commande correspondant.

### Gestion des Clients
1. **Créer un client** :  
   - `POST /client/post?nom={nom}&prenom={prenom}`  
   - Retourne : Le client créé.

2. **Obtenir un client** :  
   - `GET /client/get?id={id}`  
   - Retourne : Le client correspondant ou `null` si non trouvé.

3. **Lister tous les clients** :  
   - `GET /client/getAll`  
   - Retourne : La liste de tous les clients.

---

## Structure des Dossiers

- `controller/` : Contient les controllers REST (routes de l'API).
- `entity/` : Contient les entités JPA (Sapin, Decoration, Client, BonDeCommande).
- `repository/` : Contient les interfaces CRUD pour les entités.
- `SapinsApplication.java` : Point d'entrée principal de l'application.

---

## Auteur
- **Paul Claverie**
