package com.example.cabinetmedical.domain.model.cabinetStats;


public class CabinetStats {
    private Integer cabinetTotale;
    private Integer cabinetBasic;
    private Integer cabinetPro;

    public CabinetStats(Integer cabinetTotale, Integer cabinetStandard, Integer cabinetPremuim) {
        this.cabinetTotale = cabinetTotale;
        this.cabinetBasic = cabinetStandard;
        this.cabinetPro = cabinetPremuim;
    }

    public Integer getCabinetTotale() {
        return cabinetTotale;
    }

    public void setCabinetTotale(Integer cabinetTotale) {
        this.cabinetTotale = cabinetTotale;
    }

    public Integer getCabinetBasic() {
        return cabinetBasic;
    }

    public void setCabinetBasic(Integer cabinetStandard) {
        this.cabinetBasic = cabinetStandard;
    }

    public Integer getCabinetPro() {
        return cabinetPro;
    }

    public void setCabinetPro(Integer cabinetPremuim) {
        this.cabinetPro = cabinetPremuim;
    }
}
