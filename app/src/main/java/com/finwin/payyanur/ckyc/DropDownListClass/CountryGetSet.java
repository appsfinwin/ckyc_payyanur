package com.finwin.payyanur.ckyc.DropDownListClass;

import com.finwin.payyanur.ckyc.supportClass.CountryAndStateList;

public class CountryGetSet {
    private String id;
    private String name;

    public CountryGetSet(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CountryGetSet() {

    }

    public CountryGetSet(String in) {
        checkVal(in);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //to display object as a string in spinner
    @Override
    public String toString() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CountryGetSet) {
            CountryGetSet c = (CountryGetSet) obj;
            return c.getName().equals(name) && c.getId().equals(id);
        }

        return false;
    }

    public String checkVal(Object obj) {
        String rtn = null;
        if (obj instanceof CountryGetSet) {
            CountryGetSet c = (CountryGetSet) obj;
            if (CountryAndStateList.countryList.contains(obj)) {
                rtn = c.getId();
            }
        }
        return rtn;
    }

}
