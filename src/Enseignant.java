class Enseignant extends Utilisateur {
    private String  faculte;
    private double anneeRecrutement;

    public Enseignant(String nom, String prenom, double matricule, String motDePasse, String statut,
                      double anneeRecrutement, String faculte) {
        super(nom, prenom, matricule, motDePasse, statut);
        this.anneeRecrutement = anneeRecrutement;
        this.faculte = faculte;
    }
}