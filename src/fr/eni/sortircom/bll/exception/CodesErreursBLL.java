package fr.eni.sortircom.bll.exception;

/**
 * Liste des codes d'erreur
 * @author Emeline Hourmand
 *
 */
public abstract class CodesErreursBLL {

// Regles : EMPTY & NULL
// codes : 200xx
	
	// Participants 2000x
	public static final int RULE_PARTICIPANT_EMAIL_EMPTY_ERROR = 20000;
	public static final int RULE_PARTICIPANT_PSEUDO_EMPTY_ERROR = 20001;
	public static final int RULE_PARTICIPANT_PRENOM_EMPTY_ERROR = 20002;
	public static final int RULE_PARTICIPANT_NOM_EMPTY_ERROR = 20003;
	public static final int RULE_PARTICIPANT_MOT_DE_PASSE_EMPTY_ERROR = 20004;
	public static final int RULE_PARTICIPANT_TELEPHONE_EMPTY_ERROR = 20005;

	// Event 2001x
	public static final int RULE_EVENT_NOM_EMPTY_ERROR = 20010;
	public static final int RUEL_EVENT_DESCRIPTION_EMPTY_ERROR = 20011;


// Regles : FORMAT & Logic
// codes : 201xx

	//Participants 2010x
	public static final int RULE_PARTICIPANT_EMAIL_FORMAT_INVALID_ERROR=20100;
	public static final int RULE_PARTICIPANT_TELEPHONE_FORMAT_INVALID_ERROR = 20101;

// Regles : CONTRAINTES UNIQUES
// codes : 202xx

	public static final int RULE_USER_EMAIL_UNIQUE_ERROR = 20200;
	public static final int RULE_USER_PSEUDO_UNIQUE_ERROR = 20201;

// Regles : SIGNIN : CORRESPONDANCE LOGIN & MDP
// codes : 203xx

	public static final int SIGNIN_WRONG_IDENTIFICATION_ERROR = 20300;

// Regles : OTHER (Login invalide, erreur BDD / réseau ...)
// codes : 209xx

	public static final int USER_CANT_CHECK_EMAIL_UNIQUE_ERROR = 20900;
	public static final int USER_CANT_CHECK_PSEUDO_UNIQUE_ERROR = 20901;
	public static final int USER_CANT_SIGNIN_ERROR = 20902;
	public static final int USER_NULL_ERROR = 20903;
	public static final int EVENT_NULL_ERROR = 20904;
}
