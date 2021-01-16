
# Bienvenue sur mon dépôt Atelier Logiciel !

Ce dépôt contient les projets fait cette année 2020-21 dans le cadre du cours d'Atelier Logiciel donné par Pr. Bettens. Il comprend en outre les programmes suivants :<br> 
-**2048**.<br>
-**BMR**. <br>
-**AsciiPaint**<br>
-**Blackjack**

# 2048

Ce projet englobe tout ce que l'on a déjà appris en DEV1, DEV2 ansi qu'en ATL3.
Il s'agit d'une implémentation du célèbre jeu 2048. [Démo ici](https://duckduckgo.com/?q=2048) <br>
Le projet a été effectué en deux versions, avec un choix libre au début pour 
le joueur :
## Choix de la version
![Choix de la version](/Java/Screens/2048FX-Start.png) 

## Version Console 
![Jeu en version console](/Java/Screens/2048Console.png)

## Version JavaFX
###***Déroulement du jeu***
![Déroulement du jeu](/Java/Screens/2048FX-Gameplay.png)
###***Lancement d'une nouvelle partie***
![Nouvelle partie](/Java/Screens/2048FX-AfterReplay.png)
###***Bouton aide***
![Affichage aide](/Java/Screens/2048FX-Hint.png)



## Compatibilité du programme
Le lancement de l'application s'effectue de deux façons possibles :<br>
###***Lancement par le goal run du plugin javafx***<br>
-Sur Netbeans : ***Navigator -> Nom du projet -> javafx:run***<br>
-Terminal avec maven : <code>mvn clean javafx:run</code>  <br>
###***Déploiement du jeu avec toutes les librairies nécéssaires pour une modularité complète***<br>
-Sur Netbeans : ***Navigator -> Nom du projet -> javafx:jlink***, puis lancer le script 2048-Game (si créé sous Linux/MacOS) ou 2048-Game.bat (si crée sous Windows) se trouvant dans target/2048-Game/bin<br>
-Terminal avec maven : <code>mvn clean javafx:jlink</code> , et puis lancer le même script.

### Remerciements ###
<<< Merci au professeur Pierre Bettens pour son cours et sa guidance à travers celui-ci malgré la situation difficile du 2e confinement.<br> Merci également à mes camarades de classes pour leurs éclaircissements et leur entraide.>>> 

# BMR

Ce projet sert d'introduction à JavaFX ainsi qu'au design Pattern dit Observer.
Il s'agit d'un programme qui calcule le Métabolisme de base ainsi que le nombre de calories que le patient nécessite pour conserver sa masse.
L'utilisateur rentre sa taille (en cm), son poids (en kg) et son âge, ainsi que son sexe et son style de vie, et le programme affiche les réultats à droite.

## Fonctionnalités et Modifications  
-Le calcul s'effectue par le bouton calculer BMR, ou par la touche entrée.
-Les caractères non numériques ne sont pas acceptés, et pour les valeurs à virgule (poids et taille), un seul point (floating point) est autorisé.
-Le bouton Effacer les données, efface les différents champs et repart à zéro.
-3 différents graphes qui collecte les données au fur et à mesure et les affiche comme courbes.

## Compatibilité du programme
Le lancement de l'application doit passer par le plugin javafx, et son "goal" de lancement : javafx:run.


## Screenshots du programme.

Voici quelques screens du déroulement de l'application : 

![Interface initiale](/Java/Screens/BMRv3-InitialScreen.png)
![Calcul normal - Indice BMR en fonction du poids](/Java/Screens/BMRv3-NormalBmrVsWeight.png)
![Calcul normal - Calories en fonction du poids](/Java/Screens/BMRv3-NormalCaloriesVsWeight.png)
![Calcul normal - Indice BMR en fonction de la taille](/Java/Screens/BMRv3-NormalBmrVsHeight.png)
![Données manquantes](/Java/Screens/BMRv3-MissingData.png)
![Calcul erroné](/Java/Screens/BMRv3-ErrorRun.png)

# AsciiPaint

Ce projet, qui sert comme rappel du principe de l'héritage et de la copie défensive, présente un moyen très basique de dessiner des figures en caractères ascii, se comportant comme couleurs. 
Il prend en charge en outre les figures suivantes : 
- Un cercle (déterminé par son centre et son rayon)
- Un rectangle (déterminé par son coin haut gauche, sa largeur et son hauteur)
- Un carré (déterminé par son coin haut gauche et son côté)
- Un tronçon (déterminé par deux points d'extrémités)


## Fonctionnalités et Modifications  

Plusieurs fonctionnalités ont été implémentées pour ce jeu :
1. La possibilité d'ajouter une figure avec une syntaxe **bien précise** :
 - Pour un cercle : **add circle x y radius color** où x y sont les coordonnées du centre, radius est le rayon et color le caractère choisi comme couleur.
 - Pour un rectangle : **add rectangle x y width height color** où x y sont 
   les coordonnées du coin supérieur gauche, width est la largeur, height est la longueur et color le caractère choisi comme couleur.
 - Pour un carré : **add square x y side color** où x y sont les coordonnées 
   du coin supérieur gauche, side est le côté et color le caractère choisi comme couleur.
 - Pour une ligne : **add line x1 y1 x2 y2 color** où x1/2 y1/2 sont les coordonnées du premier/second point et color le caractère choisi comme couleur. 
2. La possibilité de faire bouger une figure en indiquant la commande 
   **move**, qui permet de choisir la figure à bouger et avec quels deltas.
3. La possibilité de zoomer/dézoomer une figure en indiquant la commande 
   **zoom**, qui permet de choisir quelle figure déformer et par quel facteur (strictement positif seulement !). *Attention, une ligne ne peut être zoomée*
4. La possibilité d'effacer une figure en indiquant la commande **remove**, qui permet de choisir la figure à supprimer.
5. La possibilité de changer la couleur d'une figure en indiquant la commande **color**, qui permet de choisir à quelle figure changer la couleur avec un nouveau caractère de votre choix.
6. La possibilité d'afficher le dessin dans son état, en indiquant la commande **show**.
7. La possibilité d'effacer le dessin courant et de commencer un nouveau, en indiquant la commande **clear**, qui permettra de choisir les nouvelles dimensions du dessin.
8. Le jeu est quitté simplement en indiquant la commande **exit**.
	  

## Compatibilité du jeu

Le lancement du jeu s'effectue par la commande : 
<pre><code>java -jar AsciiPaint.jar</pre></code>

## Screenshots du jeu.

Voici quelques screens du déroulement du jeu : 

![Afffichage d'un cercle](/Java/Screens/AsciiPaint_Circle.png)
![Affichage de deux ligne croisées](/Java/Screens/AsciiPaint_Cross.png)


# Blackjack

Ce projet, qui sert comme rappel du principe de MVC et d'héritage, présente une version simplifiée du célèbre jeu du Blackjack. 
Les règles sont les suivantes (telles que spécifiées dans l'énoncé du projet) : 
Le jeu se joue par manche jusqu’à ce que le joueur s’arrête. Une manche du jeu se
déroule comme suit :
1. le joueur reçoit 2 cartes
2. le joueur choisit de demander une carte supplémentaire ou de stopper
3. si le joueur choisit une carte supplémentaire il la reçoit, si son score dépasse
21 il a perdu (et perd la mise), sinon il retourne au point précédent (2)
4. si le joueur choisit au point (2) de stopper c’est au tour de la banque de jouer
5. la banque joue, si elle obtient plus de 21 ou moins que le joueur le joueur gagne
(et donc gagne la mise) et sinon il perd.
La stratégie de la banque est de tirer une carte jusqu’à atteindre au moins 17.

## Modifications et simplifications 

Certaines simplifications et modifications servant à simplifier le code et de rendre le jeu plus risqué/amusant ont été choisies :
- Le paquet n'est pas mélangé à chaque fin de manche, et le jour s'arrête à l'état quand il n'y a plus de cartes à piocher.
- Par contre, la valeur de l'as est fixé à 1 au lieu d'un choix entre 1 et 11. 

## Compatibilité du jeu

En égard du caractère simple et obsolète des consoles Windows, l'affichage est très fortement perturbé par le manque de couleurs pour les cartes, ainsi que l'absence d'affichage des symboles Unicode. Il est dès lors très recommandé de lancer le jeu sur un terminal Linux/Unix, ou bien sur un "émulateur" utilisant le sous-système Linux sous Windows couplé avec l'application Terminal for Windows (disponible ici : ), ou bien sous Cygwin et logiciels semblables.
![Problème d'affichage sous Windows](/Java/Screens/Screen_WindowsGraphicsProblem.png)

Le lancement du jeu s'effectue par la commande : 
<pre><code>java -jar Blackjack.jar</pre></code>

## Screenshots du jeu.

Voici quelques screens du déroulement du jeu : 

![Afffichage du jeu en terminal Ubuntu](/Java/Screens/Screen_WSL.png)
![Déroulement de la partie](/Java/Screens/Screen2.png)
![](/Java/Screens/Screen3.png)
*Fin de la partie*



### Auteur : 
<<< Ihab Tazi, Groupe C122, 55130 BA2 Informatique de Gestion, HE2B ESI-École Supérieure d'informatique >>> 