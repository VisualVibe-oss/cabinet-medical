package com.example.cabinetmedical.application.ResponseApi;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;
    private boolean success;

    public ApiResponse() {
        // constructeur vide utilisé par le builder
    }

    public ApiResponse(int status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
        this.success = status >= 200 && status < 300;
    }

    // Getters et setters
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public boolean isSuccess() { return success; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setStatus(int status) { this.status = status; }
    public void setMessage(String message) { this.message = message; }
    public void setData(T data) { this.data = data; }
    public void setSuccess(boolean success) { this.success = success; }

    // Builder générique
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static final class Builder<T> {
        private LocalDateTime timestamp;
        private int status;
        private String message;
        private T data;
        private Boolean success; // permet de savoir si l'utilisateur a explicitement défini success

        public Builder<T> timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder<T> status(int status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> success(boolean success) {
            this.success = success;
            // si le status n'a pas été défini, fixer un status par défaut selon success
            if (this.status == 0) {
                this.status = success ? 200 : 400;
            }
            return this;
        }

        public ApiResponse<T> build() {
            ApiResponse<T> resp = new ApiResponse<>();
            resp.setTimestamp(this.timestamp != null ? this.timestamp : LocalDateTime.now());
            resp.setStatus(this.status);
            resp.setMessage(this.message);
            resp.setData(this.data);
            if (this.success != null) {
                resp.setSuccess(this.success);
            } else {
                // si success non fourni, déterminer à partir du status
                resp.setSuccess(resp.getStatus() >= 200 && resp.getStatus() < 300);
            }
            return resp;
        }
    }
}
