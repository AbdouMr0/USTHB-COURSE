import java.util.*;

public class Course {
    static Scanner scanner = new Scanner(System.in);
    public static List<Course> trajetsDemandes = new ArrayList<>();
    private static List<Course> coursesDisponibles = new ArrayList<>();
    static List<Course> historiqueCourses = new ArrayList<>();
    private List<String> nomsPassagers = new ArrayList<>();
    static List<Double> matriculesPassagers = new ArrayList<>();
    private String chauffeurNom;
    private static double chauffeurMatricule; // ✅ nouveau champ
    public static int nombrePlaces;
    private String depart;
    private List<String> arrets;
    private String preferences;
    private String disponibiliteType;
    private String heureDebut;
    private String heureFin;
    private String typeCourse;
    private Float note;

    public Course(String chauffeurNom, double chauffeurMatricule, String statut, String depart, List<String> arrets,
                  String preferences, String disponibiliteType, String heureDebut,
                  String heureFin, String typeCourse, int nombrePlaces) {
        this.chauffeurNom = chauffeurNom;
        this.chauffeurMatricule = chauffeurMatricule;

        this.depart = depart;
        this.arrets = arrets;
        this.preferences = preferences;
        this.disponibiliteType = disponibiliteType;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.typeCourse = typeCourse;
        this.nombrePlaces = nombrePlaces;
        this.note = null;
    }


        private static boolean chauffeurDejaAUneCourse(double chauffeurMatricule) {
        for (Course c : coursesDisponibles) {
            if (c.chauffeurMatricule==chauffeurMatricule && c.nombrePlaces > 0) {
                return true;
            }
        }
        return false;
    }

    public static void proposerCourse(Utilisateur chauffeur) {
        if (chauffeurDejaAUneCourse(chauffeur.getMatricule())) {
            System.out.println("❌ Vous avez déjà une course active.");
            return;
        }

        System.out.println("\n=== Proposer une Course ===");

        System.out.print("Point de départ : ");
        String depart = scanner.nextLine();

        System.out.print("Nombre de points d'arrêt : ");
        int n = Integer.parseInt(scanner.nextLine());
        List<String> arrets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Point d'arrêt " + (i + 1) + " : ");
            arrets.add(scanner.nextLine());
        }

        System.out.print("Préférences : ");
        String preferences = scanner.nextLine();

        System.out.print("Type de disponibilité : ");
        String disponibiliteType = scanner.nextLine();

        System.out.print("Heure de début : ");
        String heureDebut = scanner.nextLine();

        System.out.print("Heure de fin : ");
        String heureFin = scanner.nextLine();

        System.out.print("Type de course : ");
        String typeCourse = scanner.nextLine();

        System.out.print("Nombre de places disponibles : ");
        int nombrePlaces = Integer.parseInt(scanner.nextLine());

        Course newCourse = new Course(
                chauffeur.getNomComplet(),
                chauffeur.getMatricule(), // ✅ automatiquement pris de l'utilisateur connecté
                "Chauffeur",
                depart,
                arrets,
                preferences,
                disponibiliteType,
                heureDebut,
                heureFin,
                typeCourse,
                nombrePlaces
        );

        coursesDisponibles.add(newCourse);
        historiqueCourses.add(newCourse);

