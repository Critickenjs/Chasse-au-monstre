X - 1ère base du serveur de sockets (client-serveur)

X - Mise en place des threads

X - Coloration des logs

- Bien traiter les threads / sockets
    - Faire en sorte de recevoir et renvoyer le modèle aux clients parallèlement
    - Bien fermer les sockets / flux de données quand il faut

- Stocker le nom du chasseur et du monstre dans le serveur afin de vérifier si le rôle hunter ou le chasseur est disponible

- Gestion des exceptions
    -> Structure des exceptions
    -> Dans le cas où le serveur s'arrête brusquement alors que le client est toujours connecté

- Finalisation de l'interface graphique pour le mode multijoueur
    - Vérifier les saisies de la fenêtre modale (mode multijoueur)
    - Bien fermer les sockets / flux de données quand il faut