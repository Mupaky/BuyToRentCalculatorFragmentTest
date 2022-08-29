package gzz.georgi.investorscalculator.ui.home;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import gzz.georgi.investorscalculator.Calculating.Formulas;
import gzz.georgi.investorscalculator.Calculating.InputFilterMinMax;
import gzz.georgi.investorscalculator.R;

public class HomeFragment extends Fragment {

    private TextView eAnualProfit;
    private TextView eRoi;
    private EditText eHousePrice;
    private EditText eExpencec;
    private EditText eHouseRent;
    private EditText eMortage;

    private EditText managementPercent, reinovationPercent, maintenancePercent, houseTaxPercent, investmentPercent;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        eAnualProfit = root.findViewById(R.id.text_anual_profit) ;
        eRoi =  root.findViewById(R.id.text_ROI);
        eHousePrice =  root.findViewById(R.id.inputHousePrice);
        eExpencec =  root.findViewById(R.id.inputExpences);
        eHouseRent =  root.findViewById(R.id.inputHouseRent);
        eMortage =  root.findViewById(R.id.inputMortage);
        Button calculateButton = root.findViewById(R.id.buttonCalculateIt);

        managementPercent = root.findViewById(R.id.management_percent);
        reinovationPercent = root.findViewById(R.id.reinovation_percent);
        maintenancePercent = root.findViewById(R.id.maintenance_percent);
        houseTaxPercent = root.findViewById(R.id.house_tax_percent);
        investmentPercent = root.findViewById(R.id.investment_percent);

        managementPercent.setEnabled(false);
        reinovationPercent.setEnabled(false);
        maintenancePercent.setEnabled(false);
        houseTaxPercent.setEnabled(false);
        investmentPercent.setEnabled(false);

        CheckBox checkManagementPercent = root.findViewById(R.id.check_management_percent);
        CheckBox checkReinovationPercent = root.findViewById(R.id.check_reinovation_percent);
        CheckBox checkMaintenancePercent = root.findViewById(R.id.check_maintenance_percent);
        CheckBox checkHouseTaxPercent = root.findViewById(R.id.check_house_tax_percent);
        CheckBox checkInvestmentPercent = root.findViewById(R.id.check_investment_percent);


        setFilterForPercent(managementPercent);
        setFilterForPercent(reinovationPercent);
        setFilterForPercent(maintenancePercent);
        setFilterForPercent(houseTaxPercent);
        setFilterForPercent(investmentPercent);


        checkCheckBox(checkManagementPercent,managementPercent);
        checkCheckBox(checkReinovationPercent,reinovationPercent);
        checkCheckBox(checkMaintenancePercent,maintenancePercent);
        checkCheckBox(checkHouseTaxPercent, houseTaxPercent);
        checkCheckBox(checkInvestmentPercent,investmentPercent);



        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double housePrice = Formulas.checkForEmpty(eHousePrice.getText().toString());
                double expences = Formulas.checkForEmpty(eExpencec.getText().toString());
                double houseRent = Formulas.checkForEmpty(eHouseRent.getText().toString());
                double mortage = Formulas.checkForEmpty(eMortage.getText().toString());

                int management = Formulas.makeInveger(managementPercent.getText().toString());
                int reinovation = Formulas.makeInveger(reinovationPercent.getText().toString());
                int maintenance = Formulas.makeInveger(maintenancePercent.getText().toString());
                int houseTax = Formulas.makeInveger(houseTaxPercent.getText().toString());
                int investment = Formulas.makeInveger(investmentPercent.getText().toString());


                double anualPfofit = Formulas.anualProfit(houseRent,mortage,management,reinovation, maintenance);
                double roi = Formulas.returnInvestemtInPercents(housePrice, houseRent, mortage, expences,
                            management,reinovation,maintenance,houseTax,investment);

                eAnualProfit.setText(String.format("%s", new java.text.DecimalFormat("0.00").format(anualPfofit)));
                eRoi.setText(String.format("%s%s", new java.text.DecimalFormat("0.00").format(roi), getString(R.string.percent)));

            }
        });




        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
              //
            }
        });
        return root;
    }

    private void checkCheckBox(final CheckBox checkBox, final EditText editText){
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    editText.setEnabled(true);
                }
                else{
                    editText.setEnabled(false);
                    editText.setText("0");
                }
            }
        });
    }

    private void setFilterForPercent(EditText editText){
        editText.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "100")});
    }








}