
```java
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
```

```java
int lenBufR = is.read(bufR);
if (lenBufR != -1) {
    // Décode le tableau de bytes en utilisant l'encodage UTF-8
    String reponse = new String(bufR, 0, lenBufR, "UTF-8");
    System.out.println("Reponse recue = " + reponse);
}
byte[] bufE = reponse.getBytes("UTF-8");
os.write(bufE);
```
