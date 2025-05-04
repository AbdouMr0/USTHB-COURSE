import java.util.*;
public class Login {
    static Scanner scanner = new Scanner(System.in);

    public static Utilisateur Login(List<Utilisateur> utilisateurs) {
        System.out.print("Matricule : ");
        double matricule = scanner.nextDouble();
        scanner.nextLine(); // vider le buffer

        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        for (Utilisateur u : utilisateurs) {
            if (u.getMatricule() == matricule && u.getMotDePasse().equals(motDePasse)) {
                System.out.println("✅ Connexion réussie. Bienvenue " + u.getNomComplet());
                return u;
            }
        }

        System.out.println("❌ Échec de la connexion. Vérifiez vos identifiants.");
        return null;
    }
}
