import java.util.*;
public class Utilisateur {
    protected String nom;
    protected String prenom;
    protected double matricule;
    protected String motDePasse; // simple auth
    protected String statut; // "Passager" ou "Chauffeur"
    static Float note;
    static List<Float> notesChauffeur = new ArrayList<>();
    static List<Float> notesPassager = new ArrayList<>();

    public Utilisateur(String nom, String prenom, double matricule, String motDePasse, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.motDePasse = motDePasse;
        this.statut = statut;
        this.note=note;
    }

    public double getMatricule() {
        return matricule;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNomComplet() {
        return nom + " " + prenom;
    }

    public void setNote(Float note) {
        this.note =note;
    }
    public static Float getNote(){
        return note;
    }


    public void ajouterNoteChauffeur(float note) {
        notesChauffeur.add(note);
    }

    public void ajouterNotePassager(float note) {
        notesPassager.add(note);
    }

    public float getMoyenneChauffeur() {
        if (notesChauffeur.isEmpty()) return -1;
        float sum = 0;
        for (float n : notesChauffeur) sum += n;
        return sum / notesChauffeur.size();
    }

    public float getMoyennePassager() {
        if (notesPassager.isEmpty()) return -1;
        float sum = 0;
        for (float n : notesPassager) sum += n;
        return sum / notesPassager.size();
    }

}
