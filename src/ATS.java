class ATS extends Utilisateur {
    private String  service;
    private double anneeRecrutement;
    public ATS(String nom, String prenom, double matricule, String motDePasse, String statut,
               double anneeRecrutement, String service) {
        super(nom, prenom, matricule, motDePasse, statut);
        this.anneeRecrutement = anneeRecrutement;
        this.service = service;
    }
}