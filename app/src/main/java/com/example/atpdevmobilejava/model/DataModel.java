package com.example.atpdevmobilejava.model;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DataModel {
    private FinanceDatabase database;
    private static DataModel instance = new DataModel();
    private DataModel() {

    }

    public static DataModel getInstance() {
        return instance;
    }

    public ArrayList<Finance> finances;

    public void createDatabase(Context context) {
        database = new FinanceDatabase(context);
        finances = database.getFinancesFromDB();
    }

    public ArrayList<Finance> getFinances () {
        return finances;
    }

    public Finance getFinance (int position) {
        return finances.get(position);
    }

    public boolean addFinance (Finance f) {
        long id = database.createFinance(f);
        if (id > 0) {
            f.setId(id);
            finances.add(f);
            return true;
        }
        return false;
    }

    public boolean insertFinance (Finance f, int position) {
        long id = database.insertFinance(f);
        if (id > 0) {
            finances.add(position, f);
            return true;
        }
        return false;
    }

    public boolean updateFinance (Finance f, int position) {
        int count = database.updateFinance(f);
        if (count == 1) {
            finances.set(position, f);
            return true;
        }
        return false;
    }

    public boolean updateStatus(Finance f, int position) {
        int count = database.updateStatus(f);
        if (count == 1) {
            finances.set(position, f);
            return true;
        }
        return false;
    }

    public boolean removeFinance (int position) {
        int count = database.removeFinance(getFinance(position));
        if (count == 1) {
            finances.remove(position);
        }
        return false;
    }

    public int getFinancesSize () {
        return finances.size();
    }
}
