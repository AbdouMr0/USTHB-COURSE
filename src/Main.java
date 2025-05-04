import java.util.*;

public class Main {
    static List<Utilisateur> utilisateurs = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean isConnected = false;
    static Utilisateur currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (!isConnected) {
                afficherMenuPrincipal();
                int choix = lireChoix();
                traiterChoixPrincipal(choix);
            } else {
                afficherMenuUtilisateur();
                int choix = lireChoix();
                traiterChoixUtilisateur(choix);
            }
        }
    }

    // === Menu Principal ===
    public static void afficherMenuPrincipal() {
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1. Se connecter comme un utilisateur");
        System.out.println("2. S'inscrire comme un nouvel utilisateur");
        System.out.println("3. Se connecter comme un administrateur");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    public static void traiterChoixPrincipal(int choix) {
        switch (choix) {
            case 1:
                currentUser = Login.Login(utilisateurs);
                isConnected = currentUser != null;
                break;
            case 2:
                Signup.Signup();
                break;
            case 3:
                System.out.println("⚠️ Fonction admin non implémentée.");
                break;
            case 0:
                System.out.println("👋 Au revoir !");
                System.exit(0);
                break;
            default:
                System.out.println("❌ Choix invalide !");
        }
    }

    // === Menu Utilisateur ===
    public static void afficherMenuUtilisateur() {
        System.out.println("\n=== Menu Utilisateur - Bienvenue " + currentUser.getNomComplet() + " ===");
        System.out.println("---------------------------------------");

        if (currentUser.statut == null) {
            System.out.println("1. Proposer une course (devenir chauffeur) 🚗");
            //System.out.println("2. Rechercher une course (devenir passager) 🔍");
            System.out.println("2. Demander une course (devenir un passager)");
        } else if (currentUser.statut.equals("chauffeur")) {
            System.out.println("1. Proposer une course 🚗");
            System.out.println("7. Annuler la course actuelle proposer");
            System.out.println("8. Quitter le mode chauffeur ❌");
        } else if (currentUser.statut.equals("passager")) {
           // System.out.println("2. Rechercher une course 🔍");
            System.out.println("2. Demander une course");
            System.out.println("7. Quitter le mode passager ❌");
            System.out.println("9. Terminer ou annuler la course demender");
        }
        System.out.println("3. Consulter mes trajets prévus 🗓️");
        System.out.println("4. Consulter l'historique de mes courses 📜");
        System.out.println("5. Noter un chauffeur ou passager ⭐");
        System.out.println("0. Se déconnecter 🔙");
        System.out.print("Votre choix : ");
    }

    public static void traiterChoixUtilisateur(int choix) {
        switch (choix) {
            case 1:
                if (currentUser.statut == null || currentUser.statut.equals("chauffeur")) {
                    currentUser.statut = "chauffeur";
                    Course.proposerCourse(currentUser);
                } else {
                    System.out.println("❌ En tant que passager, vous ne pouvez pas proposer une course.");
                }
                break;
            case 2:
                if (currentUser.statut == null || currentUser.statut.equals("passager")) {
                currentUser.statut = "passager";
                System.out.print("Entrez le point de départ : ");
                String departUs2 = scanner.nextLine();
                Course.demanderCourse(currentUser.nom, departUs2,currentUser.matricule);
            } else {
                System.out.println("❌ En tant que chauffeur, vous ne pouvez pas demander une course.");
            }
            case 3:
                System.out.println("Fonctionnalité en cours de développement...");
                Course.afficherTrajetsPrevus(currentUser.matricule);
                break;
            case 4:
                Course.afficherHistorique(currentUser);
                break;
            case 5:
                Course.noterUtilisateur();
                break;
            case 7:
                if (currentUser.statut.equals("chauffeur")) {
                    Course.annulerCourse(currentUser.getMatricule());
                } else if (currentUser.statut.equals("passager")) {
                    quitterStatut();
                }
                break;
            case 8:
                if (currentUser.statut.equals("chauffeur")) {
                    quitterStatut();
                }
                break;

            case 9:
                Course.terminerCoursePourPassager(currentUser);
            case 0:
                seDeconnecter();
                break;
            default:
                System.out.println("❌ Choix invalide.");
        }
    }

    public static int lireChoix() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrez un chiffre : ");
            scanner.next();
        }
        int choix = scanner.nextInt();
        scanner.nextLine(); // Clear newline
        return choix;
    }

    public static void quitterStatut() {
        if (currentUser.statut != null) {
            System.out.println("🔄 Vous quittez le mode " + currentUser.statut + ".");
            currentUser.statut = null;
        } else {
            System.out.println("❌ Vous n'êtes dans aucun mode actuellement.");
        }
    }

    public static void seDeconnecter() {
        System.out.println("🔙 Déconnexion en cours...");
        isConnected = false;
        currentUser = null;
        System.out.println("✅ Déconnecté avec succès.");
    }
}