package com.iata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

public class Iata {
public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getAirport_type() {
		return airport_type;
	}
	public void setAirport_type(String airport_type) {
		this.airport_type = airport_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitudeDeg() {
		return latitudeDeg;
	}
	public void setLatitudeDeg(double latitudeDeg) {
		this.latitudeDeg = latitudeDeg;
	}
	public double getLongitudeDeg() {
		return longitudeDeg;
	}
	public void setLongitudeDeg(double longitudeDeg) {
		this.longitudeDeg = longitudeDeg;
	}
	public int getElevationFt() {
		return elevationFt;
	}
	public void setElevationFt(int elevationFt) {
		this.elevationFt = elevationFt;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getIsoCountry() {
		return isoCountry;
	}
	public void setIsoCountry(String isoCountry) {
		this.isoCountry = isoCountry;
	}
	public String getIsoRegion() {
		return isoRegion;
	}
	public void setIsoRegion(String isoRegion) {
		this.isoRegion = isoRegion;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getScheduledService() {
		return scheduledService;
	}
	public void setScheduledService(String scheduledService) {
		this.scheduledService = scheduledService;
	}
	public String getGpsCode() {
		return gpsCode;
	}
	public void setGpsCode(String gpsCode) {
		this.gpsCode = gpsCode;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getHomeLink() {
		return homeLink;
	}
	public void setHomeLink(String homeLink) {
		this.homeLink = homeLink;
	}
/**
 * latitude_deg,longitude_deg,elevation_ft,continent,iso_country,iso_region,
 * municipality,scheduled_service,gps_code,iata_code,local_code,home_link
 */
	@Id
	@Column(name = "id", nullable = false, unique = true)
	private long id;
	@Column(name = "ident", nullable = true, unique = false)
	private String ident;
	@Column(name = "airport_type", nullable = true, unique = false)
	private String airport_type;
	@Column(name = "name", nullable = true, unique = false)
	private String name;
	@Column(name = "latitude_deg", nullable = true, unique = false)
	private double latitudeDeg;
	@Column(name = "longitude_deg", nullable = true, unique = false)
	private double longitudeDeg;
	@Column(name = "elevation_ft", nullable = true, unique = false)
	private int elevationFt;
	@Column(name = "continent", nullable = true, unique = false)
	private String continent;
	@Column(name = "iso_country", nullable = true, unique = false)
	private String isoCountry;
	@Column(name = "iso_region", nullable = true, unique = false)
	private String isoRegion;
	@Column(name = "municipality", nullable = true, unique = false)
	private String municipality;
	@Column(name = "scheduled_service", nullable = true, unique = false)
	private String scheduledService;
	@Column(name = "gps_code", nullable = true, unique = false)
	private String gpsCode;
	@Column(name = "iata_code", nullable = true, unique = false)
	private String iataCode;
	@Column(name = "local_code", nullable = true, unique = false)
	private String localCode;
	@Column(name = "home_link", nullable = true, unique = false)
	private String homeLink;
	
	
	
}
