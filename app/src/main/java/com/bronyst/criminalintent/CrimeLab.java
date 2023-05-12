package com.bronyst.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

   private List<Crime> crimeList;

   public void addCrime(Crime crime){
       crimeList.add(crime);
   }

    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        crimeList = new ArrayList<Crime>();

    }

    public List<Crime> getCrimeList(){
        return crimeList;
    }

    public Crime getCrime(UUID id){
        for (Crime crime : crimeList){
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
