# TP 4 - Héritage et Polymorphisme (SmartBank)
## NOM : EL OMARI LAHCEN  
## Lien vers le projet : https://github.com/el7-assan/Java_et_Jee-TP_4.git
## 1. Classe `Compte.java` (Classe Mère)

```java
public class Compte {
    protected String numero;
    protected String titulaire;
    protected double solde;

    public Compte(String numero, String titulaire, double solde) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = solde;
    }

    // Getters and Setters
    public String getNumero() { return numero; }
    public String getTitulaire() { return titulaire; }
    public double getSolde() { return solde; }

    public void deposer(double montant) {
        if (montant > 0) {
            solde += montant;
            System.out.println("Dépôt de " + montant + " effectué. Nouveau solde : " + solde);
        }
    }

    public void retirer(double montant) {
        if (montant > 0 && solde >= montant) {
            solde -= montant;
            System.out.println("Retrait de " + montant + " effectué. Nouveau solde : " + solde);
        } else {
            System.out.println("Retrait impossible : solde insuffisant.");
        }
    }

    public void afficher() {
        System.out.println("Numéro : " + numero + ", Titulaire : " + titulaire + ", Solde : " + solde);
    }
}
```

## 2. Classe `CompteCourant.java`

```java
public class CompteCourant extends Compte {
    private double decouvertAutorise;

    public CompteCourant(String numero, String titulaire, double solde, double decouvertAutorise) {
        super(numero, titulaire, solde);
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public void retirer(double montant) {
        if (montant > 0 && (solde + decouvertAutorise) >= montant) {
            solde -= montant;
            System.out.println("Retrait de " + montant + " effectué (Compte Courant). Nouveau solde : " + solde);
        } else {
            System.out.println("Retrait impossible : découvert autorisé dépassé.");
        }
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Découvert autorisé : " + decouvertAutorise);
    }
}
```

## 3. Classe `CompteEpargne.java`

```java
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
```

## 4. Classe `ComptePremium.java`

```java
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
```

## 5. Classe `TestBanque.java`

```java
public class TestBanque {
    public static void main(String[] args) {
        Compte[] comptes = new Compte[4];
        comptes[0] = new CompteCourant("CC01", "Alice", 1000.0, 500.0);
        comptes[1] = new CompteEpargne("CE01", "Bob", 2000.0, 2.5);
        comptes[2] = new ComptePremium("CP01", "Charlie", 5000.0, 1000.0);
        comptes[3] = new CompteCourant("CC02", "David", 300.0, 200.0);

        System.out.println("--- Exécution des tests ---");
        for (Compte c : comptes) {
            c.afficher();
            c.deposer(100.0);
            c.retirer(1200.0);
            System.out.println("-----------------------------------------------------");
        }

        System.out.println("\n--- Filtrage : Comptes Épargne uniquement ---");
        for (Compte c : comptes) {
            if (c instanceof CompteEpargne) {
                c.afficher();
                ((CompteEpargne) c).calculerInteret();
            }
        }
    }
}
```

## 6. Résultats de l'exécution

```text
--- Exécution des tests (Dépôt / Retrait / Affichage) ---
Numéro : CC01, Titulaire : Alice, Solde : 1000.0
Découvert autorisé : 500.0
Dépôt de 100.0 effectué. Nouveau solde : 1100.0
Retrait de 1200.0 effectué (Compte Courant). Nouveau solde : -100.0
-----------------------------------------------------
Numéro : CE01, Titulaire : Bob, Solde : 2000.0
Dépôt de 100.0 effectué. Nouveau solde : 2100.0
Retrait de 1200.0 effectué (Compte Épargne). Nouveau solde : 900.0
-----------------------------------------------------
Numéro : CP01, Titulaire : Charlie, Solde : 5000.0
Dépôt de 100.0 effectué. Nouveau solde : 5100.0
Retrait refusé : montant (1200.0) supérieur au plafond autorisé (1000.0).
-----------------------------------------------------
Numéro : CC02, Titulaire : David, Solde : 300.0
Découvert autorisé : 200.0
Dépôt de 100.0 effectué. Nouveau solde : 400.0
Retrait impossible : découvert autorisé dépassé.
-----------------------------------------------------

--- Filtrage : Comptes Épargne uniquement ---
Numéro : CE01, Titulaire : Bob, Solde : 900.0
Intérêts calculés et ajoutés : 22.5. Nouveau solde : 922.5

--- Analyse du Downcasting (Question 7) ---
Q: Ce code fonctionne-t-il ?
A: Non.
Q: Quelle est l’erreur ?
A: ClassCastException (Erreur d'exécution).
Q: Pourquoi ?
A: Car l'objet pointé par 'c' est de type 'CompteEpargne'. On ne peut pas convertir un compte épargne en compte courant car ils sont sur des branches d'héritage différentes (Siblings). 
```
