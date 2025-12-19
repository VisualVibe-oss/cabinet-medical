package com.example.cabinetmedical.infrastructure.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "refresh_tokens")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "refresh_token", nullable = false, unique = true, length = 512)
    private String refreshToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public RefreshTokenEntity() {}

    public RefreshTokenEntity(String refreshToken, Date expiryDate, String email) {
        this.refreshToken = refreshToken;
        this.expiryDate = expiryDate;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public Date getExpiryDate() { return expiryDate; }
    public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RefreshTokenEntity)) return false;
        RefreshTokenEntity that = (RefreshTokenEntity) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refreshToken);
    }

    @Override
    public String toString() {
        return "RefreshTokenEntity{" +
               "id=" + id +
               ", refreshToken='[PROTECTED]'" +
               ", expiryDate=" + expiryDate +
               ", email='" + email + '\'' +
               '}';
    }
}