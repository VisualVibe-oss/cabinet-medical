package com.example.cabinetmedical.application.service.adminStats;

import com.example.cabinetmedical.application.DTO.cabinetStats.CabinetStatsDTO;
import com.example.cabinetmedical.domain.model.cabinetStats.CabinetStats;
import com.example.cabinetmedical.domain.service.cabinetStats.CabinetStatsDomainService;
import com.example.cabinetmedical.infrastructure.mapper.cabinetStats.CabinetStatsMapper;
import com.example.cabinetmedical.infrastructure.repository.cabinetStats.CabinetStatsRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminStatsAppService {
    private final CabinetStatsRepository repository;
    private final CabinetStatsDomainService  domainService;
    private final CabinetStatsMapper mapper;


    public AdminStatsAppService(CabinetStatsRepository repository, CabinetStatsDomainService domainService, CabinetStatsMapper mapper) {
        this.repository = repository;
        this.domainService = domainService;
        this.mapper = mapper;
    }

    public CabinetStatsDTO getCabinetStats(){
        try {
            Integer total = repository.countCabinets();

            Integer basic = repository.countCabinetsBasic();
            Integer pro = repository.countCabinetPro();

            CabinetStats cabinetStats = new CabinetStats(total, basic, pro);

            domainService.validate(cabinetStats);

            CabinetStatsDTO cabinetStatsDTO=mapper.toDTO(cabinetStats);

            return cabinetStatsDTO;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la generation des stats: "+e);
        }
    }
}
