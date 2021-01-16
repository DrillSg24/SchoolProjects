# Bienvenue à mon dépôt pour les jeux : Plan Langues et Casse-Briques!
Ceci est le dépôt de mes projets de WEBG2, un plan langues et un jeu casse-briques. Les deux projets sont codés sous HTML-CSS-JS, en respectant pour le jeu du casse-briques, un modèle MVC.
# Compatibilité : 
#### L'affichage des projets est prévu sous les navigateur compatibles HTML-5.

![Exemple d'affichage sous Firefox](/Screens/AffichageSousFirefox.png)
![Exemple d'affichage sous Chrome](/Screens/AffichageSousChrome.png)
![Exemple d'affichage du plan langues sous Chrome](/Screens/AffichagePlanLanguesSousChrome.png)

# Règles et déroulement du plan Langues: 
Le but du jeu est simple, choisir la langue et le niveau de difficulté et valider le choix, puis essayer de répondre correctement au plus de questions possibles!

**Les langues et difficultés proposées dans cette version :**
- Néerlandais de base
- Anglais de débutant
- Anglais pour voyager
- Anglais pour converser.


# Règles et déroulement du Casse-Briques: 
Ce jeu est extrêmement connu, et les règles sont aussi simples : il faut détruire le plus grand nombre de briques pour accumuler le plus grand score, en évitant que la balle tombe plus de fois que votre compteur de vies.

**Déroulement du jeu:**

 - La balle commence collée à la raquette, que le joueur contrôle à l'aide de sa souris.
 - Quand le joueur clique sur l'écran, la balle se lance et commence à bouger, le mouvement initial est lent, mais la balle accélère à chaque fois qu'elle retouche la raquette jusqu'à atteindre une vitesse plafond (Le pourcentage de l'accélération ainsi que la vitesse maximale sont configurables dans le fichier variables.js)
 - Dès que la balle touche une brique, celle-ci est détruite, et le joueur est crédité d'un score (personnalisable aussi dans variables.js)
 -  Quand le joueur a complètement détruit le mur d'un niveau, on lui rajoute une vie (nombre maximum de vies réglable aussi) et on passe au niveau suivant. 
 >*Cette version contient 10 niveaux, possibilités d'ajouter des niveaux propres à vous dans le fichier levels.js* 
 - Si le joueur tombe, mais qu'il a encore des vies, le jeu attendra un clic pour reprendre le jeu avec une vie en moins.
 - Si le joueur tombe et qu'il n'a plus de vies, le jeu s'arrête, affiche le score final, et attend un clic pour relancer le niveau 1.
	 
## Remerciement :
Je tiens à remercier tous les professeurs à l'ESI pour les efforts qu'ils fournissent comme nous pendant cette rude épreuve qu'est le confinement pour contrer au Covid-19. Je remercie particulièrement les professeurs Arthur Paquot, Nicolas Richard et Marco Codutti, ainsi que mes camarades de classe pour leurs indications et aides respectives reçus durant le développement du projet

Ihab TAZI, 55130 -B142-
