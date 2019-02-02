package com.Library.Ens_Console;

public class Enter_Separator implements Enter_null {



    public Enter_Separator(String separator){
        this.separator = separator;

    }

    @Override
    public Object action() {
        return null;
    }


    private String separator ;



    @Override
    public String getName() {
        return separator;
    }

    @Override
    public void SetName(String newName) {
        this.separator = newName;
    }

    @Override
    public String getCall() {
        return null;
    }

    @Override
    public void setCall(String newCall) {
        //
    }

    @Override
    public Object getObject() {
        return null;
    }

    @Override
    public void setObject(Object o) {
//-------
    }

    @Override
    public StringBuffer infoOfObject() {
        return null;
    }
}
