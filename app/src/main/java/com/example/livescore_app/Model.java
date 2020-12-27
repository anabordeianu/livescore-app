package com.example.livescore_app;

public class Model
{
    String id, echipa1, echipa2, tipMeci, statusMeci, dataMeci;

    //constructor

    public Model(String id, String echipa1, String echipa2, String tipMeci, String statusMeci, String dataMeci) {
        this.id = id;
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
        this.tipMeci = tipMeci;
        this.statusMeci = statusMeci;
        this.dataMeci = dataMeci;
    }
    //getters


    public String getId() {
        return id;
    }

    public String getEchipa1() {
        return echipa1;
    }

    public String getEchipa2() {
        return echipa2;
    }

    public String getTipMeci() {
        return tipMeci;
    }

    public String getStatusMeci() {
        return statusMeci;
    }

    public String getDataMeci() {
        return dataMeci;
    }


}

/*clasa model pentru recycler view*/