X - Vérifier tous les attributs de toutes les classes sérialisables

X - 1ère base du serveur de sockets (client-serveur)

X - Mise en place des threads

X - Coloration des logs

- Bien traiter les threads / sockets
    X - Faire en sorte de recevoir et renvoyer le modèle aux clients parallèlement
    - Bien fermer les sockets / flux de données quand il faut

- Finalisation de l'interface graphique pour le mode multijoueur
    X - Vérifier les saisies de la fenêtre modale (mode multijoueur)
      - Gérer les vues en mode multijoueur

   - Problème pour le chasseur (le bouton skipTurn ne se désactive pas quand il passe son tour)
   (optionnel) -> Dans le cas où le serveur s'arrête brusquement alors que le client est toujours connecté
        -> Les clients doivent être informés qu'ils ne sont plus connectés au serveur et (envoie un signal pour quitter la partie)
   (optionnel) -> Dans le cas où l'un des clients (ou les 2) du lobby se déconnecte (utiliser le timeout)
        -> Le serveur doit savoir qu'un client a été déconnecté et (envoie un signal pour quitter la partie)
    (optionnel) -> Gérer le cas où le port est incorrect

