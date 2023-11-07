public class ComparaisonCaracteres {
    public static void main(String[] args) {
        String maChaine = "Exemple";
        
        // Convertir la chaîne en tableau de caractères
        char[] tableauDeCaracteres = maChaine.toCharArray();
        
        // Parcourir le tableau et effectuer des comparaisons
        for (char caractere : tableauDeCaracteres) {
            // Faire des comparaisons ici
            if (caractere == 'e') {
                System.out.println("Trouvé un 'e' !");
            } else {
                System.out.println("Ce n'est pas un 'e'.");
            }
        }
    }
}
