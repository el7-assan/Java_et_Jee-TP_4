public class ComptePremium extends Compte {
    private double plafondRetrait;

    public ComptePremium(String numero, String titulaire, double solde, double plafondRetrait) {
        super(numero, titulaire, solde);
        this.plafondRetrait = plafondRetrait;
    }

    @Override
    public void retirer(double montant) {
        if (montant > plafondRetrait) {
            System.out.println("Retrait refusé : montant (" + montant + ") supérieur au plafond autorisé (" + plafondRetrait + ").");
        } else if (solde < montant) {
            System.out.println("Retrait refusé : solde insuffisant.");
        } else {
            solde -= montant;
            System.out.println("Retrait de " + montant + " effectué (Compte Premium). Nouveau solde : " + solde);
        }
    }
}