        System.out.println("✅ Course ajoutée avec succès !");
    }
    public static void rechercherCourses(String depart) {
        System.out.println("\n=== Courses Disponibles depuis : " + depart + " ===");
        boolean found = false;
        int i = 1;

        for (Course course : coursesDisponibles) {
            boolean correspondDepart = course.depart.equalsIgnoreCase(depart);
            if (correspondDepart && course.nombrePlaces > 0) {
                System.out.println("Course #" + i++);
                System.out.println("Chauffeur : " + course.chauffeurNom+"avec note de "+ Utilisateur.notesChauffeur);
                System.out.println("Départ : " + course.depart);
                System.out.println("Arrêts : " + String.join(", ", course.arrets));
                System.out.println("Préférences : " + course.preferences);
                System.out.println("Disponibilité : " + course.disponibiliteType +
                        " (" + course.heureDebut + " - " + course.heureFin + ")");
                System.out.println("Type de course : " + course.typeCourse);
                System.out.println("Places disponibles : " + course.nombrePlaces);

                if (course.note == null) {
                    System.out.println("La note du chauffeur : ❌ Non noté");
                } else {
                    int etoilesPleines = course.note.intValue();
                    StringBuilder etoiles = new StringBuilder();
                    for (int ie = 0; ie < etoilesPleines; ie++) etoiles.append("★");
                    System.out.println("La note du chauffeur : " + etoiles);
                }
                System.out.println("----------------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ Aucune course trouvée.");
        }
    }

    public static void afficherHistorique(Utilisateur user) {
        System.out.println("📜 Historique des courses pour " + user.getNomComplet() + " :");
        boolean vide = true;

        for (Course c : historiqueCourses) {
            if (c.chauffeurNom.equalsIgnoreCase(user.getNomComplet())) {
                System.out.println("- " + c.depart + " -> " + String.join(", ", c.arrets) +
                        " | Type : " + c.typeCourse + " | Places restantes : " + c.nombrePlaces);
                vide = false;
            }
        }

        if (vide) {
            for (Course c : historiqueCourses) {
                System.out.println("Aucune course enregistrée. pour " + user.getNomComplet() + ":.." + c.chauffeurNom);
            }
        }
    }


    public static void annulerCourse(double matriculeChauffeur) {
        boolean found = false;
        for (int i = 0; i < coursesDisponibles.size(); i++) {
            Course c = coursesDisponibles.get(i);
            if (c.chauffeurMatricule == matriculeChauffeur) {
                coursesDisponibles.remove(i);
                System.out.println("✅ Course annulée avec succès.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ Aucune course active à annuler.");
        }
    }

    public static void noterUtilisateur() {
        System.out.println("⭐ Fonctionnalité : Noter un utilisateur.");

        System.out.print("Matricule de la personne à noter : ");
        String matriculeInput = scanner.nextLine();
        double matricule;
        try {
            matricule = Double.parseDouble(matriculeInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Format de matricule invalide.");
            return;
        }

        Utilisateur cible = null;
        for (Utilisateur u : Main.utilisateurs) {
            if (u.getMatricule() == matricule) {
                cible = u;
                break;
            }
        }

        if (cible == null) {
            System.out.println("❌ Utilisateur non trouvé.");
            return;
        }

        System.out.print("Note sur 5 : ");
        float note;
        try {
            note = Float.parseFloat(scanner.nextLine());
            if (note < 0 || note > 5) {
                System.out.println("❌ Note invalide. Entrez un nombre entre 0 et 5.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrée invalide.");
            return;
        }

        System.out.print("Noter comme chauffeur ou passager ? (c/p) : ");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("c")) {
            cible.ajouterNoteChauffeur(note);
        } else if (type.equals("p")) {
            cible.ajouterNotePassager(note);
        } else {
            System.out.println("❌ Choix invalide.");
            return;
        }

        System.out.println("✅ Merci pour votre évaluation de " + cible.getNomComplet());

        float moyenne = type.equals("c") ? cible.getMoyenneChauffeur() : cible.getMoyennePassager();

        if (moyenne == -1) {
            System.out.println("Note enregistrée : ❌ Non noté");
        } else {
            StringBuilder etoiles = new StringBuilder();
            int pleines = (int) moyenne;
            for (int i = 0; i < pleines; i++) etoiles.append("★");
            if (moyenne - pleines >= 0.5) etoiles.append("☆");
            System.out.println("Note moyenne : " + etoiles + " (" + String.format("%.2f", moyenne) + "/5)");
        }
    }

    public static void demanderCourse(String nomPassager, String departRecherche, double matriculePassager) {
        List<Course> options = new ArrayList<>();
        int index = 1;

        System.out.println("\n=== Commande de course depuis : " + departRecherche + " ===");

        for (Course course : coursesDisponibles) {
            if (course.depart.equalsIgnoreCase(departRecherche) && course.nombrePlaces > 0) {
                System.out.println("Option #" + index++);
                System.out.println("Chauffeur : " + course.chauffeurNom + "avec note de :"+ Utilisateur.notesChauffeur);
                System.out.println("Départ : " + course.depart);
                System.out.println("Arrêts : " + String.join(", ", course.arrets));
                System.out.println("Type de course : " + course.typeCourse);
                System.out.println("Disponibilité : " + course.disponibiliteType +
                        " (" + course.heureDebut + " - " + course.heureFin + ")");
                System.out.println("Places disponibles : " + course.nombrePlaces);
                System.out.println("----------------------------------------");
                options.add(course);
            }
        }

        if (options.isEmpty()) {
            System.out.println("❌ Aucune course disponible à commander.");
            return;
        }

        System.out.print("Entrez le numéro de la course que vous souhaitez commander : ");
        int choix;
        try {
            choix = Integer.parseInt(scanner.nextLine());
            if (choix < 1 || choix > options.size()) {
                System.out.println("❌ Choix invalide.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrée invalide.");
            return;
        }

        Course choisie = options.get(choix - 1);

        System.out.print("Nombre de places à réserver : ");
        int placesReservees;
        try {
            placesReservees = Integer.parseInt(scanner.nextLine());
            if (placesReservees <= 0 || placesReservees > choisie.nombrePlaces) {
                System.out.println("❌ Nombre de places invalide.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrée invalide.");
            return;
        }

        choisie.nombrePlaces -= placesReservees;

        // Ajouter nom et matricule dans les listes

            choisie.nomsPassagers.add(nomPassager);
            choisie.matriculesPassagers.add(matriculePassager);
            trajetsDemandes.add(choisie);


        System.out.println("✅ Vous avez commandé " + placesReservees + " place(s) avec le chauffeur : " + choisie.chauffeurNom);
        System.out.println("Places restantes : " + choisie.nombrePlaces);
        System.out.println("Bon voyage !");
    }
    public static void terminerCoursePourPassager(Utilisateur passagerUtilisateur) {
        boolean found = false;

        for (Course course : coursesDisponibles) {
            if (course.matriculesPassagers.contains(passagerUtilisateur.getMatricule())) {
                // Supprimer toutes les occurrences du matricule (ex. : plusieurs réservations)
                course.matriculesPassagers.removeIf(m -> m == passagerUtilisateur.getMatricule());

                System.out.println("✅ Le passager " + passagerUtilisateur.getNomComplet() + " a terminé la course avec " + course.chauffeurNom);
                found = true;

                // 🔸 Trouver le chauffeur
                Utilisateur chauffeur = null;
                for (Utilisateur u : Main.utilisateurs) {
                    if (u.getNomComplet().equalsIgnoreCase(course.chauffeurNom)) {
                        chauffeur = u;
                        break;
                    }
                }

                // 🔸 Noter le chauffeur
                if (chauffeur != null) {
                    System.out.print("⭐ Veuillez noter votre chauffeur sur 5 : ");
                    try {
                        float noteChauffeur = Float.parseFloat(scanner.nextLine());
                        if (noteChauffeur >= 0 && noteChauffeur <= 5) {
                            chauffeur.ajouterNoteChauffeur(noteChauffeur);
                            System.out.println("✅ Merci ! Votre note a été enregistrée pour le chauffeur " + chauffeur.getNomComplet());
                        } else {
                            System.out.println("❌ Note invalide. La note doit être entre 0 et 5.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Entrée invalide.");
                    }
                }

                // 🔸 Le chauffeur note le passager
                System.out.print("⭐ Le chauffeur souhaite-t-il noter ce passager ? (o/n) : ");
                String reponse = scanner.nextLine();
                if (reponse.equalsIgnoreCase("o")) {
                    System.out.print("Notez le passager sur 5 : ");
                    try {
                        float notePassager = Float.parseFloat(scanner.nextLine());
                        if (notePassager >= 0 && notePassager <= 5) {
                            passagerUtilisateur.ajouterNotePassager(notePassager);
                            System.out.println("✅ Merci ! La note du passager a été enregistrée.");
                        } else {
                            System.out.println("❌ Note invalide. La note doit être entre 0 et 5.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Entrée invalide.");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("❌ Aucun trajet en cours pour ce matricule.");
        }
    }
    public static void afficherTrajetsPrevus(double matriculePassager) {
        boolean found = false;
        System.out.println("\n📅 Trajets prévus pour le passager [" + matriculePassager + "] :");

        for (Course course : trajetsDemandes) {
            if (course.matriculesPassagers.contains(matriculePassager)) {
                found = true;
                System.out.println("🚗 Chauffeur : " + course.chauffeurNom);
                System.out.println("Départ : " + course.depart);
                System.out.println("Arrêts : " + String.join(", ", course.arrets));
                System.out.println("Type : " + course.typeCourse);
                System.out.println("Disponibilité : " + course.disponibiliteType + " (" + course.heureDebut + " - " + course.heureFin + ")");
                System.out.println("Tu as le note le chaffeur avec : "+ Utilisateur.notesChauffeur);
                System.out.println("----------------------------------------");
            }
        }

        if (!found) {
            System.out.println("❌ Aucun trajet prévu pour ce passager.");
        }
    }


}