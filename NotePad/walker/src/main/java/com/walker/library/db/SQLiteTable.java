package com.walker.library.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SQLiteTable
{
    public static final String ID               = "_id";

    private String mTableName;
    private ArrayList<Column> mColumnsDefinitions = new ArrayList();

    public String getTableName()
    {
        return this.mTableName;
    }

    public SQLiteTable(String tableName)
    {
        this.mTableName = tableName;
        this.mColumnsDefinitions.add(
                new Column(ID,
                        Column.Constraint.PRIMARY_KEY, Column.DataType.INTEGER));
    }

    public SQLiteTable addColumn(Column columnsDefinition)
    {
        this.mColumnsDefinitions.add(columnsDefinition);
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.DataType dataType)
    {
        this.mColumnsDefinitions.add(new Column(columnName, null, dataType));
        return this;
    }

    public SQLiteTable addColumn(String columnName, Column.Constraint constraint, Column.DataType dataType)
    {
        this.mColumnsDefinitions.add(new Column(columnName, constraint, dataType));
        return this;
    }

    public void create(SQLiteDatabase db)
    {
        String formatter = " %s";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS ");
        stringBuilder.append(this.mTableName);
        stringBuilder.append("(");
        int columnCount = this.mColumnsDefinitions.size();
        int index = 0;
        for (Column columnsDefinition : this.mColumnsDefinitions)
        {
            stringBuilder.append(columnsDefinition.getColumnName()).append(
                    String.format(formatter, new Object[]{columnsDefinition.getDataType()
                            .name()}));
            Column.Constraint constraint = columnsDefinition.getConstraint();

            if (constraint != null)
            {
                stringBuilder.append(String.format(formatter, new Object[]{
                        constraint.toString()}));
            }
            if (index < columnCount - 1)
            {
                stringBuilder.append(",");
            }
            index++;
        }
        stringBuilder.append(");");
        db.execSQL(stringBuilder.toString());
    }

    public void delete(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + this.mTableName);
    }
}