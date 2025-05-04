class Etudiant extends Utilisateur {
    private String  faculte, specialite;
    private double anneeAdmission;

    public Etudiant(String nom, String prenom, double matricule, String motDePasse, String statut,
                    double anneeAdmission, String faculte, String specialite) {
        super(nom, prenom, matricule, motDePasse, statut);
        this.anneeAdmission = anneeAdmission;
        this.faculte = faculte;
        this.specialite = specialite;
    }
}