package com.oc.p6_lade.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.oc.p6_lade.annotation.EmailUnique;
import com.oc.p6_lade.annotation.FieldMatch;

@FieldMatch(motDePasseFormInscription="motDePasseFormMajUtilisateur", confirmationMotDePasseFormInscription="confirmationMotDePasseFormMajUtilisateur", message="Les mots-de-passe doivent être identiques.")
public class FormMajUtilisateur {

    private Long idFormMajUtilisateur;

    @Size(min=2, max=30, message="Le prénom n'a pas la longueur appropriée.")
    @Pattern(regexp="^[a-zA-Z -]+$", message="Le prénom n'est pas valide.")
    private String prenomFormMajUtilisateur;

    @Size(min=2, max=30, message="Le nom n'a pas la longueur appropriée.")
    @Pattern(regexp="^[a-zA-Z -]+$", message="Le nom n'est pas valide.")
    private String nomFormMajUtilisateur;

    @Email(message="Veuillez saisir un email valide.")
    @EmailUnique(value="emailFormInscription")
    private String emailFormMajUtilisateur;

//    @Size(min=5, max=30, message="L'email n'a pas la longueur appropriée.")
//	@Pattern(regexp="^[a-zA-Z0-9- @^_!\\\"#$%&'()*+,./:;{}<>=|~?]+$", message="Le mot-de-passe n'est pas valide.")
//	private String motDePasseFormMajUtilisateur;

    private String confirmationMotDePasseFormMajUtilisateur;

    private Boolean membreFormMajUtilisateur;

    public Long getIdFormMajUtilisateur() {
        return idFormMajUtilisateur;
    }

    public void setIdFormMajUtilisateur(Long idFormMajUtilisateur) {
        this.idFormMajUtilisateur = idFormMajUtilisateur;
    }

    public String getPrenomFormMajUtilisateur() {
        return prenomFormMajUtilisateur;
    }

    public void setPrenomFormMajUtilisateur(String prenomFormMajUtilisateur) {
        this.prenomFormMajUtilisateur = prenomFormMajUtilisateur;
    }

    public String getNomFormMajUtilisateur() {
        return nomFormMajUtilisateur;
    }

    public void setNomFormMajUtilisateur(String nomFormMajUtilisateur) {
        this.nomFormMajUtilisateur = nomFormMajUtilisateur;
    }

    public String getEmailFormMajUtilisateur() {
        return emailFormMajUtilisateur;
    }

    public void setEmailFormMajUtilisateur(String emailFormMajUtilisateur) {
        this.emailFormMajUtilisateur = emailFormMajUtilisateur;
    }

//	public String getMotDePasseFormMajUtilisateur() {
//		return motDePasseFormMajUtilisateur;
//	}
//
//	public void setMotDePasseFormMajUtilisateur(String motDePasseFormMajUtilisateur) {
//		this.motDePasseFormMajUtilisateur = motDePasseFormMajUtilisateur;
//	}

    public String getConfirmationMotDePasseFormMajUtilisateur() {
        return confirmationMotDePasseFormMajUtilisateur;
    }

    public void setConfirmationMotDePasseFormMajUtilisateur(String confirmationMotDePasseFormMajUtilisateur) {
        this.confirmationMotDePasseFormMajUtilisateur = confirmationMotDePasseFormMajUtilisateur;
    }

    public Boolean getMembreFormMajUtilisateur() {
        return membreFormMajUtilisateur;
    }

    public void setMembreFormMajUtilisateur(Boolean membreFormMajUtilisateur) {
        this.membreFormMajUtilisateur = membreFormMajUtilisateur;
    }
}
