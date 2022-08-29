package gzz.georgi.investorscalculator.Calculating;

public class Formulas {


    public static double houseTax(double housePrice, double houseTaxPercent){
        if(houseTaxPercent == 0){
            return 0;
        }
        double tax = (housePrice / 100) * houseTaxPercent;
        return tax;
    }

    public static double houseInvesting(double housePrice, double investmentPercent){
        if(investmentPercent == 0){
            return 0;
        }
        double quarterInvestment = (housePrice / 100) * investmentPercent;
        return quarterInvestment;
    }

    public static double totalInvestmentPlusExpences(double housePrice, double expences,  double houseTaxPercent, double investmentPercent){
        double totalInvestment = houseTax(housePrice, houseTaxPercent) + houseInvesting(housePrice, investmentPercent) + expences;
        return totalInvestment;
    }

    public static double management(double monthlyRent, double percent){
        if(percent == 0){
            return 0;
        }
        double managementTax = (monthlyRent / 100) * percent;
        return managementTax;
    }

    public static double reinovation(double monthlyRent, double percent){
        if(percent == 0){
            return 0;
        }
        double reinovationCosts = (monthlyRent / 100) * percent;
        return reinovationCosts;
    }

    public static double maintenance(double monthlyRent, double percent){
        if(percent == 0){
            return 0;
        }
        double maintenanceSave = (monthlyRent / 100) * percent;
        return maintenanceSave;
    }


    public static double anualProfit(double monthlyRent, double mortage, double managmentPercent, double reinovationPercent,
                                     double maintenancePercent){
        double anualprofit = monthlyRent - (management(monthlyRent, managmentPercent) +
                                reinovation(monthlyRent, reinovationPercent) + mortage + maintenance(monthlyRent, maintenancePercent));
        return anualprofit*12;
    }

    public static double returnInvestemtInPercents(double housePrice, double monthlyRent, double mortage,
                                                   double expences, double managmentPercent, double reinovationPercent,
                                                   double maintenancePercent, double houseTaxPercent, double investmentPercent){
        double retunrInPercent = ((anualProfit(monthlyRent,mortage,managmentPercent, reinovationPercent, maintenancePercent)) /
                            totalInvestmentPlusExpences(housePrice, expences,houseTaxPercent,investmentPercent)) * 100;

        return retunrInPercent;
    }

    public static double checkForEmpty(String str){
        double checked;
        if(str.equals("")){
            checked = 0;
        }
        else {
            checked = Double.parseDouble(str);
        }
        return checked;
    }

    public static int makeInveger(String str){
        int checked= Integer.parseInt(str);
        return checked;
    }



}
