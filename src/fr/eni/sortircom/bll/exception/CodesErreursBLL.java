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
	public static final int RULE_PARTICIPANT_USERNAME_EMPTY_ERROR = 20001;
	public static final int RULE_PARTICIPANT_FIRSTNAME_EMPTY_ERROR = 20002;
	public static final int RULE_PARTICIPANT_LASTNAME_EMPTY_ERROR = 20003;
	public static final int RULE_PARTICIPANT_PASSWORD_EMPTY_ERROR = 20004;
	public static final int RULE_PARTICIPANT_PHONE_EMPTY_ERROR = 20005;

	// Event 2001x
	public static final int RULE_EVENT_NAME_EMPTY_ERROR = 20010;
	public static final int RULE_EVENT_DATE_BEGINNING_EMPTY_ERROR = 20011;
	public static final int RULE_EVTN_DATE_END_EMPTY_ERROR = 20012;

	// City 2002x
	public static final int RULE_CITY_NAME_EMPTY_ERROR = 20020;
	public static final int RULE_CITY_POSTAL_CODE_EMPTY_ERROR = 20021;

	// Place 2003x
    public static final int RULE_PLACE_NAME_EMPTY_ERROR = 20030;

    // Site 2004x
    public static final int RULE_SITE_NAME_EMPTY_ERROR = 20040;

    // State 2005x
    public static final int RULE_STATE_LABEL_EMPTY_ERROR = 20050;


// Regles : FORMAT & Logic
// codes : 201xx

	//Participants 2010x
	public static final int RULE_PARTICIPANT_EMAIL_FORMAT_INVALID_ERROR = 20100;
	public static final int RULE_PARTICIPANT_PHONE_FORMAT_INVALID_ERROR = 20101;

	//City 2020x
	public static final int RULE_CITY_NAME_FORMAT_INVALID_ERROR = 20200;
	public static final int RULE_CITY_POSTAL_CODE_INVALID_ERROR = 20201;

	//Event 2030x
	public static final int RULE_EVENT_NAME_FORMAT_INVALID_ERROR = 20300;
	public static final int RULE_EVENT_DESCRIPTION_FORMAT_INVALID_ERROR = 20301;
	public static final int RULE_EVENT_DATE_BEGINNING_OLD_ERROR = 20302;
	public static final int RULE_EVENT_DATE_END_OLD_ERROR = 20304;
	public static final int RULE_EVENT_DATE_REGISTRATION_OLD_ERROR = 20305;

	//Place 2040x
    public static final int RULE_PLACE_NAME_FORMAT_INVALID_ERROR = 20400;

    //Site 2050x
    public static final int RULE_SITE_NAME_FORMAT_INVALID_ERROR = 20500;

    //State 2060x
    public static final int RULE_STATE_LABEL_FORMAT_INVALID_ERROR = 20600;


// Regles : CONTRAINTES UNIQUES
// codes : 220xx

	public static final int RULE_PARTICIPANT_EMAIL_UNIQUE_ERROR = 22000;

// Regles : SIGNIN : CORRESPONDANCE LOGIN & MDP
// codes : 230xx

	public static final int SIGNIN_WRONG_IDENTIFICATION_ERROR = 23000;

// Regles : OTHER (Login invalide, erreur BDD / réseau ...)
// codes : 209xx

	public static final int PARTICIPANT_CANT_CHECK_EMAIL_UNIQUE_ERROR = 20900;
	public static final int PARTICIPANT_CANT_CHECK_USERNAME_UNIQUE_ERROR = 20901;
	public static final int PARTICIPANT_CANT_SIGNIN_ERROR = 20902;
	public static final int PARTICIPANT_NULL_ERROR = 20903;
	public static final int EVENT_NULL_ERROR = 20904;
	public static final int CITY_NULL_ERROR = 20904;
}
