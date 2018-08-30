package ValidatorPackage;

import ExceptionPackage.ValidatorException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private String regexString = "^[\\p{L} .'-]+$" ;
    private String regexNumber = "[0-9]+";

    public Validator () {}

    public String controlText (String text,String area) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexString);

        if (text == null) throw new ValidatorException("Merci de remplir le champ obligatoire  ",area);
        if (text.isEmpty()) throw new ValidatorException("Merci de remplir le champ obligatoire  ",area);
        if ((text.length() < 3) || (text.length() > 50)) throw new ValidatorException("Merci de remplir le champ avec minimum 3 caractères et maximum 50 caractères dans le champ  ",area);
        Matcher matcher = r.matcher(text);
        if (!matcher.find()) throw new ValidatorException("Merci de ne composer le texte que de lettre dans le champ  ",area);
        else return text;
    }

    public String controlInitial (String text,String area) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexString);
        if (text != null)
        {
            if ((text.length()) > 3) throw new ValidatorException("Merci de remplir le champ avec maximum 3 caractères dans le champ  ",area);
            Matcher matcher = r.matcher(text);
            if (!matcher.find()) throw new ValidatorException("Merci de ne composer le texte que de lettre dans le champ  ",area);

        }
        return text;
    }

    public int controlNumber (Integer number, String area) throws ValidatorException
    {
        if (number == null) throw new ValidatorException("Merci de remplir le champ obligatoire",area);
        else {
            try {
                if (number < 0) throw new ValidatorException("Merci de remplir un nombre positif dans le champ obligatoire  ",area);
                return number;
            }
            catch (NumberFormatException e) {
                throw new ValidatorException("Merci de remplir avec uniquement des chiffres le champ obligatoire ", area);
            }
        }
    }

    public int controlRequiredNumber (Integer number,String area) throws ValidatorException
    {
        if (number != null)
        {
            try {
                if (number < 1) throw new ValidatorException("Merci de remplir un nombre positif dans le champ obligatoire  ",area);
                return number;
            }
            catch (NumberFormatException e) {
                throw new ValidatorException("Merci de remplir avec uniquement des chiffres le champ obligatoire ", area);
            }
        } else return number;
    }

    public String controlTextRequiredNumber (String text,String area) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexNumber);

        if (text == null) throw new ValidatorException("Merci de remplir le champ obligatoire  ",area);
        if (text.isEmpty()) throw new ValidatorException("Merci de remplir le champ obligatoire  ",area);
        Matcher matcher = r.matcher(text);
        if (!matcher.find()) throw new ValidatorException("Merci de ne remplir le champ que de nombres  ",area);
        else return text;

    }

    public String controlTextNumber (String text,String area) throws ValidatorException
    {
        Pattern r = Pattern.compile(regexNumber);

        if  (!text.toString().isEmpty())
        {
            Matcher matcher = r.matcher(text);
            if (!matcher.find()) throw new ValidatorException("Merci de ne remplir le champ que de nombres ou le laisser vide  ",area);
            else return text;
        } else return text;



    }

}
