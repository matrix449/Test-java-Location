package modelo;

import java.time.LocalDateTime;

public class Location {

    private int idLocation;
    private Integer idZone;
    private Integer idAddress;
    private Double latitude;
    private Double longitude;
    private Integer idUserCreate;
    private Integer idUserUpdate;
    private LocalDateTime dateCreate;
    private LocalDateTime dateUpdate;

    // Constructor vacío
    public Location() {
    }


    // Getters y Setters
    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public Integer getIdZone() {
        return idZone;
    }

    public void setIdZone(Integer idZone) {
        this.idZone = idZone;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getIdUserCreate() {
        return idUserCreate;
    }

    public void setIdUserCreate(Integer idUserCreate) {
        this.idUserCreate = idUserCreate;
    }

    public Integer getIdUserUpdate() {
        return idUserUpdate;
    }

    public void setIdUserUpdate(Integer idUserUpdate) {
        this.idUserUpdate = idUserUpdate;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", idZone=" + idZone +
                ", idAddress=" + idAddress +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", idUserCreate=" + idUserCreate +
                ", idUserUpdate=" + idUserUpdate +
                ", dateCreate=" + dateCreate +
                ", dateUpdate=" + dateUpdate +
                '}';
    }
}