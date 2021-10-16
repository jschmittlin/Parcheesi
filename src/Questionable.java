public interface Questionable {
    public boolean process(Rider r);
    // le cavalier r est éventuellement modifié par la case
    // est retourné : un message qui explicite l’effet de la case
    // sur le cavalier r.
    // en version console : ce message est affiché dans la console
    // en version graphique ce message est affiché dans la fenêtre
    // graphique
}
