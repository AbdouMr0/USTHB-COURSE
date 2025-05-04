import java.util.*;

public class Signup {
    static Scanner scanner = new Scanner(System.in);
    static List<Utilisateur> utilisateurs = new ArrayList<>();

    public static void Signup() {
        scanner.nextLine(); // clear buffer
        System.out.println("=== Création d’un compte ===");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Matricule (ex: 1234A) : ");
        double matricule = scanner.nextDouble();
        scanner.nextLine(); // vider le buffer après nextDouble()


        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        //System.out.print("Statut (Passager/Chauffeur) : ");
        String statut = null;
        Float note= null;

        System.out.print("Type d'utilisateur (Etudiant / Enseignant / ATS) : ");
        String type = scanner.nextLine().toLowerCase();

        Utilisateur newUser = null;

        switch (type) {
            case "etudiant":
                System.out.print("Année d’admission : ");
                double anneeAdmission = scanner.nextDouble();
                System.out.print("Faculté : ");
                String faculteEtu = scanner.nextLine();
                scanner.nextLine();
                System.out.print("Spécialité : ");
                String specialite = scanner.nextLine();
                scanner.nextLine();
                newUser = new Etudiant(nom, prenom, matricule, motDePasse, statut, anneeAdmission, faculteEtu, specialite);
                break;

            case "enseignant":
                System.out.print("Année de recrutement : ");
                double anneeRecrutEns = scanner.nextDouble();
                System.out.print("Faculté : ");
                String faculteEns = scanner.nextLine();
                scanner.nextLine();
                newUser = new Enseignant(nom, prenom, matricule, motDePasse, statut, anneeRecrutEns, faculteEns);
                break;

            case "ats":
                System.out.print("Année de recrutement : ");
                double anneeRecrutAts = scanner.nextDouble();
                System.out.print("Service de rattachement : ");
                String service = scanner.nextLine();
                scanner.nextLine();
                newUser = new ATS(nom, prenom, matricule, motDePasse, statut, anneeRecrutAts, service);
                break;

            default:
                System.out.println("Type d'utilisateur invalide. Inscription annulée.");
                return;
        }

        Main.utilisateurs.add(newUser);
        System.out.println("✅ Inscription réussie pour : " + newUser.getNomComplet());
    }
}