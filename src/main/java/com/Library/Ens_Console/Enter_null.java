package com.Library.Ens_Console;

public interface Enter_null <T> {


  T action();

  String getName();
  void SetName(String newName);

  String getCall();
  void setCall(String newCall);


  T getObject();
  void setObject(T t);

  StringBuffer infoOfObject();


  default int getOrder(){return -1;};



}
