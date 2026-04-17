public class CompteEpargne extends Compte {
    private double tauxInteret;

    public CompteEpargne(String numero, String titulaire, double solde, double tauxInteret) {
        super(numero, titulaire, solde);
        this.tauxInteret = tauxInteret;
    }

    public void calculerInteret() {
        double interet = solde * tauxInteret / 100;
        solde += interet;
        System.out.println("Intérêts calculés et ajoutés : " + interet + ". Nouveau solde : " + solde);
    }

    @Override
    public void retirer(double montant) {
        if (montant > 0 && solde >= montant) {
            solde -= montant;
            System.out.println("Retrait de " + montant + " effectué (Compte Épargne). Nouveau solde : " + solde);
        } else {
            System.out.println("Retrait impossible : solde insuffisant (Compte Épargne ne permet pas de solde négatif).");
        }
    }
}
