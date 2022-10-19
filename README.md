# Projet de session
## Introduction

Dans le cadre de ce travail pratique de session, vous serez amenés à développer un système de modélisation et de calcul pour des circuits logiques. Ce travail sera effectué en 2 phases:


|              | Objectifs | Date limite de remise |  Présentation | Pondération de note|
| ----------- | ----------- |----------- |----------- |----------- |
| Phase 1      | Conception détaillée et implémentation des exigences de priorité haute. | 10/11/2021 à 23h55 |  11/11/2021 à partir de 9h30 | 10%|
| Phase 2      | Conception détaillée et implémentation des exigences de priorité moyenne. | 08/12/2021 à 23h55 |  09/12/2021 à partir de 9h30 | 20%|

## 1.Objectif du projet
### 1.1. Objectifs Pédagogique
* Concevoir et développer un logiciel de grande envergure
* Conception incrémentale
* Livraison régulière de valeur à un client


### 1.2. Environnement nécessaire et outillage
* Java (version 8 ou supérieure)
* Maven (version 3.x)
* Un environnent de développement récent (p.-ex, IntelliJ)
* Un outil de modélisation permettant de réaliser des diagrammes UML conformes (p.-ex. PlantUML, Visual Paradigm)

## 2. Déroulement du projet

### 2.1. Livraison hebdomadaire 
On s’attend de votre équipe-projet une livraison hebdomadaire, **le vendredi à 18h**. Ainsi, chaque semaine, vous devez avoir sur votre dépôt sur Github:
1.  Le code doit compiler et tester sans échec avec la commande mvn clean package lancée à la racine de votre dépôt;
2.  Une version à jour de votre journal de bord (dans le fichier journal.md), indiquant :
      * Le travail effectué dans la semaine (une ligne par membre, avec le nombre d'heures passées sur le projet et les taches effectuées)
      * Les objectifs que le projet est censé remplir
      * Votre plan d'action pour la prochaine livraison
Chaque semaine, votre projet sera récupéré et exécuté.
 
 
### 2.2.  Présentations techniques

Les présentations consistent en un entretien technique de 15 minutes avec le professeur. La présentation requiert la présence de la totalité de l'équipe, et fait partie de l'évaluation du projet. Il s'agit pour vous d'y défendre vos choix de conception et de mise en œuvre dans le projet.

Il n'y a pas de diaporama à préparer. Vous devez simplement venir au rendez-vous avec un ordinateur, prêt à montrer le code dans votre IDE et les modèles (ne perdez pas de temps à démarrer l'IDE ou visualParadigm pendant l'entretien, préparez votre session avant). Prévoyez suffisamment de batterie pour tenir la durée de l'entretien.

Vous présenterez pendant ~10 minutes votre conception et le code associé. Il est important de faire les bons choix de présentation durant cet entretien, pour mettre en valeur les points durs et vous concentrer sur l'essentiel. Il y aura ~5 minutes de question du professeur à l'équipe sur les choix de conceptions et le respect des principes de conceptions.
Important : l'entretien consiste en ~10 minutes de présentation et ~5 minutes de questions, mais ce ne sont pas deux étapes séparées de la présentation. Il s'agit d'une discussion, et on peut alterner entre présentation de l'équipe et question. 


### 2.3.  Exigence pour le produit minimal viable 
1.  Le code doit compiler et tester sans échec avec la commande mvn clean package lancée à la racine de votre dépôt;
2.  On doit trouver à la racine de votre dépôt un fichier rapport_X_mvp.pdf (où X est votre lettre d'équipe), de 5 pages maximum (letter, police 11 pt, interligne simple), contenant :
    * Le nom des participants de l'équipe
    * Les modèles de conceptions pertinent pour illustrer votre projet
      * La justification de leur pertinence
    * Une analyse critique (force / faiblesse) du projet
    * Les évolutions prioritaires qui doivent être mise en place par la suite

### 2.4.  Exigence pour le produit Final
1.  Le code doit compiler et tester sans échec avec la commande mvn clean package lancée à la racine de votre dépôt;
2.  On doit trouver à la racine de votre dépôt un fichier rapport_X_final.pdf (où X est votre lettre d'équipe), de 5 pages maximum (letter, police 11 pt, interligne simple), contenant :
    * Le nom des participants de l'équipe
    * Les modèles de conceptions pertinent pour illustrer votre projet
      * La justification de leur pertinence
    * Une analyse critique (force / faiblesse) du projet
    * Les patrons de conception utilisés, les problèmes de conceptions résolus et la justifications de leurs choix 


### 2.5  Répartition du travail

Chaque étudiant de l'équipe doit :
    * Participer à la conception du projet en étant capable d'expliquer lors de présentation les choix de conceptions effectués dans les modèles;
    * Participer à l'implémentation du code du projet (et de ses tests).
**Typiquement un étudiant ayant passé sa session à dessiner des modèles UML sans aucune participation au code du projet se verra attribuer la note de zéro (0).
**

## 3. Le code de démarrage

Exécuter le code de démarrage est disponible sur ce repository. Il s'exécute à travers les deux commandes : 	
  *  mvn clean package
  *  mvn -q exec:java

A l’exécution du Code de démarrage, un exemple d’interface graphique du jeu est affiché. Pour l'instant, le système ne fait absolument rien et les boutons sont non fonctionnels. Pour l'instant, le système ne fait absolument rien et se contente d'afficher ce que vous lui avez demandé de faire.

## 4. Description du domaine
Ce projet de session vise à réaliser la conception et l’implémentation d’un logiciel de modélisation de circuits logiques. Un model.circuit logique est un ensemble de portes logiques reliées entre‐elles à travers leurs entrées et leur sorties. Principalement utilisé en électronique, un model.circuit logique permet d’utiliser la logique booléenne pour concevoir des circuits intégrés; les portes logiques étant remplacées par des diodes ou des transistors. Les 3 portes de base correspondent aux trois opérateurs booléens de base, à savoir les portes : AND (ET logique), OR (OU inclusif) et NOT. (Négation).

|![image](https://user-images.githubusercontent.com/37906695/137015228-c93a3f93-dd40-4f01-83dd-79b3836df860.png)|
|:--:| 
| *Porte OR* |

|![image](https://user-images.githubusercontent.com/37906695/137015464-1a931cc1-a222-4927-94c1-7e4fd6225d39.png)|
|:--:| 
| *Porte AND* |

|![image](https://user-images.githubusercontent.com/37906695/137015525-69160a81-5927-497f-8d25-ba4c8c6972c8.png)|
|:--:| 
| *Porte NOT* |

Plusieurs autres portes communément utilisées peuvent être construites en utilisant ces portes telles que NAND, NOR, XOR et XNOR. 

Un model.circuit logique comporte un nombre déterminé d’entrées et de sorties booléennes (0 ou 1). Pour qu’un model.circuit soit valide, if faut que : 
* Chacune des entrées du model.circuit doit obligatoirement être reliée à au moins une entrée d’une porte; 
* Chacune des sorties du model.circuit doit obligatoirement être reliée à au plus une sortie d’une porte. 
* Pour chacune des portes qui constituent le model.circuit, chacune de ses entrées doit obligatoirement être reliées à une et une seule source. Une source pouvant être soit la sortie d’une autre porte ou une entrée du model.circuit. 
* Nous supposerons, pour simplifier, qu’un model.circuit valide ne doit par comporter de boucles. 

La Figure 1, ci--dessous, donne un exemple de model.circuit logique valide comportant trois entrées (E1, E2 et E3), une sortie (S1) et qui est constitué de deux portes AND, une porte NOT et une porte OR.

|![image](https://user-images.githubusercontent.com/37906695/137015655-edd01689-b94b-4d57-aec4-a3c1baef52c5.png)|
|:--:| 
| *Figure 1 : Circuit valide* |

Le model.circuit représenté dans la Figure2 est invalide car une des entrées de la deuxième porte AND est reliée à deux sources.
|![image](https://user-images.githubusercontent.com/37906695/137015756-4bb5aa0d-9f5d-4686-8f7a-7900c9d0a5ae.png)|
|:--:| 
| *Figure 2 : Circuit invalide* |

Comme pour une porte, à chaque model.circuit logique, on associe une table de vérité.Une table de vérité associée à chaque jeu d’entrées les sorties correspondantes. Le nombre de ligne d’une table de vérité dépend du nombre d’entrées du model.circuit :Si nous avons n entrées dans notre model.circuit, nous aurons 2n lignes. Le tableau suivant donne la table de vérité pour le model.circuit de la Figure1.

|![image](https://user-images.githubusercontent.com/37906695/137016037-e9271413-51b1-4318-b9f1-8f106cd62d83.png)|
|:--:| 
| *Table de vérité du model.circuit de la figure 1* |

## 5. Exigences Logicielles 

Les sous‐sections suivantes énumèrent les exigences du logiciel à concevoir et à implémenter. Les exigences sont regroupées à la fois logiquement et par priorité.

### 5.1 Fonctionnalisés de bases (Base) - priorité haute
 
* Base1: À l’ouverture de l’application, un model.circuit vide est crée comportant deux (2) entrées et une (1) sortie. 
* Base2 : Un model.circuit peut être sauvegardé à tout moment sur le disque dur. 
* Base2.1 : Un model.circuit doit être sauvegardé au format XMI. 
* Base2.2 : Lorsque l’usager demande à sauvegarder un model.circuit, le système doit lui demander le nom du fichier à sauvegarder ainsi que le dossier dans lequel il * doit être sauvegardé. 
* Base3 : À tout moment, l’usager doit pouvoir charger un model.circuit, préalablement sauvegardé depuis le disque dur. Le système doit demander à l’utilisateur de sélectionner le fichier à charger en indiquant le dossier et le fichier à charger. 
* Base4 : Le système doit permettre la création d’un nouveau model.circuit. Le model.circuit en cours d’édition doit alors être fermé. 
* Base5 : À tout moment, un seul model.circuit est affiché par le logiciel. Lors du chargement d’un model.circuit, le model.circuit étant déjà à l’écran est fermé par le logiciel.
* Base6 : Le logiciel doit demander une confirmation de fermeture du model.circuit en cours lors du chargement d’un autre model.circuit (Base3) ou lors de la fermeture de l’application si celui‐ci a subi des modifications depuis la dernière sauvegarde.

### 5.2 Fonctionnalités d’édition (Edit) – Priorité haute  

* Edit1: L’utilisateur doit être en mesure d’ajouter ou de supprimer des entrées et des sorties au model.circuit. 
* Edit1.1 : Le model.circuit doit comporter à tout moment au moins une entrée et au moins une sortie. Si une seule entrée ou une seule sortie existe et l’utilisateur demande à la supprimer, le système doit afficher un message d’erreur et l’opération doit être annulée. 
* Edit1.2 : Le système doit empêcher la création de plus de 5 entrées et de plus de 5 sorties dans un même model.circuit. 
* Edit2 : L’utilisateur doit pouvoir définir un nom unique pour chaque entrée ou sortie;
* Edit2.1 : Le nom d’une entrée ou d’une sortie ne doit pas excéder 5 caractères alphanumériques. 
* Edit2.2 : Le système doit générer un nom par défaut automatiquement pour chaque entrée ou sortie de la forme Ex pour une entrée et Sy pour les sorties (x et y étant des entiers). Ex. : La première entrée ajoutée du model.circuit se nommera E1, la seconde E2, etc. 
* Edit2.3 : Si l’utilisateur spécifie un nom d’entrée ou de sortie déjà existant, le système doit afficher un message d’erreur et annuler la modification. 
* Edit3: Si une entrée ou une sortie du model.circuit est supprimée alors que celle‐ci était reliée à une porte, la liaison avec la porte est également supprimée. 
* Edit4 : L’utilisateur doit être en mesure d’ajouter une porte au model.circuit. 
* Edit4.1 : Le système doit permettre l’ajout, par défaut, des portes standard à savoir : AND, OR et NOT. 
* Edit4.2 : Un model.circuit donné ne peut contenir plus de 50 portes. 
* Edit5 : L’utilisateur doit être en mesure de supprimer une porte du model.circuit. 
* Edit5.1 : Lorsqu’une porte est supprimée du model.circuit, toutes les liaisons de la porte (avec d’autres portes ou des entrée/sorties du model.circuit) doivent être supprimées automatiquement. 
* Edit6 : L’utilisateur doit être en mesure de relier une source à une entrée d’une porte ou à une sortie du model.circuit.
* Edit6.1 : Le système ne doit pas permettre de relier une source à une entrée d’une porte (ou une sortie du model.circuit) qui est déjà reliée à une source.

### 5.3 Fonctionnalités de calcul et de validation (Calc) – Priorité moyenne 

* Calc1 : Le système doit afficher en permanence la table de vérité du model.circuit.
* Calc1.1 : Le système doit générer automatiquement, dans la table de vérité, les différentes combinaisons d’entrées dans la table de vérité, selon le nombre d’entrées du model.circuit. 
* Calc1.2 : Le système doit afficher initialement la valeur « ND » (pour « non‐déterminée ») dans la/les colonne(s) de sorties de la table de vérité.  
* Calc1.3 : Le système doit actualiser les colonnes de la table de vérité à chaque ajout ou suppression d’une entrée ou d’une sortie du model.circuit. Chaque colonne doit porter le nom de l’entrée ou de la sortie correspondante. 
* Calc2 : L’utilisateur doit être en mesure de demander au système de calculer la table de vérité. Les valeurs des sorties sont alors remplacées par les valeurs calculées. 
* Calc2.1 : La fonctionnalité de calcul de la table de vérité ne doit pas être disponible si le model.circuit n’est pas valide. 
* Calc2.2 : Suite à toute modification du model.circuit (Edit1, Edit4, Edit5 et Edit6), les valeurs des sorties de la table de vérité doivent être réinitialisées à leur valeur par défaut « ND ». 
* Calc3 : Le système doit vérifier, suite à chaque modification du model.circuit (i.e. en temps réel) la validité du model.circuit.

### 5.4 Historique des modifications (HIST) – Priorité moyenne
* Hist1 : Le système doit permettre l’annulation de la dernière opération d’édition (Edit) sur le model.circuit (i.e. Undo). 
* Hist1.1 : Le système doit permettre d’annuler successivement toutes les opérations effectuées sur le model.circuit permettant ainsi de le rétablir à son état initial. L’état initial du model.circuit étant l’état vide (Base1 ou Base4) ou le model.circuit lors de son chargement (Base3). 
* Hist2 : Lors de la fermeture d’un model.circuit, toutes les données portant sur son historique de modifications NE sont PAS conservées. 
* Hist3 : Après un Undo, le système doit offrir la fonctionnalité Redo (Refaire) permettant de rétablir l’action ayant été annulée 
* Hist3.1 : Il doit être possible d’effectuer des Redos tant qu’il reste des actions qui ont été annulée en mémoire. 
* Hist3.2 : Toute modification au model.circuit doit vider la liste des Redos disponibles en mémoire. 
* Hist4 : Seules les actions d’édition du model.circuit sont concernées par les Undo/Redo. (c.f. 3.2 Edit)

### 5.5 Fonctionnalités de portes personnalisées (PERS) – Priorité moyenne‐basse 

* Pers1 : L’utilisateur doit pouvoir ajouter au model.circuit un autre model.circuit préalablement sauvegardé sur le disque (la porte personnalisée). Le model.circuit ainsi ajouté doit être utilisé et affiché comme une porte normale comportant autant d’entrées que le model.circuit ajouté et autant de sorties que le model.circuit ajouté. 
* Pers1.1 : L’utilisateur doit être en mesure d’effectuer toutes les opérations prévues pour les portes standard (Edit4, Edit5, Edit6). 
* Pers2 : Le système ne doit pas recalculer la table de vérité d’une porte personnalisée à chaque fois que l’utilisateur demande le calcul de la table de vérité du model.circuit (Calc2).

### 5.6 Fonctionnalité d’export (EXP) – Priorité basse 

* Exp1 : Le système doit permettre de générer une image graphique du model.circuit dans l’un des formats PNG ou JPG (i.e. un seul de ces deux formats doit être supporté par le système, ceci est laissé à l’appréciation du développeur). 
* Exp1.1 : Lors de l’export graphique du model.circuit, le système doit demander à l’utilisateur le dossier dans lequel l’image doit être sauvegardée ainsi que son nom du fichier. 
* Exp2 : Le système doit permettre d’exporter dans un fichier la table de vérité du model.circuit dans le format texte avec tabulations pour séparer les colonnes. 
* Exp2.1 : Lors de l’export de la table de vérité, le système doit demander à l’utilisateur le dossier dans lequel le fichier doit être sauvegardée ainsi que son nom du fichier.

### 5.7 Exigences non-fonctionnelles 

* NF1 : Le système doit manipuler le model.circuit intégralement en mémoire. Aucun système de buffer ou de cache sur le disque n’est permis. Compte tenu de la taille maximale d’un model.circuit et de la mémoire généralement disponible dans les ordinateurs contemporains, nous estimons que ceci est une exigence raisonnable. 
* NF2 : Le système doit fournir une interface utilisateur permettant à un utilisateur ayant déjà utilisé le système pendant 30mn, d’ajouter une porte à un model.circuit et relier chacune de ses entrées/sorties en moins de 30sec. En d’autres termes, l’implémentation d’une interface graphique implémentant le Drag&Drop pour la fenêtre d’édition du model.circuit n’est pas obligatoire (bien que préférable) pour peu que le système soit utilisable convenablement. 
* NF3 : Le système doit être conçu de façon à favoriser sa maintenabilité. L’utilisation de patrons de conception, lorsque approprié est requise. 
* NF4 : Le système doit permettre l’ajout de fonctionnalités d’analyse et de calcul sur le model.circuit sans affecter la structure des circuits. En d’autres termes, on doit prévoir l’ajout de fonctionnalités de calcul à l’avenir sans que les circuits créés avec une version précédente de l’application ne doivent être modifiés ou convertis.






