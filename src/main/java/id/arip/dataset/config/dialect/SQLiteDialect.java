package id.arip.dataset.config.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import static java.sql.Types.*;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(INTEGER, "INTEGER");
        registerColumnType(VARCHAR, "VARCHAR");
    }

    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    public boolean hasAlterTable() {
        return false;
    }

    public boolean dropConstraints() {
        return false;
    }

    public String getDropForeignKeyString() {
        return "";
    }

    public String getAddForeignKeyConstraintString(String constraintName, String[] foreignKey, String referencedTable, String[] primaryKey, boolean referencesPrimaryKey) {
        return "";
    }

    public String getAddPrimaryKeyConstraintString(String constraintName) {
        return "";
    }

    public String getForUpdateString() {
        return "";
    }

    public String getAddColumnString() {
        return "add column";
    }

    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    public boolean supportsCascadeDelete() {
        return false;
    }
}
