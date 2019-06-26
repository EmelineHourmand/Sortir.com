package fr.eni.sortircom.bll.exception;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Exception lié a la couche BLL
 * @author ehourman2019
 *
 */
@SuppressWarnings("serial")
public class BLLException extends Exception {

    private static final long serialVersionUID = 2792507809394421962L;
    private List<Integer> listeCodesErreur;
    private List<String> listeNomsErreur;

    public BLLException() {
        super();
        this.listeCodesErreur=new ArrayList<>();
        this.listeNomsErreur=new ArrayList<>();
    }

    public BLLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.listeCodesErreur=new ArrayList<>();
        this.listeNomsErreur=new ArrayList<>();
    }

    public BLLException(String message, Throwable cause) {
        super(message, cause);
        this.listeCodesErreur=new ArrayList<>();
        this.listeNomsErreur=new ArrayList<>();
    }

    public BLLException(String message) {
        super(message);
        this.listeCodesErreur=new ArrayList<>();
        this.listeNomsErreur=new ArrayList<>();
    }

    public BLLException(Throwable cause) {
        super(cause);
        this.listeCodesErreur=new ArrayList<>();
        this.listeNomsErreur=new ArrayList<>();
    }

    /**
     * @param code Code de l'erreur.
     * Doit avoir un message associé dans un fichier properties.
     */
    public void addErreur(int code) {
        if(!this.listeCodesErreur.contains(code)) {
            this.listeCodesErreur.add(code);
            String errorName = "";
            for(Field field : CodesErreursBLL.class.getDeclaredFields()) {
                field.setAccessible(true);
                int compareCode = -1;
                if(field.isAccessible()){
                    try {
                        compareCode = (Integer) field.get(null);

                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    if(code == compareCode) {
                        if(errorName.length() != 0)
                            System.err.println("Plusieurs erreurs ont le même numéro : " + code );
                        errorName += field.getName();
                    }
                }
            }
            this.listeNomsErreur.add(errorName);

        }
    }

    public boolean hasErrors() { return this.listeCodesErreur.size()>0; }

    public List<Integer> getErrorCodesList() { return this.listeCodesErreur; }

    public List<String> getListeNomsErreur() { return listeNomsErreur; }

    public String errorsToString() {
        String ret = "";
        for(int i = 0; i < listeCodesErreur.size(); i++) {
            ret += String.format("Erreur : %d %s\n",
                    listeCodesErreur.get(i),
                    listeNomsErreur.get(i));
        }

        return ret;
    }
}