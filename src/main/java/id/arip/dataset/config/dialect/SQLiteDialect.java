package id.arip.dataset.config.dialect;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

import static java.sql.Types.*;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        registerColumnType(BIT, "integer");
        registerColumnType(TINYINT, "tinyint");
        registerColumnType(SMALLINT, "smallint");
        registerColumnType(INTEGER, "integer");
        registerColumnType(BIGINT, "bigint");
        registerColumnType(FLOAT, "float");
        registerColumnType(REAL, "real");
        registerColumnType(DOUBLE, "double");
        registerColumnType(NUMERIC, "numeric");
        registerColumnType(DECIMAL, "decimal");
        registerColumnType(CHAR, "char");
        registerColumnType(VARCHAR, "varchar");
        registerColumnType(LONGVARCHAR, "longvarchar");
        registerColumnType(DATE, "date");
        registerColumnType(TIME, "time");
        registerColumnType(TIMESTAMP, "timestamp");
        registerColumnType(BINARY, "blob");
        registerColumnType(VARBINARY, "blob");
        registerColumnType(LONGVARBINARY, "blob");
        registerColumnType(BLOB, "blob");
        registerColumnType(CLOB, "clob");
        registerColumnType(BOOLEAN, "integer");
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
