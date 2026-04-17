public class TestBanque {
    public static void main(String[] args) {
        // 5. Tableau polymorphe
        Compte[] comptes = new Compte[4];

        comptes[0] = new CompteCourant("CC01", "Alice", 1000.0, 500.0);
        comptes[1] = new CompteEpargne("CE01", "Bob", 2000.0, 2.5);
        comptes[2] = new ComptePremium("CP01", "Charlie", 5000.0, 1000.0);
        comptes[3] = new CompteCourant("CC02", "David", 300.0, 200.0);

        // Parcourir et tester
        System.out.println("--- Exécution des tests (Dépôt / Retrait / Affichage) ---");
        for (Compte c : comptes) {
            c.afficher();
            c.deposer(100.0);
            c.retirer(1200.0); // Devrait échouer ou réussir selon le type
            System.out.println("-----------------------------------------------------");
        }

        // 6. Filtrage par type (CompteEpargne)
        System.out.println("\n--- Filtrage : Comptes Épargne uniquement ---");
        for (Compte c : comptes) {
            if (c instanceof CompteEpargne) {
                c.afficher();
                // Test de la méthode spécifique
                ((CompteEpargne) c).calculerInteret();
                c.afficher();
            }
        }

        // 7. Analysis Downcasting
        System.out.println("\n--- Analyse du Downcasting (Question 7) ---");
        try {
            Compte c = new CompteEpargne("CE-TEST", "TestUser", 500.0, 1.0);
            System.out.println("Tentative de caster CompteEpargne vers CompteCourant...");
            CompteCourant cc = (CompteCourant) c; 
            System.out.println("Downcasting réussi !");
        } catch (ClassCastException e) {
            System.out.println("ERREUR : ClassCastException !");
            System.out.println("Pourquoi ? Car l'objet réel est un 'CompteEpargne'.");
            System.out.println("Même si les deux héritent de 'Compte', un CompteEpargne n'est PAS un CompteCourant.");
        }
    }
}
