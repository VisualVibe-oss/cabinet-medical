package com.example.cabinetmedical.domain.model.Stats;

import com.example.cabinetmedical.application.DTO.Stats.*;
import com.example.cabinetmedical.domain.model.Depence.Depence;
import com.example.cabinetmedical.domain.model.Facture.Facture;
import com.example.cabinetmedical.domain.model.functionnalities.Functionnalitie;
import com.example.cabinetmedical.domain.utils.DepenceType;
import com.example.cabinetmedical.domain.utils.FactureState;
import com.example.cabinetmedical.domain.utils.FeatureParameter;
import com.example.cabinetmedical.domain.utils.FeatureResponce;
import com.example.cabinetmedical.domain.utils.Featurekey;
import com.example.cabinetmedical.domain.utils.payload.StatsPayload;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class StatsFunctionality implements Functionnalitie {

    @Override
    public FeatureResponce performWork(FeatureParameter param){
        StatsPayload payload = (StatsPayload) param.getPayload();

        //current month
        float monthIncome = sommefactures(payload.getMonthFactures());
        float monthExpense = sommedepences(payload.getMonthDepences());
        float monthNetProfit = monthIncome - monthExpense;

        //last month
        float lastMonthIncome = sommefactures(payload.getLastMonthFactures());
        float lastMonthExpense = sommedepences(payload.getLastMonthDepences());
        float lastMonthNetProfit = lastMonthIncome - lastMonthExpense;

        //growth
        float monthGrowthIncome = growth(monthIncome, lastMonthIncome);
        float monthgrowthExpense = growth(monthExpense, lastMonthExpense);
        float monthGrowthNetProfit = growth(monthNetProfit, lastMonthNetProfit);

        //rendez-vous
        int totalAppointments = payload.getMonthFactures().size();
        float avgAppointment = totalAppointments == 0 ? 0:monthIncome/totalAppointments;
        float peakDay=peakday(payload.getMonthFactures());

        //chart
        List<ChartDTO> chartList = buildChartList(payload.getAllDepences(), payload.getAllFactures());

        //expense breakdown
        List<ExpenseBreakdownDTO> expenseBreakdownDTOList = buildexpensebreakdown(payload.getMonthDepences());

        //transactions
        List<TransactionDTO> transactionDTOList = buildtransaction(payload.getAllDepences(), payload.getAllFactures());

        //recurring
        List<RecurringDTO> recurringDTOList = buildRecurringList(payload.getAllDepences());

        StatsDTO dto =  new StatsDTO();
        dto.setMonthIncome(monthIncome);
        dto.setMonthExpense(monthExpense);
        dto.setMonthNetProfit(monthNetProfit);

        dto.setLastmonthIncome(lastMonthIncome);
        dto.setLastmonthNetProfit(lastMonthNetProfit);

        dto.setMonthGrowthIncome(monthGrowthIncome);
        dto.setMonthGrowthExpense(monthgrowthExpense);
        dto.setMonthGrowthNetProfit(monthGrowthNetProfit);

        dto.setTotalAppointments(totalAppointments);
        dto.setAvgAppointment(avgAppointment);
        dto.setPeakDay(peakDay);

        dto.setChartList(chartList);
        dto.setTransactionList(transactionDTOList);
        dto.setExpenseBreakdownList(expenseBreakdownDTOList);
        dto.setRecurringList(recurringDTOList);

        return new FeatureResponce(Featurekey.VIEW_STATS, dto);
    }

    private float sommefactures(List<Facture> factures){
        return factures.stream().filter(f -> f.getState() == FactureState.PAID).map(Facture::getPrix).reduce(0, Integer::sum);
    }

    private float sommedepences(List<Depence> depences){
        return depences.stream().filter(d -> d.getType() != DepenceType.INITIAL_PERIODIC).map(Depence::getPrix).reduce(0.0f, Float::sum);
    }

    private float growth(float current, float last) {
        if (last == 0) return 0;

        float diff = current - last;

        if ((current < 0 && last < 0) || (current > 0 && last > 0)) {
            return (Math.abs(diff) / Math.abs(last)) * 100 * (diff > 0 ? 1 : -1);
        }
        return (diff / Math.abs(last)) * 100;
    }


    private float peakday(List<Facture> factures){
        return factures.stream().filter(f -> f.getState() == FactureState.PAID).collect(
                Collectors.groupingBy(
                        f->f.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.summingDouble(Facture::getPrix)
                )
        ).values().stream().map(Double::floatValue).max(Float::compareTo).orElse(0.0f);
    }

    private List<ExpenseBreakdownDTO> buildexpensebreakdown(List<Depence> depences) {
        return depences.stream()
                .filter(d -> d.getType() == DepenceType.ONE_TIME || d.getType() == DepenceType.PERIODIC)
                .collect(Collectors.groupingBy(
                        d -> d.getDescription() + "_" + d.getType().getName()  // group by both
                ))
                .entrySet().stream()
                .map(e -> {
                    ExpenseBreakdownDTO dto = new ExpenseBreakdownDTO();
                    DepenceType type = e.getValue().getFirst().getType(); // now all in this group have same type
                    dto.setDescription(e.getValue().getFirst().getDescription());
                    dto.setType(type.getName());
                    dto.setAmount((float) e.getValue().stream().mapToDouble(Depence::getPrix).sum());
                    return dto;
                })
                .toList();
    }


    private List<TransactionDTO> buildtransaction(List<Depence> depences, List<Facture> factures){
        List<TransactionDTO> list = new ArrayList<>();

       
        factures.stream().filter(f -> f.getState()== FactureState.PAID).forEach( f ->{
            TransactionDTO dto = new TransactionDTO();
            dto.setIdDepence(f.getIdFacture());
            dto.setDate(f.getDate());
            dto.setDescription("Rendez-Vous");
            dto.setAmount(f.getPrix());
            dto.setKind("Income");
            dto.setType(null);
            list.add(dto);
        } );

        depences.stream()
                .filter(d -> d.getType() == DepenceType.ONE_TIME || d.getType() == DepenceType.PERIODIC)
                .forEach(d -> {
                    TransactionDTO dto = new TransactionDTO();
                    dto.setIdDepence(d.getIdDepence());
                    dto.setDate(d.getDate());
                    dto.setDescription(d.getDescription());
                    dto.setAmount(d.getPrix());
                    dto.setKind("Expense");
                    dto.setType(d.getType());
                    list.add(dto);
                });

        return list;
    }
    private List<ChartDTO> buildChartList(List<Depence> depences, List<Facture> factures){
        Map<YearMonth, Float> incomePerMonth =factures.stream()
                .collect(Collectors.groupingBy(
                        f -> YearMonth.from(f.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                        Collectors.summingDouble(Facture::getPrix)
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().floatValue()));

        Map<YearMonth, Float> expensePerMonth = depences.stream()
                .filter(d -> d.getType() != DepenceType.INITIAL_PERIODIC)
                .collect(Collectors.groupingBy(
                        d -> YearMonth.from(d.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()), //group by month, so the month is the key in the map
                        Collectors.summingDouble(Depence::getPrix) //the value is the sum of prices in said month
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e ->  e.getValue().floatValue())); //convert double to float, turn the entries into a map

        List<ChartDTO> chartDTOList = new ArrayList<>();
        Set<YearMonth> allMonths = new HashSet<>();
        allMonths.addAll(incomePerMonth.keySet());
        allMonths.addAll(expensePerMonth.keySet());

        allMonths.stream().sorted().forEach(month -> {
            chartDTOList.add(new ChartDTO(month.toString(), incomePerMonth.getOrDefault(month, 0f), "income"));
            chartDTOList.add(new ChartDTO(month.toString(), expensePerMonth.getOrDefault(month, 0f), "expense"));
        });
        return chartDTOList;
    }
    private List<RecurringDTO> buildRecurringList(List<Depence> allDepences) {
        return allDepences.stream()
                .filter(d -> d.getType() == DepenceType.INITIAL_PERIODIC)
                .map(d -> {
                    System.out.println("current depence last generated: " + d.getLastGeneratedDate());
                    System.out.println("current depence anchor: "+d.getAnchorDate());
                    RecurringDTO dto = new RecurringDTO();
                    dto.setIdDepence(d.getIdDepence());
                    dto.setDescription(d.getDescription());
                    dto.setDate(d.getAnchorDate());
                    dto.setAmount(d.getPrix());
                    dto.setPeriod(d.getPeriodeJour());

                    Date lastGenerated = d.getLastGeneratedDate() == null ?  d.getAnchorDate() :  d.getLastGeneratedDate();

                    System.out.println("LAST GENERATED: " + lastGenerated);


                    Date next = new Date(lastGenerated.getTime() + (long) d.getPeriodeJour() * 24 * 60 * 60 * 1000);
                    dto.setNext(next);

                    return dto;
                })
                .toList();
    }
}
