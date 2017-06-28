package anxa.com.smvideo.util;

/**
 * Created by allenanxa on 6/23/2017.
 */

public class InputValidatorUtil {

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static Boolean isValidPhoneFormat(String phoneNumber) {
        if (!phoneNumber.isEmpty() && phoneNumber.length() == 10 && phoneNumber.substring(0,1).equals("0"))
            return true;

        return false;
    }

    public final static String isValidHeight(String strHeight) {
        if (strHeight == null || strHeight.isEmpty()) {
            return "Merci de préciser votre taille.";
        } else {
            Double height = Double.parseDouble(strHeight);
            if (height < 140 || height > 220) {
                return "Votre taille doit être comprise entre 140 et 220 cm.";
            } else {
                return "";
            }
        }
    }

    public final static String isValidCurrentWeight(String strWeight, String strHeight) {
        if (strWeight == null || strWeight.isEmpty() || strHeight == null || strHeight.isEmpty()) {
            return "Merci de préciser votre poids.";
        } else {
            Double weight = Double.parseDouble(strWeight);
            Double height = Double.parseDouble(strHeight);
            if (weight < 40 || weight > 200) {
                return "Votre poids actuel doit être compris entre 40 et 200 kg.";
            } else if ((weight / (height * height / 10000)) < 19) {
                double test = weight / (height * height) / 10000;
                return "Attention, votre poids actuel est insuffisant par rapport à votre taille. Ce site est destiné aux personnes en surpoids, nous vous encourageons donc à consulter votre médecin traitant.";
            } else {
                return "";
            }
        }
    }

    public final static String isValidTargetWeight(String strTargetWeight, String strCurrentWeight, String strHeight) {
        if (strTargetWeight == null || strTargetWeight.isEmpty() || strCurrentWeight == null || strCurrentWeight.isEmpty() || strHeight == null || strHeight.isEmpty()) {
            return "Merci de préciser votre poids souhaité.";
        } else {
            Double targetWeight = Double.parseDouble(strTargetWeight);
            Double currentWeight = Double.parseDouble(strCurrentWeight);
            Double height = Double.parseDouble(strHeight);

            if (targetWeight < 40 || targetWeight > 200) {
                return "Merci de bien vérifier le poids que vous avez indiqué. Nous ne pouvons conseiller sur internet que les personnes dont le poids est compris entre 40 et 200 kg. Merci de bien vouloir consulter votre médecin.";
            } else if (targetWeight > currentWeight) {
                return "Votre poids idéal est censé être inférieur à votre poids actuel.";
            } else if (targetWeight < (19 * height * height / 10000)) {
                Double idealWeight = 19 * height * height / 10000;
                return "Attention, le poids que vous visez est inférieur au poids santé recommandé pour votre taille sur le plan médical, c'est à dire " + idealWeight.intValue() + " kg. Il vous est donc vivement recommandé de revoir votre objectif de poids ou de consulter votre médecin traitant.";
            } else {
                return  "";
            }
        }
    }


    /*



        If no name
        Merci de saisir votre prénom.

        if no surname
        Merci de saisir votre nom

        If invalid email
        Vous devez entrer un email valide

        Phone
        "Attention, votre numéro doit commencer par 01 à 09 et comporter 10 chiffres sans espace. Cochez la case si le numéro est étranger."

        */






}
