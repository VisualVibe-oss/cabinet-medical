package com.example.cabinetmedical.infrastructure.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="examen")
public class ExamenEntity {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idExamen;
    
    @Column(nullable = false) 
    private String type ;

    @Column(nullable = false)
    private String etat ; 

    @Lob
    @Column(nullable =  true) // PostgreSQL utilisera le type 'bytea'
    private byte[] file;

    @ManyToOne
    @JoinColumn(name = "idConsultation")
    private ConsultationEntity consultation;

    public int getIdExamen() {
        return idExamen;
    }   

    public String getType() {
        return type;
    }   

    public String getEtat() {
        return etat;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setType(String type) {
        this.type = type;
    }   
    public void setEtat(String etat) {
        this.etat = etat;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }
    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }

    

}
