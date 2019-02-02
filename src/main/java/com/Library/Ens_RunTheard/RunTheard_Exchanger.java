package com.Library.Ens_RunTheard;

import java.util.concurrent.Exchanger;

public abstract class RunTheard_Exchanger extends RunTheard_default{


    public RunTheard_Exchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }





    //------------------------------------------------------------------
   private final Exchanger exchanger;

    public Exchanger getExchanger() {
        return exchanger;
    }
}
