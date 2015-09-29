package com.vladkel.dametenebra.model;

/**
 * @author eliott
 *
 */
public class Photo {

	private int photo_id;

	private String photo_name;

	private String photo_description;

	private String photo_date;
	
	private String photo_link;

	private String photo_mini_link;

	private String appareil;
	
	private String obturation;

	private String ouverture;

	private String longueur_focale;
	
	private String vitesse_ISO;

	private int category_photo;

	private int active;
	
	public Photo() {
		super();
	}

	/**
	 * @return the photo_id
	 */
	public int getPhoto_id() {
		return photo_id;
	}

	/**
	 * @param photo_id the photo_id to set
	 */
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}

	/**
	 * @return the photo_name
	 */
	public String getPhoto_name() {
		return photo_name;
	}

	/**
	 * @param photo_name the photo_name to set
	 */
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}

	/**
	 * @return the photo_description
	 */
	public String getPhoto_description() {
		return photo_description;
	}

	/**
	 * @param photo_description the photo_description to set
	 */
	public void setPhoto_description(String photo_description) {
		this.photo_description = photo_description;
	}

	/**
	 * @return the photo_date
	 */
	public String getPhoto_date() {
		return photo_date;
	}

	/**
	 * @param photo_date the photo_date to set
	 */
	public void setPhoto_date(String photo_date) {
		this.photo_date = photo_date;
	}

	/**
	 * @return the photo_link
	 */
	public String getPhoto_link() {
		return photo_link;
	}

	/**
	 * @param photo_link the photo_link to set
	 */
	public void setPhoto_link(String photo_link) {
		this.photo_link = photo_link;
	}

	/**
	 * @return the photo_mini_link
	 */
	public String getPhoto_mini_link() {
		return photo_mini_link;
	}

	/**
	 * @param photo_mini_link the photo_mini_link to set
	 */
	public void setPhoto_mini_link(String photo_mini_link) {
		this.photo_mini_link = photo_mini_link;
	}

	/**
	 * @return the appareil
	 */
	public String getAppareil() {
		return appareil;
	}

	/**
	 * @param appareil the appareil to set
	 */
	public void setAppareil(String appareil) {
		this.appareil = appareil;
	}

	/**
	 * @return the obturation
	 */
	public String getObturation() {
		return obturation;
	}

	/**
	 * @param obturation the obturation to set
	 */
	public void setObturation(String obturation) {
		this.obturation = obturation;
	}

	/**
	 * @return the ouverture
	 */
	public String getOuverture() {
		return ouverture;
	}

	/**
	 * @param ouverture the ouverture to set
	 */
	public void setOuverture(String ouverture) {
		this.ouverture = ouverture;
	}

	/**
	 * @return the longueur_focale
	 */
	public String getLongueur_focale() {
		return longueur_focale;
	}

	/**
	 * @param longueur_focale the longueur_focale to set
	 */
	public void setLongueur_focale(String longueur_focale) {
		this.longueur_focale = longueur_focale;
	}

	/**
	 * @return the vitesse_ISO
	 */
	public String getVitesse_ISO() {
		return vitesse_ISO;
	}

	/**
	 * @param vitesse_ISO the vitesse_ISO to set
	 */
	public void setVitesse_ISO(String vitesse_ISO) {
		this.vitesse_ISO = vitesse_ISO;
	}

	/**
	 * @return the category_photo
	 */
	public int getCategory_photo() {
		return category_photo;
	}

	/**
	 * @param category_photo the category_photo to set
	 */
	public void setCategory_photo(int category_photo) {
		this.category_photo = category_photo;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
	
}
