package com.walker.library.db;

public class Column
{
  private String mColumnName;
  private Constraint mConstraint;
  private DataType mDataType;

  public Column(String columnName, Constraint constraint, DataType dataType)
  {
    this.mColumnName = columnName;
    this.mConstraint = constraint;
    this.mDataType = dataType;
  }

  public String getColumnName()
  {
    return this.mColumnName;
  }

  public Constraint getConstraint()
  {
    return this.mConstraint;
  }

  public DataType getDataType()
  {
    return this.mDataType;
  }

  public enum Constraint
  {
    UNIQUE("UNIQUE"), NOT("NOT"), NULL("NULL"), CHECK("CHECK"), 
    FOREIGN_KEY("FOREIGN KEY"), PRIMARY_KEY("PRIMARY KEY AUTOINCREMENT");

    private String value;

    private Constraint(String value) {
      this.value = value;
    }

    public String toString()
    {
      return this.value;
    }
  }

  public static enum DataType
  {
    NULL, INTEGER, REAL, TEXT, BLOB;
  }
}